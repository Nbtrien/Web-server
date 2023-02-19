import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import core.DataPackage;
import core.FileEvent;
import core.User;
import core.UserAuth;
import core.Website;
import core.WebsiteAuth;

public class ClientConnectThread implements Runnable{
	private WebServer webServer;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private boolean run;
	
	public ClientConnectThread(WebServer webServer, Socket socket) {
		super();
		this.socket = socket;
		this.webServer = webServer;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream()); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		run = true;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("connected");
		while(run) {
			DataPackage data = getData();
			int key = data.getKeyNum();
			System.out.println("key: "+key);
			switch (key) {
			case 0: {
				exit();
				run = false;
				break;
			}
			case 1: {
//				Register
				UserAuth userAuth = data.getUserAuth();
				DataPackage dataPackage = new DataPackage(key);
				dataPackage.setStatus(this.webServer.register(userAuth));
				dataPackage.setUserAuth(userAuth);
				sendData(dataPackage);
				break;
			}
			case 2: {
//				Login
				UserAuth userLogin = data.getUserAuth();
				DataPackage dataPackage = new DataPackage(key);
				
				if(this.webServer.login(userLogin)) {
					dataPackage.setStatus(true);
					User user = this.webServer.database.getUser(userLogin.getEmail());
					dataPackage.setUser(user);
				} else {
					dataPackage.setStatus(false);
				}
				
				System.out.println(dataPackage.getKeyNum()+" > "+dataPackage.getStatus());
				sendData(dataPackage);
				break;
			}
			case 3: {
//				get List Website
				User user = data.getUser();
				List<Website> list = this.webServer.database.getWebsitesbyUser(user.getId());
				DataPackage dataPackage = new DataPackage(key);
				dataPackage.setWebsites(list);
				sendData(dataPackage);
				break;
			}
			case 4: {
//				create new Website
				WebsiteAuth websiteAuth = data.getWebsiteAuth();
				User user = data.getUser();
				try {
					DataPackage dataPackage = new DataPackage(key);
					dataPackage.setStatus(this.webServer.createWebsite(user, websiteAuth));
					if(dataPackage.getStatus()) {
						Website website = this.webServer.database.getWebsitebyName(websiteAuth.getName());
						dataPackage.setWebsite(website);
					}
					
					sendData(dataPackage);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case 5:
			{
//				Receiver File
				FileEvent fileEvent = data.getFileEvent();
				this.webServer.downloadFiles(fileEvent);
				if (fileEvent.isLast()) {
					DataPackage dataPackage = new DataPackage(key);
					dataPackage.setStatus(true);
					sendData(dataPackage);
				}
				
				break;
			}
			default:
				break;
			}
		}
	}
	
//	Get data
	private DataPackage getData(){
		DataPackage data = null;
		try {
			data = (DataPackage) this.ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
//	Send Data
	public void sendData(DataPackage data){
		try {
			this.oos.writeObject(data);
			this.oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	Exit
	private void exit(){
		try {
			this.oos.close();
			this.ois.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

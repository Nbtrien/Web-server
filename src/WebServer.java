import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import core.FileEvent;
import core.PasswordHash;
import core.User;
import core.UserAuth;
import core.WebsiteAuth;
import database.ConnectDB;
import database.Database;

public class WebServer {
	private static int SERVER_PORT1;
	private static int SERVER_PORT2 = 8003;
	private static ServerSocket serverSocket, serverSocket2;
	
	private static ConnectDB connDB;
	public Database database;
	
	public WebServer() throws IOException {
		
		Settings set = new Settings();
		set.readConfFile("C:\\Users\\ASUS\\eclipse-workspace\\WebServer\\conf\\config.txt");
		
		SERVER_PORT1 = set.getServerPort();
		System.out.println(SERVER_PORT1);
		 
		Thread serThread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					serverSocket = new ServerSocket(SERVER_PORT1);
					
					while(true) {
			            Socket connectionSocket = serverSocket.accept();
			            Thread connectionThread = new Thread(new WebConnectionWorkerThread(WebServer.this, connectionSocket));
			            connectionThread.start();
			        }
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread serThread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					serverSocket2 = new ServerSocket(SERVER_PORT2);
					while(true) {
						Socket cientConnection = serverSocket2.accept();
			            Thread clientThread = new Thread(new ClientConnectThread(WebServer.this ,cientConnection));
			            clientThread.start();
			            Thread.sleep(5000);
			        }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		serThread1.start();
		serThread2.start();
		
		connDB = new ConnectDB();
		database = new Database(connDB);
	}
	
	public boolean register(UserAuth userRegister){
		boolean result = false;
		try {
			if (database.checkEmailRegister(userRegister.getEmail())) {
				PasswordHash passwordHash = new PasswordHash(userRegister.getPassword());
				String newPasString = passwordHash.toHexString();
				if (database.insertNewUser(userRegister.getName(), userRegister.getEmail(), newPasString)) {
					result = true;
					File theDir = new File("f:/Web Server/"+userRegister.getEmail());
				   if (theDir.mkdirs()) {
					   String directory = theDir.getAbsolutePath().replace('\\', '/');
					   if(database.insertNewDirectory(directory)) {
						   System.out.println("Succesful");
						   int direc_id = database.getDirectorybyName(directory);
						   if (direc_id != 0) {
							   int user_id = database.getUser(userRegister.getEmail()).getId();
							   if(database.updateUser(direc_id, user_id)) {
								   System.out.println(database.updateUser(direc_id, user_id));
							   }else {
								   System.out.println("fail upda user");
							   }
						   }else {
							   System.out.println("fail get direc");
						   }
					   } else {
						   System.out.println("fail insert direc");
					   }
				   } else {
					   System.out.println("fail");
				   }
				} else {
					System.out.println("register fail");
				}
			} else {
				System.out.println("register fail");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}
	
	public boolean login(UserAuth userLogin) {
		boolean result = false;
		String email = userLogin.getEmail();
		PasswordHash passwordHash = new PasswordHash(userLogin.getPassword());
		try {
			String password = passwordHash.toHexString();
			if (database.checkUser(email, password)) {
				result = true; 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean createWebsite(User user, WebsiteAuth websiteAuth) throws NoSuchAlgorithmException {
		if (this.database.checkDomainName(websiteAuth.getName())) {
			PasswordHash passwordHash = new PasswordHash(websiteAuth.getPassWord());
			if (this.database.insertNewWebsite(user.getId(), websiteAuth.getName(), passwordHash.toHexString())) {
//				String direc = this.
				String userDirec = this.database.getDirectorybyId(user.getDirectory_id());
				System.out.println(userDirec);
				String webDirec = userDirec+"/"+websiteAuth.getName();
				File theDir = new File(webDirec);
				if (theDir.mkdirs()) {
					String directory = theDir.getAbsolutePath().replace('\\', '/');
					if(this.database.insertNewDirectory(webDirec)) {
						int direc_id = database.getDirectorybyName(webDirec);
						   if (direc_id != 0) {
							   int website_id = database.getIDWebsitebyName(websiteAuth.getName());
							   if(database.updateWebsite(direc_id, website_id)) {
								   System.out.println("Ok");
							   }else {
								   System.out.println("fail upda user");
							   }
						   }else {
							   System.out.println("fail get direc");
						   }
					}
				}	
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void downloadFiles(FileEvent fileEvent) {
		try {			
			if (fileEvent.isDirec()) {
				
			} else {
				if (fileEvent.getStatus().equalsIgnoreCase("Error")) {
					System.out.println("Error occurred ..with file" + fileEvent.getFilename() + "at sending end ..");
				}
				String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
				if (!new File(fileEvent.getDestinationDirectory()).exists()) {
					new File(fileEvent.getDestinationDirectory()).mkdirs();
				}
				File dstFile = new File(outputFile);
				FileOutputStream fileOutputStream = new FileOutputStream(dstFile);
				fileOutputStream.write(fileEvent.getFileData());
				fileOutputStream.flush();
				fileOutputStream.close();
				System.out.println("Output file : " + outputFile + " is successfully saved ");
				if (fileEvent.isLast()) {
					System.out.println("DONE");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException  {
		new WebServer();
	}

}

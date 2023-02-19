package client;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URI;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.DataPackage;
import core.FileEvent;
import core.User;
import core.UserAuth;
import core.Website;
import core.WebsiteAuth;

public class Client implements ActionListener, DataProcessing{
	private static int PORT = 8003;
	private static Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private DataStream dataStream;
	
	private User user;
	private Login login;
	private Register register;
	private ListSide listSide;
	
	private JButton jBLogin, jBSignUp;
	private JTextField jTEmail;
	private JPasswordField jPassword;
	
	private JTextField jTNameRegis, jTEmailRegis, jTEmailDomain;
	private JPasswordField jPasswordRegis;
	private JButton jBRegister;
	
	private JButton jBNewSite;
	
	private NewSite newSite = null;
	private JTextField jTDomain;
	private JPasswordField jPasswordWeb;
	private JButton jBSubmit, jBUpload;
	
	private int fileCount = 0;
	private FileEvent fileEvent = null;
	private JTextField jTDirectPath;
	private JFileChooser chooser;
	private String choosertitle;
	
	private Website website;
	private int directLength = 0;
	
	public Client() {
		try {
			socket = new Socket("localhost", PORT);
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			dataStream = new DataStream(this, ois);
			System.out.println("connected");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jTEmail = new JTextField();
		jBLogin = new JButton("Login");
		jBLogin.addActionListener(this);
		jBSignUp = new JButton("Create new account");
		jBSignUp.addActionListener(this);
		jPassword = new JPasswordField();
		login = new Login(jBLogin, jBSignUp, jTEmail, jPassword);
		login.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				exit();
			}
		});
		login.setVisible(true);
		
//		Test Login
//		String email = "trien@gmail.com";
//		String pass = "1234";
//		UserAuth userAuth = new UserAuth("", email, pass);
//		Login(userAuth);
	}
	
	private void createFrameListSide() {
		jBNewSite = new JButton("Create New Site");
		jBNewSite.addActionListener(this);
		listSide = new ListSide(user.getEmail(), jBNewSite);
		listSide.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				exit();
			}
		});
		listSide.setVisible(true);
		DataPackage dataPackage = new DataPackage(3);
		dataPackage.setUser(this.user);
		sendData(dataPackage);
	}
	
//	Login key = 2
	private void Login(UserAuth userAuth) {
		DataPackage dataPackage = new DataPackage(2);
		dataPackage.setUserAuth(userAuth);
		sendData(dataPackage);
	}
	
//	Register key = 1
	private void Register(String name, String email, String passWord) {
		DataPackage dataPackage = new DataPackage(1);
		UserAuth userAuth = new UserAuth(name, email, passWord);
		dataPackage.setUserAuth(userAuth);
		sendData(dataPackage);
	}
	
//	Send data
	private void sendData(DataPackage data) {
		try {
//			data.setLocalPort(client.getLocalPort());
			oos.writeObject(data);
			oos.flush();
			System.out.println(data.toString());
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		} 
	}
	
//	Get Directory Location
	public void locateFiles(File srcDir, String fromDirectory, String toDirectory) {
//		File srcDir = new File(sourceDirectory);
		if (!srcDir.isDirectory()) {
			System.out.println("Source directory is not valid ..Exiting the client");
			System.exit(0);
		}
		File[] files = srcDir.listFiles();
		fileCount = files.length;
		System.out.println("lenght "+fileCount);
		if (fileCount == 0) 
		{
			System.out.println("Empty directory ..Exiting the client");
//			System.exit(0);
		}
		else {
			for (int i = fileCount-1; i >= 0; i--) 
			{ 
				System.out.println("Sending " + files[i].getAbsolutePath()+" =>"+i); 
				if (files[i].isDirectory()) {
					System.out.println(files[i].getName()+" folder");
					this.directLength -=1;
					locateFiles(files[i], files[i].getAbsolutePath(), toDirectory+files[i].getName()+"/");
				} else if (files[i].isFile()) {
					System.out.println(files[i].getAbsolutePath()+" file"); 
					this.directLength -=1;
					if (this.directLength == 0) {
						sendFile(files[i].getAbsolutePath(), fileCount - i - 1, false, fromDirectory, toDirectory, true); 
					} else {
						sendFile(files[i].getAbsolutePath(), fileCount - i - 1, false, fromDirectory, toDirectory, false);
					}
					
				}
			} 
		}
	}
//	Send directory to server
	public void sendFile(String fileName, int index, boolean isDirec, String fromDirectory, String toDirectory, boolean isLast) 
	{ 
		fileEvent = new FileEvent(); 
		fileEvent.setDestinationDirectory(toDirectory);
		fileEvent.setDirec(isDirec);
		fileEvent.setLast(isLast);
		File file = new File(fileName); 
		fileEvent.setFilename(file.getName()); 
		System.out.println(fileEvent.getFilename()+" "+index+" from "+fileEvent.getSourceDirectory()+" to "+fileEvent.getDestinationDirectory()+"\n");
		
		fileEvent.setRemainder(index); 
		DataInputStream diStream = null; 
		try { 
			diStream = new DataInputStream(new FileInputStream(file)); 
			long len = (int) file.length(); 
			byte[] fileBytes = new byte[(int) len]; 
			int read = 0; 
			int numRead = 0; 
			while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) 
			{
				read = read + numRead;
			}
			fileEvent.setFileData(fileBytes);
			fileEvent.setStatus("Success");
		} catch (Exception e) {
			e.printStackTrace();
			fileEvent.setStatus("Error");
		}
		
		DataPackage dataPackage = new DataPackage(5);
		dataPackage.setFileEvent(fileEvent);
		sendData(dataPackage);
		
	}
	
	public static boolean openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}
	
	private void exit() {
		try {
			DataPackage dataPackage = new DataPackage(0);
			sendData(dataPackage);
			oos.close();
			ois.close();
			socket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.exit(0);
	}
	
	public static void main(String[] args) {
		new Client();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jBSignUp) {
			jTNameRegis = new JTextField(); 
			jTEmailRegis = new JTextField();
			jTEmailDomain = new JTextField();
			jPasswordRegis = new JPasswordField();
			jBRegister = new JButton("Register");
			jBRegister.addActionListener(this);
			register = new Register(jTNameRegis, jTEmailRegis, jTEmailDomain, jPasswordRegis, jBRegister);
			register.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					exit();
				}
			});
			register.setVisible(true);
			login.setVisible(false);
		} else if (e.getSource() == jBLogin) {
			String email = jTEmail.getText();
			String pass = jPassword.getText();
			UserAuth userAuth = new UserAuth("", email, pass);
			Login(userAuth);
		} else if (e.getSource() == jBRegister) {
			if (!(jTNameRegis.getText().equals("")) && !(jTEmailRegis.getText().equals("")) && !(jPasswordRegis.getText().equals(""))) {
				Register(jTNameRegis.getText(), jTEmailRegis.getText()+jTEmailDomain.getText(), jPasswordRegis.getText());
				
			} else {
				JOptionPane.showMessageDialog(register, "Please Enter Your Infor");
			}
		} else if (e.getSource() == jBNewSite) {
			if (newSite == null || newSite.isVisible() == false) {
				jTDomain = new JTextField();
				jPasswordWeb = new JPasswordField();
				jBSubmit = new JButton("CREATE");
				jBSubmit.addActionListener(this);
				jBUpload = new JButton("UPLOAD");
				jBUpload.addActionListener(this);
				newSite = new NewSite(user.getEmail(), jTDomain, jPasswordWeb, jBSubmit, jBUpload);
				newSite.setVisible(true);
				System.out.println("creater new Side");
			}
		} else if (e.getSource() == jBSubmit) {
			System.out.println(jTDomain.getText()+"=> "+jPasswordWeb.getText());
			String name = jTDomain.getText();
			String passWeb = jPasswordWeb.getText();
			
			WebsiteAuth websiteAuth = new WebsiteAuth(name, passWeb);
			
			DataPackage dataPackage = new DataPackage(4);
			dataPackage.setWebsiteAuth(websiteAuth);
			dataPackage.setUser(user);
			sendData(dataPackage);
		}
		
		else if (e.getSource() == jBUpload) {
			System.out.println("upload");
			String path = newSite.getPath();
			System.out.println(path);
			System.out.println("length"+ newSite.getSum());
			directLength = newSite.getSum();
			File srcDir = new File(path);
			String destinationDirectory = website.getDirectory()+"/";
			locateFiles(srcDir, path, destinationDirectory);
		}
	}

	@Override
	public void getData(DataPackage dataReceiver) {
		// TODO Auto-generated method stub
		int key = dataReceiver.getKeyNum();
		System.out.println("key: "+key);
		if (key == 1) {
			boolean status = dataReceiver.getStatus();
			System.out.println(status);
			if (status) {
				UserAuth userAuth = dataReceiver.getUserAuth();
				int output = JOptionPane.showConfirmDialog(register, "Login now", null, JOptionPane.YES_NO_OPTION);
                if (output == JOptionPane.YES_OPTION) {
                	Login(userAuth);
                	register.setVisible(false);
                } else if (output == JOptionPane.NO_OPTION) {
                	register.setVisible(false);
                	login.setVisible(true);
                }
				
			}
		} else if (key == 2) {
			boolean status = dataReceiver.getStatus();
			System.out.println(status);
			if (status) {
				this.user = dataReceiver.getUser();
				System.out.println(user.toString());
//				JOptionPane.showMessageDialog(login, "Login Succseful");
				login.setVisible(false);
				createFrameListSide();
			} else {
				JOptionPane.showMessageDialog(login, "Email or password incorrect");
			}
		} else if (key == 3) {
			List<Website> list = dataReceiver.getWebsites();
			System.out.println("web size: "+list.size());
			this.listSide.setListSide(list);
 		} else if (key == 4) {
			boolean status = dataReceiver.getStatus();
			System.out.println(status);
			if (status) {
				website = dataReceiver.getWebsite();
				newSite.showFormUpload();
			} else {
				JOptionPane.showMessageDialog(newSite, "Create new site unsuccessful");
			}
		} else if (key == 5) {
			newSite.dispose();
			DataPackage dataPackage = new DataPackage(3);
			dataPackage.setUser(this.user);
			sendData(dataPackage);
			JOptionPane.showMessageDialog(listSide, "Upload Succseful");
		}
	}
}

package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.Directory;
import core.User;
import core.Website;


public class Database {
	private ConnectDB conn;
	
	public Database(ConnectDB conn) {
		super();
		this.conn = conn;
	}
	
//	Check user Login
	public boolean checkUser(String email, String passWord) {
		boolean resutl = false;
		ResultSet rst = conn.gettable("SELECT * FROM users WHERE email = "+"'"+email+"' AND password = "+"'"+passWord+"'");
		System.out.println("SELECT * FROM users WHERE email = "+"'"+email+"' AND password = "+"'"+passWord+"'");
		try {
			if (rst.isBeforeFirst()) {
				resutl = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resutl;
	}
	
//	Check Email Register
	public boolean checkEmailRegister(String email) throws SQLException {
		boolean check = true;
		ResultSet rst = conn.gettable("SELECT * FROM users");
		if (!rst.next() == false) {
			ResultSetMetaData rsm = rst.getMetaData();
			int count_column = rsm.getColumnCount();
			do {
				for(int i=1; i<=count_column; i++) {
					if(email.equals(rst.getObject("email").toString())) {
						check = false;
					}
				}
			} while (rst.next());
		}
		
		return check;
	}
	
//	Check Domain Name
	public boolean checkDomainName(String name) {
		boolean check = true;
		ResultSet rst = conn.gettable("SELECT * FROM websites WHERE name = "+"'"+name+"'");
		System.out.println("SELECT * FROM websites WHERE name = "+"'"+name+"'");
		try {
			if (rst.isBeforeFirst()) {
				check = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}

//	Get User by Email
	public User getUser(String email) {
		User user = new User();
		ResultSet rst = conn.gettable("SELECT * FROM users WHERE email = "+"'"+email+"'");
		System.out.println("SELECT * FROM users WHERE email = "+"'"+email+"'");
		try {
		while (rst.next())
		{
				user.setId(rst.getInt("id"));
				user.setEmail(rst.getObject("email").toString());
				user.setUserName(rst.getObject("name").toString());
				user.setDirectory_id(rst.getInt("directory_id"));
				
				Directory directory = new Directory();
				String path = getDirectorybyId(user.getDirectory_id());
				directory.setPath(path);
				user.setDirectory(directory);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
//	Get User by Id
	public User getUserbyId(int id) {
		User user = new User();
		ResultSet rst = conn.gettable("SELECT * FROM users WHERE id = "+"'"+id+"'");
		System.out.println("SELECT * FROM users WHERE id = "+"'"+id+"'");
		try {
		while (rst.next())
		{
				user.setId(rst.getInt("id"));
				user.setEmail(rst.getObject("email").toString());
				user.setUserName(rst.getObject("name").toString());
				user.setDirectory_id(rst.getInt("directory_id"));
				
				Directory directory = new Directory();
				String path = getDirectorybyId(user.getDirectory_id());
				directory.setPath(path);
				user.setDirectory(directory);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
//	get list website by user
	public List<Website> getWebsitesbyUser(int user_id) {
		List<Website> list = new ArrayList<>();
		ResultSet rst = conn.gettable("SELECT * FROM websites WHERE user_id = "+"'"+user_id+"'");
		try {
			if (rst.next()) {
				do {
					Website website = new Website(rst.getInt("id"), rst.getObject("name").toString(), rst.getObject("url").toString());
					list.add(website);
				} while (rst.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
//	get website id by name
	public int getIDWebsitebyName(String name) {
		int id = 0;
		ResultSet rst = conn.gettable("SELECT * FROM websites WHERE name = "+"'"+name+"'");
		System.out.println("SELECT * FROM websites WHERE name = "+"'"+name+"'");
		try {
		while (rst.next())
		{
				id = rst.getInt("id");
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
//	get website by name
	public Website getWebsitebyName(String name) {
		Website website = new Website(0, name, name);
		ResultSet rst = conn.gettable("SELECT * FROM websites WHERE name = "+"'"+name+"'");
		System.out.println("SELECT * FROM websites WHERE name = "+"'"+name+"'");
		try {
		while (rst.next())
		{
			website.setId(rst.getInt("id"));
			website.setDomain(rst.getObject("name").toString());
			website.setUrl(rst.getObject("url").toString());
			website.setDirectory_id(rst.getInt("directory_id"));
			String direct = getDirectorybyId(website.getDirectory_id());
			website.setDirectory(direct);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return website;
	}
	
//	get all web site
	public List<Website> getAllWebsite(){
		List<Website> websites = new ArrayList<>();
		String query = "SELECT * FROM websites";
		ResultSet rst = conn.gettable(query);
		System.out.println(query);
		try {
			while (rst.next())
			{
				Website website = new Website();
				website.setId(rst.getInt("id"));
				website.setName(rst.getObject("name").toString());
				website.setUrl(rst.getObject("url").toString());
				websites.add(website);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return websites;
	}
	
	
//	Insert new User
	public boolean insertNewUser(String userName, String email, String password) {
		System.out.println("INSERT INTO `users`(`name`, `email`, `password`) VALUES('"+userName+"','"+email+"','"+password+"'");
		int n = conn.change("INSERT INTO `users`(`name`, `email`, `password`) VALUES('"+userName+"','"+email+"','"+password+"')");
		if (n == 1) {
			return true;
		} else {
			return false;
		}
	}
	
//	Update User
	public boolean updateUser(int directory_id, int user_id) {
		System.out.println("UPDATE `users` SET `directory_id`='"+directory_id+"' WHERE id ='"+user_id+"'");
		int n = conn.change("UPDATE `users` SET `directory_id`='"+directory_id+"' WHERE id ='"+user_id+"'");
		if (n == 1) {
			return true;
		} else {
			return false;
		}
	}
	
//	Insert New Directory
	public boolean insertNewDirectory(String directory) {
		System.out.println("INSERT INTO `directories`(`directory`) VALUES('"+directory+"')");
		int n = conn.change("INSERT INTO `directories`(`directory`) VALUES('"+directory+"')");
		if (n == 1) {
			return true;
		} else {
			return false;
		}
	}
	
//	Insert new Website
	public boolean insertNewWebsite(int user_id, String name, String password) {
		String url = "http://localhost:8080/"+name;
		System.out.println("INSERT INTO `websites`(`name`,`url`,`user_id`,`password`) VALUES('"+name+"','"+url+"','"+user_id+"','"+password+"')");
		int n = conn.change("INSERT INTO `websites`(`name`,`url`,`user_id`,`password`) VALUES('"+name+"','"+url+"','"+user_id+"','"+password+"')");
		if (n == 1) {
			return true;
		} else {
			return false;
		}
	}
	
//	Update website
	public boolean updateWebsite(int directory_id, int website_id) {
		System.out.println("UPDATE `websites` SET `directory_id`='"+directory_id+"' WHERE id ='"+website_id+"'");
		int n = conn.change("UPDATE `websites` SET `directory_id`='"+directory_id+"' WHERE id ='"+website_id+"'");
		if (n == 1) {
			return true;
		} else {
			return false;
		}
	}
	
//	get directory by name
	public int getDirectorybyName(String directory) {
		ResultSet rst = conn.gettable("SELECT * FROM directories WHERE directory = "+"'"+directory+"'");
		int i = 0;
		try {
			if (rst.next())
			i = rst.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
//	get directory by id
	public String getDirectorybyId(int id) {
		ResultSet rst = conn.gettable("SELECT * FROM directories WHERE id = "+"'"+id+"'");
		System.out.println("SELECT * FROM directories WHERE id = "+"'"+id+"'");
		String direct = "";
		try {
			if (rst.next())
				direct = rst.getObject("directory").toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return direct;
	}

}

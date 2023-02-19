package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {
	Connection connDB;
	Statement sttm;
	public void ConnectDB()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/webserver","root","");  
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int change(String sql) 
	{
		int n=0;
		try {
			ConnectDB();
			sttm = connDB.createStatement();
			n=sttm.executeUpdate(sql);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return n;
	}
	
	public ResultSet gettable(String sql) 
	{
		ResultSet n = null;
		try {
			ConnectDB();
			sttm = connDB.createStatement();
			n = sttm.executeQuery(sql);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return n;
	}
}

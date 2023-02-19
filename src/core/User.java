package core;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String userName;
	private String email;
	private int directory_id;
	private Directory directory;
	
	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDirectory_id() {
		return directory_id;
	}
	public void setDirectory_id(int directory_id) {
		this.directory_id = directory_id;
	}
	
	
	public Directory getDirectory() {
		return directory;
	}
	public void setDirectory(Directory directory) {
		this.directory = directory;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", directory_id=" + directory_id
				+ ", directory=" + directory + "]";
	}
	
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", userName=" + userName + ", email=" + email + "]";
//	}
	
	
}

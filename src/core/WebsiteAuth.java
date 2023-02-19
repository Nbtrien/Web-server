package core;

import java.io.Serializable;

public class WebsiteAuth implements Serializable{
	private String name;
	private String passWord;
	public WebsiteAuth(String name, String passWord) {
		super();
		this.name = name;
		this.passWord = passWord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}

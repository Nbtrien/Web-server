package core;
import java.io.Serializable;
import java.util.List;

public class DataPackage implements Serializable{
	private int keyNum;
	private UserAuth userAuth;
	private User user;
	private boolean status;
	private Website website;
	private List<Website> websites;
	private WebsiteAuth websiteAuth;
	private FileEvent fileEvent;
	
	public DataPackage(int keyNum) {
		super();
		this.keyNum = keyNum;
	}
	
	public int getKeyNum() {
		return keyNum;
	}

	public UserAuth getUserAuth() {
		return userAuth;
	}

	public void setUserAuth(UserAuth userAuth) {
		this.userAuth = userAuth;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	public List<Website> getWebsites() {
		return websites;
	}

	public void setWebsites(List<Website> websites) {
		this.websites = websites;
	}

	public WebsiteAuth getWebsiteAuth() {
		return websiteAuth;
	}

	public void setWebsiteAuth(WebsiteAuth websiteAuth) {
		this.websiteAuth = websiteAuth;
	}

	public FileEvent getFileEvent() {
		return fileEvent;
	}

	public void setFileEvent(FileEvent fileEvent) {
		this.fileEvent = fileEvent;
	}
	
	
	
	
	
}

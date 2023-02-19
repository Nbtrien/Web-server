package core;

import java.io.Serializable;

public class Website implements Serializable{
	private int id;
	private String name;
	private String domain;
	private String url;
	private int directory_id;
	private String directory;
	
	public Website() {
	}
	
	public Website(int id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public int getDirectory_id() {
		return directory_id;
	}
	public void setDirectory_id(int directory_id) {
		this.directory_id = directory_id;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	@Override
	public String toString() {
		return "Website [id=" + id + ", name=" + name + ", domain=" + domain + ", url=" + url + ", directory_id="
				+ directory_id + ", directory=" + directory + "]";
	}
	
	
	
	
}

package core;

import java.io.Serializable;

public class Directory implements Serializable{
	private int id;
	private String path;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "Directory [id=" + id + ", path=" + path + "]";
	}
	

	
	
}

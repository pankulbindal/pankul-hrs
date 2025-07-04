package com.pankul.response;

public class StatusDescription {
	
	private long status;
	private String description;
	
	
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "LoginResponse [status=" + status + ", description=" + description + "]";
	}
	  
}

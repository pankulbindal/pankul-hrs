package com.pankul.response;

import com.pankul.models.UsersModel;

public class UserResponse {

	private UsersModel userDetails;
	private StatusDescription status;

	public UsersModel getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UsersModel userDetails) {
		this.userDetails = userDetails;
	}

	public StatusDescription getStatus() {
		return status;
	}

	public void setStatus(StatusDescription status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserResponse [userDetails=" + userDetails + ", status=" + status + "]";
	}

}

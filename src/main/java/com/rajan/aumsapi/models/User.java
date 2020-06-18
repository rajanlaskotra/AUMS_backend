package com.rajan.aumsapi.models;

public class User {

	private int userID;
	private String userName;
	private String email;
	private String userLocation;
	

	public User() {
	}

	public User(String userName, String email, String userLocation) {
		super();
		this.userName = userName;
		this.email = email;
		this.userLocation = userLocation;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public String getUserLocation() {
		return userLocation;
	}


	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	
	
}

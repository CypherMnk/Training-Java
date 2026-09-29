package com.training.model;
 
public class User {
	
	private int userId;
	
	private String name;

	private String role;

	private String username;

	private String password;

	private String email;

	private String mobile;
	
	public User() {
		
	}
 
	public User(int userId, String name, String role, String username, String password,
			String email, String mobile) {
		super();
		this.userId = userId;
		this.name = name;
		this.role = role;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
	}
 
	public int getUserId() {
		return userId;
	}
 
	public void setUserId(int userId) {
		this.userId = userId;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public String getRole() {
		return role;
	}
 
	public void setRole(String role) {
		this.role = role;
	}
 
	public String getUsername() {
		return username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public String getMobile() {
		return mobile;
	}
 
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
 
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", role=" + role
				+ ", username=" + username + ", email=" + email + ", mobile=" + mobile + "]";
	}
	
	
 
}

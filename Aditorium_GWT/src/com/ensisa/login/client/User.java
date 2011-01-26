package com.ensisa.login.client;

import java.io.Serializable;

public class User implements Serializable{
  /**
  * Add this variable for serialization
  */
  private static final long serialVersionUID = 1L;
 
  private String role;
  public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

private String userName;
  private String password;
 
  public String getUserName() {
    return userName;
  }
 
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getPassword() {
 	return password;
  }
  public void setPassword(String password) {
	this.password = password;
 }
 
  public void setUser(String userName, String password)
  {
	  this.password=password;
	  this.userName=userName;
  }
 }
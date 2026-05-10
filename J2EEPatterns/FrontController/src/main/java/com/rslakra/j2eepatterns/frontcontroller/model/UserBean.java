package com.rslakra.j2eepatterns.frontcontroller.model;

/**
 * The interface to our model or a user
 */
public interface UserBean {
	// the username field
	public String getUsername();
	
	public void setUsername(String username);
	
	// the password field
	public String getPassword();
	
	public void setPassword(String password);
	
	// business methods to perform login
	public boolean doLogin();
	
	public boolean isLoggedIn();
	
}

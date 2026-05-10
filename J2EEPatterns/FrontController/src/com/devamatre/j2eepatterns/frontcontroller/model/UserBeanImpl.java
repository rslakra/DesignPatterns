package com.devamatre.j2eepatterns.frontcontroller.model;

/**
 * An implmentation of the mailing bean interface, using local
 * variables as storage. Real implementations should use a real
 * login mechanism.
 */
public class UserBeanImpl implements UserBean {
	/** the username */
	private String username;
	
	/** the password */
	private String password;
	
	/** whether the user is logged in or not */
	private boolean loggedIn;
	
	/**
	 * Get the user name
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Set the user name
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Get the password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Set the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Business method to actually perform login. This method just checks that
	 * the password exists and is longer than 3 characters long. Real versions
	 * should check with some kind of real mechanism to determing username and
	 * password validity.
	 */
	public boolean doLogin() {
		if (password == null || password.length() < 3) {
			setLoggedIn(false);
		} else {
			setLoggedIn(true);
		}
		
		return isLoggedIn();
	}
	
	/**
	 * Set whether we are currently logged in or not
	 */
	private void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	/**
	 * Determine if we are currently logged in or not
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}
}

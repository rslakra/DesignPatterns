package com.devamatre.patterns.s2w.model;

/**
 * A (very) simple implementation of the UserBean interface.  This simply
 * stores values in memory.  
 */
public class UserBeanImpl implements UserBean {
    /** the username */
    private String username;
    
    /** the password */
    private String password;
    
    /** the preferred language */
    private String language;
    
    /** whether or not the user is logged in */
    private boolean loggedIn = false;
    
    /**
     * Constructor.
     */
    public UserBeanImpl() {
    }
    
    /**
     * Business method to perform login
     */
    public boolean doLogin() {
        loggedIn = false;
       
        // this implementation just checks that the password is more
        // than three characters long.  A real implementation should
        // use some real method of verifying the username and password.
        if((username != null) && (username.length() > 0) &&
           (password != null) && (password.length() > 3)) {
            loggedIn = true;
        }
        
        return loggedIn;
    }
    
    /**
     * Get the user's preferred language 
     */
    public String getLanguage() {
        return language;
    }
    
    /**
     * Set the user's preferred language
     */
    public void setLanguage(String language) {
        this.language = language;
    }
    
    /**
     * Get the username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Set the username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /** 
     * Set the user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    /** 
     * Determine if the user is logged in
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}

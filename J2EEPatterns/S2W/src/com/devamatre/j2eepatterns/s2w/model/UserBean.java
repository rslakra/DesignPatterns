package com.devamatre.patterns.s2w.model;

/**
 * The itnerface to our model of a User
 */
public interface UserBean {
    // the username
    public String getUsername();
    public void setUsername(String username);
  
    // the password
    public void setPassword(String password);
    
    // the user's preferred language
    public String getLanguage();
    public void setLanguage(String language);
    
    // business method to perform login
    public boolean doLogin();
    
    // determine whether we are logged in or not
    public boolean isLoggedIn();
}

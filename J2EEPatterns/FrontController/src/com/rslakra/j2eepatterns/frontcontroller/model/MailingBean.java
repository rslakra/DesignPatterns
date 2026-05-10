package com.rslakra.j2eepatterns.frontcontroller.model;

/**
 * The interface to our model object, which stores information about
 * a single mailing list subscription
 */
public interface MailingBean {
	// first name
	public String getFirst();
	
	public void setFirst(String first);
	
	// last name
	public String getLast();
	
	public void setLast(String last);
	
	// email address
	public String getEmail();
	
	public void setEmail(String email);
	
	// business method
	public boolean doSubscribe();
	
	// subscription result
	public String getErrorString();
	
}

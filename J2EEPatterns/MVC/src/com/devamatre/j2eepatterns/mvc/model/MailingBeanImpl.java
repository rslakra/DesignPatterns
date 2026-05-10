package com.devamatre.j2eepatterns.mvc.model;

/**
 * An implmentation of the mailing bean interface, using local
 * variables as storage. Real implementations should use a real
 * persistence mechanism.
 */
public class MailingBeanImpl implements MailingBean {
	// first name
	private String first;
	
	// last name
	private String last;
	
	// email address
	private String email;
	
	// current error
	private String errorString;
	
	/**
	 * Create a new MailingBeanImpl
	 */
	public MailingBeanImpl() {
	}
	
	/**
	 * Get the first name
	 */
	public String getFirst() {
		return first;
	}
	
	/**
	 * Set the first name
	 */
	public void setFirst(String first) {
		this.first = first;
	}
	
	/**
	 * Get the last name
	 */
	public String getLast() {
		return last;
	}
	
	/**
	 * Set the last name
	 */
	public void setLast(String last) {
		this.last = last;
	}
	
	/**
	 * Get the email address
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Set the email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Process a subscription to the mailing list. The doesn't actually
	 * do anything other than verify the address has an '@' character in it.
	 * Real implementations would talk to the mailing list server here
	 */
	public boolean doSubscribe() {
		if (getEmail().indexOf('@') == -1) {
			errorString = "invalid email address";
			return false;
		} else {
			errorString = null;
			return true;
		}
	}
	
	/**
	 * Get the error string for the most recent attempt to subscribe.
	 */
	public String getErrorString() {
		return errorString;
	}
}

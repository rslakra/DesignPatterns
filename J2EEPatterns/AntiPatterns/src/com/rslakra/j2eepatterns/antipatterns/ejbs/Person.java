package com.rslakra.j2eepatterns.antipatterns.ejbs;

import java.io.Serializable;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@rslakra.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class Person implements Serializable {
	
	private String lastName;
	private String firstName;
	private String phoneNumber;
	
	public Person() {
	}
	
	public Person(String first, String last, String phone) {
		setFirstName(first);
		setLastName(last);
		setPhoneNumber(phone);
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}

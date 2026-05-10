package com.devamatre.j2eepatterns.antipatterns.ejbs;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public interface LocalLdapPerson extends javax.ejb.EJBLocalObject {
	
	public java.lang.String getFirstName();
	
	public java.lang.String getLastName();
	
	public java.lang.String getPhoneNumber();
	
}

package com.rslakra.j2eepatterns.antipatterns.ejbs;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@rslakra.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public interface EJBPerson extends javax.ejb.EJBObject {
	
	public java.lang.String getFirstName() throws java.rmi.RemoteException;
	
	public java.lang.String getLastName() throws java.rmi.RemoteException;
	
	public java.lang.String getPhoneNumber() throws java.rmi.RemoteException;
	
}

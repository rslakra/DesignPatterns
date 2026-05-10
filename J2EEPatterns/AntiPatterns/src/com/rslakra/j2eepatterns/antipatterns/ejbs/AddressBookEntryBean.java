package com.rslakra.j2eepatterns.antipatterns.ejbs;

import javax.ejb.CreateException;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@rslakra.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AddressBookEntryBean implements javax.ejb.EntityBean {
	
	private javax.ejb.EntityContext context;
	
	/**
	 * @see javax.ejb.EntityBean#setEntityContext(javax.ejb.EntityContext)
	 */
	public void setEntityContext(javax.ejb.EntityContext aContext) {
		context = aContext;
	}
	
	/**
	 * @see javax.ejb.EntityBean#ejbActivate()
	 */
	public void ejbActivate() {
		
	}
	
	/**
	 * @see javax.ejb.EntityBean#ejbPassivate()
	 */
	public void ejbPassivate() {
		
	}
	
	/**
	 * @see javax.ejb.EntityBean#ejbRemove()
	 */
	public void ejbRemove() {
		
	}
	
	/**
	 * @see javax.ejb.EntityBean#unsetEntityContext()
	 */
	public void unsetEntityContext() {
		context = null;
	}
	
	/**
	 * @see javax.ejb.EntityBean#ejbLoad()
	 */
	public void ejbLoad() {
		
	}
	
	/**
	 * @see javax.ejb.EntityBean#ejbStore()
	 */
	public void ejbStore() {
		
	}
	
	public abstract int getEntryId();
	
	public abstract void setEntryId(int entryId);
	
	public abstract java.lang.String getOwner();
	
	public abstract void setOwner(java.lang.String owner);
	
	public abstract java.lang.String getFirstName();
	
	public abstract void setFirstName(java.lang.String firstName);
	
	public abstract java.lang.String getLastName();
	
	public abstract void setLastName(java.lang.String lastName);
	
	public abstract java.lang.String getPhoneNumber();
	
	public abstract void setPhoneNumber(java.lang.String phoneNumber);
	
	public AddressBookEntryKey ejbCreate(int entryId, String owner, String firstName, String lastName, String phoneNumber) throws CreateException {
		setEntryId(entryId);
		setOwner(owner);
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
		
		return new AddressBookEntryKey(entryId);
	}
	
	public void ejbPostCreate(int entryId, java.lang.String owner, java.lang.String firstName, java.lang.String lastName, java.lang.String phoneNumber) throws javax.ejb.CreateException {
	}
	
}

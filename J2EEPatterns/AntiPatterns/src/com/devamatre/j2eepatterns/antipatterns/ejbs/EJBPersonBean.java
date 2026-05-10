package com.devamatre.j2eepatterns.antipatterns.ejbs;

import javax.ejb.CreateException;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class EJBPersonBean implements javax.ejb.EntityBean {
	
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
	
	public abstract int getPersonId();
	
	public abstract void setPersonId(int personId);
	
	public abstract java.lang.String getFirstName();
	
	public abstract void setFirstName(java.lang.String firstName);
	
	public abstract java.lang.String getLastName();
	
	public abstract void setLastName(java.lang.String lastName);
	
	public abstract java.lang.String getPhoneNumber();
	
	public abstract void setPhoneNumber(java.lang.String phoneNumber);
	
	public EJBPersonKey ejbCreate(int personId, String firstName, String lastName, String phoneNumber) throws CreateException {
		setPersonId(personId);
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
		return new EJBPersonKey(personId);
	}
	
	public void ejbPostCreate(int personId, String firstName, String lastName, String phoneNumber) throws CreateException {
	}
	
}


package com.oreilly.patterns.chapter9;

import javax.ejb.*;


public interface LocalCustomerHome extends EJBLocalHome {
    
    public LocalCustomer findByPrimaryKey(CustomerKey aKey)
    throws FinderException;
    
    public LocalCustomer findByCustomerId(int customerId) 
    throws FinderException;
}

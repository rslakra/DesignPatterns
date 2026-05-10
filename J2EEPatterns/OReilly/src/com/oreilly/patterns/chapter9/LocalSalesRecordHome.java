
package com.oreilly.patterns.chapter9;

import javax.ejb.*;

public interface LocalSalesRecordHome extends EJBLocalHome {
    
    public LocalSalesRecord findByPrimaryKey(SalesRecordKey aKey)
    throws FinderException;
    
    public LocalSalesRecord createRecord(LocalItem[] items, 
					 LocalCustomer customer)
    throws CreateException;
}

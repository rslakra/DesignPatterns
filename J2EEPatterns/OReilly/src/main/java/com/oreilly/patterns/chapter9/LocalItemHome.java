
package com.oreilly.patterns.chapter9;

import javax.ejb.*;

public interface LocalItemHome extends EJBLocalHome {
    
    public LocalItem findByPrimaryKey(ItemKey aKey)
    throws FinderException;
    
    public LocalItem findByItemNumber(int itemNumber) 
    throws FinderException;
    
}

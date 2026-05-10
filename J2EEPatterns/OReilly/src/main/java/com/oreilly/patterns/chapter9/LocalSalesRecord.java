
package com.oreilly.patterns.chapter9;

import javax.ejb.*;

public interface LocalSalesRecord extends EJBLocalObject {
    
    public float getTotalPrice();
    
}


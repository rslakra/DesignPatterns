
package com.oreilly.patterns.chapter9;

import javax.ejb.*;

public interface LocalCustomer extends EJBLocalObject {
    
    public String getFirstName();
    
    public String getLastName();
    
}


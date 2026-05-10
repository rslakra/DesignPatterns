
package com.oreilly.patterns.chapter9;

import javax.ejb.*;

public interface LocalItem extends EJBLocalObject {
    
    public int getItemNumber();
    
    public String getDescription();
    
    public float getPrice();
    
}


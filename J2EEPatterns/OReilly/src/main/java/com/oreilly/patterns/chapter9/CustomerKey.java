
package com.oreilly.patterns.chapter9;

import java.io.Serializable;

public final class CustomerKey implements Serializable {
    
    public int customerId;
           
    public boolean equals(Object otherOb) {
        
        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof CustomerKey)) {
            return false;
        }
        
	CustomerKey other = (CustomerKey) otherOb;
        
	return (customerId == other.customerId);
    }
    
    public int hashCode() {
        return customerId;
    }
    
}

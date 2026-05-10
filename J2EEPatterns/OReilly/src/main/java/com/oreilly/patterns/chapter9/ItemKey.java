
package com.oreilly.patterns.chapter9;

import java.io.Serializable;

public final class ItemKey implements Serializable {
    
    public int itemNumber;
    
    public boolean equals(Object otherOb) {
        
        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof ItemKey)) {
            return false;
        }

        ItemKey other = (ItemKey) otherOb;
        
	return (itemNumber == other.itemNumber);
    }
    
    public int hashCode() {
        return itemNumber;
    }    
}

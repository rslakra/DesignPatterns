// Example 10-4: Lockable interface for the Lockable Object pattern

package com.oreilly.patterns.chapter10;

public interface Lockable {
    public boolean isLocked();
    public void lock(String username) throws LockingException;
    public void unlock(String username) throws LockingException;
}  

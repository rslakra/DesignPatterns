// Example 10-5: Customer object with locking interface

package com.oreilly.patterns.chapter10;

public class Customer implements Lockable {

    private String lockingUser = null;
    private Object lockSynchronizer = new Object();

    public void lock(String username) throws LockingException {
        if (username == null) throw new LockingException("No User Provided.");
        synchronized(lockSynchronizer) {
            if(lockingUser == null)
                lockingUser = username;
            else if ((lockingUser != null) && (!lockingUser.equals(username)))
                throw new LockingException("Resource already locked");
        }
    }

    public void unlock(String username) throws LockingException {
        if((lockingUser != null) && (lockingUser.equals(username)))
            lockingUser = null;
        else if (lockingUser != null)
            throw new LockingException("You do not hold the lock.");
    }

    public boolean isLocked() {
        return (lockingUser != null);
    }

    // Customer getter/setter methods go here
}
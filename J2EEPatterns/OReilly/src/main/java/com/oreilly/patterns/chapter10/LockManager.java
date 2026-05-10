// Example 10-6: LockManager.java

package com.oreilly.patterns.chapter10;

import java.util.*;

public class LockManager {
  private HashMap locks;

  private static HashMap managers = new HashMap();

  /**
   * Get a named Lock Manager. The manager will be created if not found.
   */
  public static synchronized LockManager getLockManager(String managerName) {
    LockManager manager = (LockManager)managers.get(managerName);
    if(manager == null) {
        manager = new LockManager();
        managers.put(managerName, manager);
    }
    return manager;
  }

  /**
   * Create a new LockManager instance.
   */
  public LockManager() {
    locks = new HashMap();
  }

 /**
  * Request a lock from this LockManager instance.
  */
  public boolean requestLock(String username, Object lockable) {
    if(username == null)
      return false; // or raise exception
      
    synchronized(locks) {
      if(!locks.containsKey(lockable)) {
        locks.put(lockable, username);
        return true;
      }
      // Return true if this user already has a lock
      return (username.equals(locks.get(lockable)));
    }
  }

 /**
  * Release a Lockable object.
  */
  public Object releaseLock(Object lockable) {
    return locks.remove(lockable);
  }
}


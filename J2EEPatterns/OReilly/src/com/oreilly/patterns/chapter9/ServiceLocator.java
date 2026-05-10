// Example 9-6 - The ServiceLocator class

package com.oreilly.patterns.chapter9;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.EJBHome;
import javax.ejb.HomeHandle;
import javax.rmi.PortableRemoteObject;
import java.util.HashMap;
import java.rmi.RemoteException;

public class ServiceLocator {

 private static ServiceLocator instance;
 private HashMap cache;
 private Context context;
 
 // return the singelton service locator       
 public static ServiceLocator getInstance()
 throws ServiceLocatorException {
  if (instance == null)
   instance = new ServiceLocator();
  
  return instance;
 }
    
 // Creates a new instance of ServiceLocator
 private ServiceLocator() throws ServiceLocatorException {
  cache = new HashMap();
  
  // initialize the shared context object      
  try {
   context = new InitialContext();
  } catch(NamingException ne) {
   throw new ServiceLocatorException("Unable to create " + 
    "initial context", ne);
  }
 }
 
 // get an EJB from the cache or directory service   
 public EJBHome getRemoteEJB(String name, Class type)
 throws ServiceLocatorException {
  // see if it's in the cache
  if (cache.containsKey(name)) {
   // cache HomeHandle objects since they are maintained
   // by the container
   HomeHandle hh = (HomeHandle)cache.get(name);
            
   try {
    return hh.getEJBHome();
   } catch(RemoteException re) {
    // some kind of problem -- fall through to
    // relookup below
   }
  }
  
  // access to the shared context as well as modifications
  // to the HashMap must be synchronized.  Hopefully the
  // majority of cases are handled above     
  synchronized(this) {
   try {
    Object rRef = context.lookup(name);
    EJBHome eh = 
     (EJBHome)PortableRemoteObject.narrow(rRef, type);
    
    cache.put(name, eh.getHomeHandle());
    return eh;
   } catch(Exception ex) {
    throw new ServiceLocatorException("Unable to find EJB",
     ex);
   }
  }
 }
}

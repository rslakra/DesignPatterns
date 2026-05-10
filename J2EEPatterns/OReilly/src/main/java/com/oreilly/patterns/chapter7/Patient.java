// Example 7-1: Patient.java

package com.oreilly.patterns.chapter7;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface Patient extends EJBObject  {

  public String getFirstName() throws RemoteException;
  public void setFirstName(String firstName) throws RemoteException;
  
  public String getLastName() throws RemoteException;
  public void setLastName(String lastName) throws RemoteException;

  public Address getHomeAddress() throws RemoteException;
  public void setHomeAddress(Address addr) throws RemoteException;

  public Address getWorkAddress() throws RemoteException;
  public void setWorkAddress(Address addr) throws RemoteException;
}

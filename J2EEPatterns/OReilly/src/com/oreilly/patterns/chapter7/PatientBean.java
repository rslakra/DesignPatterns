// Example 7-3: PersonBean.java (Partial)

package com.oreilly.patterns.chapter7;

import javax.ejb.*;
import javax.naming.*;
import java.sql.*;
import java.rmi.RemoteException;
import javax.sql.*;


public class PatientBean implements EntityBean  {
  private EntityContext context;

  private Long pat_no = null;
  private String fname = null;
  private String lname = null;
  
  private Address workAddress = new Address();
  private Address homeAddress = new Address();
  
  public Long ejbCreate(Long newId) {
    pat_no = newId;
	homeAddress.addressType = "Home";
    workAddress.addressType = "Work";
    return newId;
  }

  public void ejbPostCreate(Long newId) {
  }

  public Address getHomeAddress() {
    return homeAddress;
  }

  public void setHomeAddress(Address addr) {
     setAddress(homeAddress, addr);
  }

  public Address getWorkAddress() {
    return workAddress;
  }

  public void setWorkAddress(Address addr) {
    setAddress(workAddress, addr);
  }

  private void setAddress(Address target, Address source) {
	target.address1 = source.address1;
	target.address2 = source.address2;
	target.city = source.city;
	target.province = source.province;
	target.postcode = source.postcode;
  }
  
  public Long ejbFindByPrimaryKey(Long primaryKey) throws FinderException {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      con = getConnection(); // local method for JNDI Lookup
      ps = con.prepareStatement("select pat_no from patient where pat_no = ?");
      ps.setLong(1, primaryKey.longValue());
      rs = ps.executeQuery();
      if(!rs.next())
        throw (new ObjectNotFoundException("Patient does not exist"));
    } catch (SQLException e) {
      throw new EJBException(e);
    } finally {
      try {
        if(rs != null) rs.close();
        if(ps != null) ps.close();
        if(con != null) con.close();
      } catch (SQLException e) {}
    }
    // We found it, so return it
    return primaryKey; 
  }

   public void ejbLoad() {

    Long load_pat_no = (Long)context.getPrimaryKey();
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      con = getConnection(); // local method for JNDI Lookup
      ps = con.prepareStatement("select * from patient where pat_no = ?");
      ps.setLong(1, load_pat_no.longValue());
      rs = ps.executeQuery();

      if(!rs.next())
        throw (new EJBException("Unable to load patient information"));
        
      pat_no = new Long(rs.getLong("pat_no"));
      fname = rs.getString("fname");
      lname= rs.getString("lname");
      ps.close();
      rs.close();

      ps = con.prepareStatement(
        "select * from patient_address where pat_no = ? and address_label in " +
        "('Home', 'Work')");
        
      ps.setLong(1, load_pat_no.longValue());
      rs = ps.executeQuery();
      // Load any work or home
      while(rs.next()) {
        String addrType = rs.getString("ADDRESS_LABEL");
        if("Home".equals(addrType)) {
          homeAddress.address1 = rs.getString("addr1");
          homeAddress.address2 = rs.getString("addr2");
          homeAddress.city = rs.getString("city");
          homeAddress.province = rs.getString("province");
          homeAddress.postcode = rs.getString("postcode");
        } else if ("Work".equals(addrType)) {
          workAddress.address1 = rs.getString("addr1");
          workAddress.address2 = rs.getString("addr2");
          workAddress.city = rs.getString("city");
          workAddress.province = rs.getString("province");
          workAddress.postcode = rs.getString("postcode");
        }
      }
      
      
    } catch (SQLException e) {
      throw new EJBException(e);
    } finally {
      try {
        if(rs != null) rs.close();
        if(ps != null) ps.close();
        if(con != null) con.close();
      } catch (SQLException e) {}
    }
  }


  private Connection getConnection() throws SQLException {
    try {
      Context jndiContext = new InitialContext();
      DataSource ds = (DataSource)jndiContext.lookup("java:comp/env/jdbc/PatientDS");
      return ds.getConnection();
    } catch (NamingException ne) {
        throw new SQLException (ne.getMessage());
    }
  }
    // Other required EJB Methods:

    public void setEntityContext(EntityContext entityContext) throws EJBException, RemoteException {
    }

    public void unsetEntityContext() throws EJBException, RemoteException {
    }

    public void ejbRemove() throws RemoveException, EJBException, RemoteException {
    }

    public void ejbActivate() throws EJBException, RemoteException {
    }

    public void ejbPassivate() throws EJBException, RemoteException {
    }

    public void ejbStore() throws EJBException, RemoteException {
    }
}

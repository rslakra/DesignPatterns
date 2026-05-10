// Example 8-2: PatientDatabaseDAO.java

package com.oreilly.patterns.chapter7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oreilly.patterns.chapter8.PatientDAO;

public class PatientDatabaseDAO implements PatientDAO  {

  public PatientDTO findPatient(long pat_no)  {
  
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    PatientDTO patient = null;
    
    try {
      con = getConnection(); // local method for JNDI Lookup
      ps = con.prepareStatement("select * from patient where pat_no = ?");
      ps.setLong(1, pat_no);
      rs = ps.executeQuery();

      if(!rs.next())
       return null;

      patient = new PatientDTO();    
      patient.pat_no = pat_no;
      patient.fname = rs.getString("fname");
      patient.lname= rs.getString("lname");
      ps.close();
      rs.close();
      
      ps = con.prepareStatement(
        "select * from patient_address where pat_no = ? and address_label in " +
        "('Home', 'Work')");
        
      ps.setLong(1, pat_no);
      rs = ps.executeQuery();
      // Load any work or home
      while(rs.next()) {
        String addrType = rs.getString("ADDRESS_LABEL");
        Address addr = new Address();
        addr.addressType = addrType;
        addr.address1 = rs.getString("addr1");
        addr.address2 = rs.getString("addr2");
        addr.city = rs.getString("city");
        addr.province = rs.getString("province");
        addr.postcode = rs.getString("postcode");
        patient.addresses.add(addr);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {//TODO
        if(rs != null) rs.close();
        if(ps != null) ps.close();
        if(con != null) con.close();
      } catch (SQLException e) {}
    }
    
    return patient;
  }

  public void savePatient(PatientDTO patient) {
    // Persistence code goes here
  } 
  
  public PatientDTO createPatient(PatientDTO patient) {
    // Creation code goes here
    return null;
  }
  
  private Connection getConnection() throws SQLException {
    try {
      Context jndiContext = new InitialContext();
      DataSource ds = (DataSource)jndiContext.lookup("java:comp/env/jdbc/DataChapterDS");
      return ds.getConnection();
    } catch (NamingException ne) {
        throw new SQLException (ne.getMessage());
    }
  }
}

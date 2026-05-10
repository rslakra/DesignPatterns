// Example 8-6: MemberTupleDAO.java

package com.oreilly.patterns.chapter8;

import java.util.*;
import java.lang.reflect.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class MemberTupleDAO  {

   public MemberDTO findMember(long member_no)  {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    MemberDTO member = new MemberDTO();
    member.setMemberNumber(member_no);
      
    try {
      con = getConnection();
      ps = con.prepareStatement("select fieldname, numerical, string " + 
        "from object_data where obj_pk = ?");
      ps.setLong(1, member_no);
      rs = ps.executeQuery();

      while(rs.next()) {
        String fieldName = rs.getString(1);
        String strVal = rs.getString(3);

        if(strVal != null)
          setVal(fieldName, member, strVal);
        else { 
          // We do this indirectly to make database typecasting more reliable
          long lngVal = rs.getLong(2);
          if(!rs.wasNull())
            setVal(fieldName, member, new Long(lngVal));
        }
      }

      rs.close();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      if(con != null)
        try { con.close(); } catch (SQLException e) {}
    }
    
    return member;
   }
   

  public void saveMember(MemberDTO member)  {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    long memberNo = member.getMemberNumber();
    if(memberNo < 1) 
      return;
      
    Class[] stringParam = new Class[] {String.class};
    Class[] longParam = new Class[] {Long.class};
    
    try {
      con = getConnection();
      ps = con.prepareStatement("delete from object_data where obj_pk = ?");
      ps.setLong(1, memberNo);
      ps.executeUpdate();
      ps.close();

      ps = con.prepareStatement("insert into object_data " +
        "(obj_pk, fieldname, numerical, string) values (?, ?, ?, ?)");
      ps.setLong(1, memberNo);

      Method[] methods = member.getClass().getMethods();
      for(int i=0; i < methods.length; i++) {
        String mName = methods[i].getName();
        if(mName.startsWith("get")) {
          try {
            if(methods[i].getReturnType() == String.class) {
              ps.setString(2, mName.substring(3));
              ps.setNull(3, Types.NUMERIC);
              ps.setString(4, (String)methods[i].invoke(member, new Object[] {}));
              ps.executeUpdate();
            } else if (methods[i].getReturnType() == Long.class) {
              ps.setString(2, mName.substring(3));
              ps.setObject(3, (Long)methods[i].invoke(member, new Object[] {}), 
                Types.NUMERIC);
              ps.setNull(4, Types.VARCHAR);
              ps.executeUpdate();
            } 
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }catch (InvocationTargetException e) {
            e.printStackTrace();
          }
        }
      }
      ps.close();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if(ps != null) 
        try { ps.close(); } catch (SQLException e) {}
      if(con != null)
        try { con.close(); } catch (SQLException e) {}
    }
  }

  /**
   * Set a value on the target object, by searching for a set<fieldName> method
   * which takes a parameter of the same type as the "param" parameter.
   */
  private void setVal(String fieldName, Object target, Object param) {
    
    try { 
      Class targetClass = target.getClass();
      Method setter = targetClass.getMethod("set" + fieldName, 
                                            new Class[] { param.getClass() });
      setter.invoke(target, new Object[] { param });
    } catch (NoSuchMethodException e) {
      // Ignore it - it must not be in the target
    } catch (InvocationTargetException e) {
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }
  
   private Connection getConnection() throws SQLException {
    try {
      Context jndiContext = new InitialContext();
      DataSource ds = (DataSource)
        jndiContext.lookup("java:comp/env/jdbc/DataChapterDS");
      return ds.getConnection();
    } catch (NamingException ne) {
        throw new SQLException (ne.getMessage());
    }
  }

  public static void main(String [] args) {

    MemberTupleDAO mtd = new MemberTupleDAO();
    MemberDTO member = mtd.findMember(1);
    System.out.println(member.getFirstname() + " " + member.getLastname());
    System.out.println(member.getFreePasses());
    System.out.println(member.getCity());
    System.out.println(member.getAddress1());

    member.setMemberNumber(4);
    member.setFirstname("Reginald");
    mtd.saveMember(member);

    member = mtd.findMember(4);
    // Will display "Reginald" and the last name from the original member
	System.out.println(member.getFirstname() + " " + member.getLastname());
  }
}

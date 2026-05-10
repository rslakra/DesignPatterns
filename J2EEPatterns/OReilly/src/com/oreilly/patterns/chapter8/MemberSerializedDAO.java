// Example 8-5: MemberSerializableDAO.java

package com.oreilly.patterns.chapter8;

import java.io.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class MemberSerializedDAO  {

  public MemberSerializedDAO() {
  }


  public MemberDTO findMember(long member_no)  {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    MemberDTO member = null;
    
    try {
      con = getConnection();
      ps = con.prepareStatement(
        "select object_data from members where member_no = ?"); 
      ps.setLong(1, member_no);
      rs = ps.executeQuery();
      if(rs.next()) {
        ObjectInputStream ois = new ObjectInputStream(rs.getBinaryStream(1));
        member = (MemberDTO)ois.readObject();
        ois.close();
      }
      rs.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (ps != null)
        try { ps.close(); } catch (SQLException e) {}
      if (con != null)
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
   
    try {
      con = getConnection();
      ps = con.prepareStatement("delete from members where member_no = ?");
      ps.setLong(1, memberNo);
      ps.executeUpdate();
      ps.close();

      ps = con.prepareStatement("insert into members " +
        "(member_no, object_data) values (?, ?)");
      ps.setLong(1, memberNo);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(member);
      ps.setBytes(2, baos.toByteArray());
      ps.executeUpdate();
      
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if(ps != null) 
        try { ps.close(); } catch (SQLException e) {}
      if(con != null)
        try { con.close(); } catch (SQLException e) {}
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
}

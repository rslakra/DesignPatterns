// Example 8-4: SequenceBlock.java

package com.oreilly.patterns.chapter8;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class SequenceBlock  {

  private static int BLOCK_SIZE = 10;
  private static long current = -1;
  private static long getNextAt = -1;
  
  
  public static synchronized long getNextId() {
    if((current > -1) && (current < getNextAt))
      return current++;

    // We need to retrieve another block from the database
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      con = getConnection();
      stmt = con.createStatement();
      // Oracle specific
      rs = stmt.executeQuery("SELECT SEQ_PK.NEXTVAL FROM DUAL"); 
      rs.next(); // Exception handler will kick in on failure
      long seqVal = rs.getLong(1);
      current = seqVal * BLOCK_SIZE;
      getNextAt = current + BLOCK_SIZE;
      return current++;
    } catch (SQLException e) {
      throw new IllegalStateException("Unable to access key store");
    } finally {
      if(rs != null) try { rs.close(); } catch (SQLException e) {}
      if(stmt != null) try { stmt.close(); } catch (SQLException e) {}
      if(con != null) try { con.close(); } catch (SQLException e) {}
    }
  }

  private static Connection getConnection() throws SQLException {
    try {
      Context jndiContext = new InitialContext();
      DataSource ds =
 (DataSource)jndiContext.lookup("java:comp/env/jdbc/DataChapterDS");
      return ds.getConnection();
    } catch (NamingException ne) {
        throw new SQLException (ne.getMessage());
    }
  }
}

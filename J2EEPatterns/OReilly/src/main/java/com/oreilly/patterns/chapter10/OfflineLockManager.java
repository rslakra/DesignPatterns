// Example 10-7: OfflineLockManager.java

package com.oreilly.patterns.chapter10;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class OfflineLockManager {
 private DataSource dataSource;
 private static final String LOCK_INSERT_STMT =
    "INSERT INTO LOCK_TRACKING "+
    "(OBJECT_TYPE, OBJECT_KEY, USERNAME) VALUES (?, ?, ?)";

 private static final String LOCK_SELECT_STMT =
    "SELECT OBJECT_TYPE, OBJECT_KEY, USERNAME, " + 
    "OBTAINED FROM LOCK_TRACKING WHERE " + 
    "OBJECT_TYPE = ? AND OBJECT_KEY = ?";

 private static final String RELEASE_LOCK_STMT =
    "DELETE FROM LOCK_TRACKING WHERE OBJECT_TYPE = ? "+
    "AND OBJECT_KEY = ? AND USERNAME = ?";

 private static final String RELEASE_USER_LOCKS_STMT =
    "DELETE FROM LOCK_TRACKING WHERE USERNAME = ?";

 // Oracle specific lock release statement; 
 // release all locks over 15 minutes (1/96 of a day)
 private static final String RELEASE_AGED_LOCKS_STMT =
    "DELETE FROM LOCK_TRACKING WHERE OBTAINED < SYSDATE - (1/96)";

 public OfflineLockManager(DataSource ds) {
   dataSource = ds;
 }

 public boolean getLock(String objectType, long key, String username)
         throws LockingException {

     Connection con = null;
     PreparedStatement pstmt = null;
     boolean gotLock = false;

     try {
       con = dataSource.getConnection();
       // use strict isolation
       con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
       con.setAutoCommit(false);
       pstmt = con.prepareStatement(LOCK_INSERT_STMT);
       pstmt.setString(1, objectType);
       pstmt.setLong(2, key);
       pstmt.setString(3, username);

       try {
          pstmt.executeUpdate();
          gotLock = true;
       } catch (SQLException ex) {
       } // a SQLException means a PK violation, which means an existing lock

       if (!gotLock) { 
          // This means there was a Primary Key violation: somebody has a lock!
          String lockingUsername = getLockingUser(con, objectType, key);
          if ((lockingUsername != null) && (lockingUsername.equals(username)))
             gotLock = true; // We already have a lock!
       }

       con.commit(); // end the transaction
     } catch (SQLException e) {
       try {
         con.rollback();
       } catch (SQLException ignored) {}
       LockingException le = new LockingException(e.getMessage());
       le.initCause(e); // JDK 1.4; comment out for earlier JDK releases
       throw le;
     } finally {
        if (pstmt != null) 
            try { pstmt.close(); } catch (SQLException ignored) {}
        if (con != null) 
            try { con.close(); } catch (SQLException ignored) {}
     }

     return gotLock;
 }

 /**
 * Release a lock held by a given user on a particular type/key pair.
 */
 public boolean releaseLock(String objectType, long key, String username)
         throws LockingException {
   Connection con = null;
   PreparedStatement pstmt = null;
   try {
     con = dataSource.getConnection();
     pstmt = con.prepareStatement(RELEASE_LOCK_STMT);
     pstmt.setString(1, objectType);
     pstmt.setLong(2, key);
     pstmt.setString(3, username);
     int count = pstmt.executeUpdate();
     return (count > 0); // if we deleted anything, we released a lock.
   } catch (SQLException e) {
     LockingException le = new LockingException(e.getMessage());
     le.initCause(e); // JDK 1.4; comment out for earlier JDK releases
     throw le;
   } finally {
     if (pstmt != null) 
         try { pstmt.close(); } catch (SQLException ignored) {}
     if (con != null) 
         try { con.close(); } catch (SQLException ignored) {}
   }
 }

 /**
  * Release all locks held by a particular user. 
  * Returns true if locks were release.
  */
 public boolean releaseUserLocks(String username) throws LockingException {
   Connection con = null;
   PreparedStatement pstmt = null;
   try {
     con = dataSource.getConnection();
     pstmt = con.prepareStatement(RELEASE_USER_LOCKS_STMT);
     pstmt.setString(1, username);
     int count = pstmt.executeUpdate();
     return (count > 0); // if we deleted anything, we released locks.
   } catch (SQLException e) {
     LockingException le = new LockingException(e.getMessage());
     le.initCause(e); // JDK 1.4; comment out for earlier JDK releases
     throw le;
   } finally {
     if (pstmt != null) 
         try { pstmt.close(); } catch (SQLException ignored) {}
     if (con != null) 
         try { con.close(); } catch (SQLException ignored) {}
   }
 }

 /**
   * Release all locks over 15 minutes old.
   */
 public boolean releaseAgedLocks() throws LockingException {
   Connection con = null;
   PreparedStatement pstmt = null;

   try {
      con = dataSource.getConnection();
      pstmt = con.prepareStatement(RELEASE_AGED_LOCKS_STMT);
      int count = pstmt.executeUpdate();
      return (count > 0); // if we deleted anything, we released locks.
   } catch (SQLException e) {
      LockingException le = new LockingException(e.getMessage());
      le.initCause(e); // JDK 1.4; comment out for earlier JDK releases
      throw le;
   } finally {
      if (pstmt != null) 
          try { pstmt.close(); } catch (SQLException ignored) {}
      if (con != null) 
          try {  con.close(); } catch (SQLException ignored) {}
   }
 }


  /**
    * Returns the user currently hold a lock on this type/key pair, 
    * or null if there is no lock.
    */
  private String getLockingUser(Connection con, String objectType, 
                                long key) throws SQLException {
    PreparedStatement pstmt = null;
    try {
      pstmt = con.prepareStatement(LOCK_SELECT_STMT);
      pstmt.setString(1, objectType);
      pstmt.setLong(2, key);
      ResultSet rs = pstmt.executeQuery();
      String lockingUser = null;
      if (rs.next())
        lockingUser = rs.getString("USERNAME");
        rs.close();
        return lockingUser;
    } catch (SQLException e) {
      throw e;
    } finally {
      if (pstmt != null) 
          try { pstmt.close(); } catch (SQLException ignored) {}
    }
 }

}

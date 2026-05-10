// Example 10-1: ConnectionManager.java

package com.oreilly.patterns.chapter10;

import java.sql.Connection;

public final class ConnectionManager
{
    private static final ThreadLocal currentConnection = new ThreadLocal();
     
    static Connection setConnection( Connection connection ) {
        Connection priorConnection = (Connection)currentConnection.get();
        currentConnection.set( connection );
        // We return the prior connection, if any, to give the application
        // the opportunity to deal with it, if so desired. It's important
        // that all database connections be properly closed.
        return priorConnection;
    }
    
    public static Connection getConnection() {
        return (Connection)currentConnection.get();
    }
}

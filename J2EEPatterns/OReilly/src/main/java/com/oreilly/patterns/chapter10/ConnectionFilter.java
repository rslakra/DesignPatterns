// Example 10-2: ConnectionFilter.java

package com.oreilly.patterns.chapter10;

import java.io.IOException;

import javax.servlet.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class ConnectionFilter implements Filter {

    private DataSource dataSource = null;
    
    /**
     * Create a datasource from a parameter defined in web.xml.
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            InitialContext iCtx = new InitialContext();
            Context ctx = (Context) iCtx.lookup("java:comp/env");
            dataSource = (DataSource) 
              ctx.lookup(filterConfig.getInitParameter("JNDI_datasource"));
        } catch (Exception e) {
            ServletException se = new ServletException();
            // Uncomment in JDK 1.4 for easier troubleshooting.
            // se.initCause(e); 
            throw se;
        }
    }

    public void destroy() {
    }

    /** Retrieve a connection, run the filter chain, and return the connection. 
     * */
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {

        Connection con = null;
        
        try {
          con = dataSource.getConnection();
          
          // Set the connection, and retrieve the previous connection for 
          // disposal
          Connection previousCon = ConnectionManager.setConnection(con);
          if(previousCon != null) 
            try { previousCon.close(); } catch (SQLException e) {}

          // Run the rest of the filter chain.  
          chain.doFilter(request, response);
          
          // Make sure we disassociate the connection, just in case.
          ConnectionManager.setConnection(null);
        } catch (SQLException e) {
          ServletException se = new ServletException(e);
          throw se;
        } finally {
          if (con != null)
            try { con.close(); } catch (SQLException e) {}
        }
    }
}

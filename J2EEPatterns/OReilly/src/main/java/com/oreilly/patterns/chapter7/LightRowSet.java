// Example 7-6: LightRowSet.java

package com.oreilly.patterns.chapter7;

import java.sql.*;
import java.util.*;

/**
 * Provide a lightweight wrapper for a set of rows. Preserve types as best
 * as possible.
 */

public class LightRowSet implements java.io.Serializable {
    ArrayList rows = null;
    int rowCount = 0;
    int colCount = 0;
    String[] colNames = null;

    public LightRowSet(ResultSet rs) throws SQLException {
        rows = new ArrayList();

        if (rs == null) {
            throw new SQLException("No ResultSet Provided");
        }

        ResultSetMetaData rsmd = rs.getMetaData();
        colCount = rsmd.getColumnCount();
        colNames = new String[colCount];

        for (int i = 0; i < colCount; i++) {
            colNames[i] = rsmd.getColumnName(i + 1);
        }

        while (rs.next()) {
            Object[] row = new Object[colCount];

            for (int i = 0; i < colCount; i++)
                row[i] = rs.getObject(i + 1);

            rows.add(row);
        }

        rs.close();
    }

    /** Return the column names in this row set, in indexed order */
    public String[] getColumnNames() {
        return colNames;
    }

    /**
     * Return an iterator containing all of the rows
     */
    public Iterator getRows() {
        return rows.iterator();
    }

    /**
     * Return a particular row, indexed from 1..n. Return null if the row
     * isn't found.
     */
    public Object[] getRow(int index) {
      try {
        return (Object[]) rows.get(index - 1);
      } catch (ArrayIndexOutOfBoundsException aioobe) {
        return null;
      }
    }

    public int getRowCount() {
        return rows.size();
    }
}

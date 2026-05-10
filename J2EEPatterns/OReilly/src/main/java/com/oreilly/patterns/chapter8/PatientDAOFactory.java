// Example 8-3: PatientDAOFactory.java

package com.oreilly.patterns.chapter8;

import com.oreilly.patterns.chapter7.PatientDatabaseDAO;

public class PatientDAOFactory {

    private static final int DAO_ORACLE = 1;
    private static final int DAO_DB2 = 2;
    private static final int DAO_SYBASE = 3;
    private static final int DAO_LDAP = 4;
    private static final int DAO_NONE = -1;

    private int mode = DAO_NONE;

    public PatientDAOFactory() {
        String dataSource = System.getProperty("app.datasource");
        if ("oracle".equalsIgnoreCase(dataSource))
            mode = DAO_ORACLE;
        else if ("db2".equalsIgnoreCase(dataSource))
            mode = DAO_DB2;
        else if ("sybase".equalsIgnoreCase(dataSource))
            mode = DAO_SYBASE;
        else if ("ldap".equalsIgnoreCase(dataSource))
            mode = DAO_LDAP;
    }

    public PatientDAO getPatientDAO() {
        switch (mode) {
            case DAO_ORACLE:
                return new PatientDatabaseDAO(); // Generic, works with Oracle
            case DAO_DB2:
                return new PatientDatabaseDAO(); // also works with DB2
                // case DAO_SYBASE:   // We didn't actually implement these.
                //  return new PatientSybaseDAO(); // But Sybase needs special treatment
                // case DAO_LDAP:
                //   return new PatientLDAPDAO(); // Or we can just hit the directory
            default:
                throw new DAOCreationException("No Data Access Mechanism Configured");
        }
    }
}

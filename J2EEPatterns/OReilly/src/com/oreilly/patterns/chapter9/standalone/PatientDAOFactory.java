// The simple variation of Chapter 8's PatiendDAOFactory
package com.oreilly.patterns.chapter9.standalone;

import com.oreilly.patterns.chapter7.PatientDatabaseDAO;
import com.oreilly.patterns.chapter8.PatientDAO;

public class PatientDAOFactory {

    public static PatientDAO getPatientDAO() {
        return new PatientDatabaseDAO(); // Generic, works with Oracle
    }
}

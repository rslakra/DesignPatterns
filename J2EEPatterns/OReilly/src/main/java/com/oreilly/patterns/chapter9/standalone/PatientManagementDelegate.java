// Example 9-1: A business delegate

package com.oreilly.patterns.chapter9.standalone;

import com.oreilly.patterns.chapter7.PatientDTO;
import com.oreilly.patterns.chapter7.InvalidPatientException;
import com.oreilly.patterns.chapter8.PatientDAO;

public class PatientManagementDelegate {

  public static PatientDTO createNewPatient(PatientDTO patient)
    throws InvalidPatientException{

    if(patient == null|| patient.getFirstName() == null 
        || patient.getLastName() == null) 
          throw new InvalidPatientException(
            "Patient Records Require Full Name");

    PatientDAO pDAO = PatientDAOFactory.getPatientDAO();
    PatientDTO newPatientRecord = pDAO.createPatient(patient);

    return newPatientRecord;
  }
}

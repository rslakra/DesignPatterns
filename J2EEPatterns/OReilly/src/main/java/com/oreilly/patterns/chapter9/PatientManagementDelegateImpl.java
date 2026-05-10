// Business delegate implemented via interface, for Example 9-3
// This particular code is not in the book.

package com.oreilly.patterns.chapter9;

import com.oreilly.patterns.chapter7.PatientDTO;
import com.oreilly.patterns.chapter7.InvalidPatientException;
import com.oreilly.patterns.chapter8.PatientDAO;
import com.oreilly.patterns.chapter9.standalone.PatientDAOFactory;

public class PatientManagementDelegateImpl implements PatientManagementDelegate {

  public PatientDTO createNewPatient(PatientDTO patient)
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
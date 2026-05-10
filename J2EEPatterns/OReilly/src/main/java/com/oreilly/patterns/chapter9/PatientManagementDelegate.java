
package com.oreilly.patterns.chapter9;

import com.oreilly.patterns.chapter7.PatientDTO;
import com.oreilly.patterns.chapter7.InvalidPatientException;

public interface PatientManagementDelegate {

      public PatientDTO createNewPatient(PatientDTO patient)
        throws InvalidPatientException;
}

// Example 8-1: PatientDAO.java

package com.oreilly.patterns.chapter8;

import com.oreilly.patterns.chapter7.PatientDTO;

public interface PatientDAO {
	// Retrieve a patient's record from the database
	public PatientDTO findPatient(long pat_no);

	// Save a patient DTO back to the database
	public void savePatient(PatientDTO patient);

	// create a new patient, based on data in the PatientDTO,
	// and return a PatientDTO updated with the primary key for the new patient
	public PatientDTO createPatient(PatientDTO patient);
}

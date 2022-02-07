package com.citius.services;

import java.util.List;

import com.citius.models.patientDetails.Patient;

public interface PatientService {

	Patient registerPatient(Patient patient, String userid);
	List<Patient> getAllPatient();
	Patient getPatientData(long patientId);

}

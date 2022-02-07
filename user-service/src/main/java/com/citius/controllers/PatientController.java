package com.citius.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citius.models.patientDetails.Patient;
import com.citius.services.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(description = "Will Handle All Patient Module Requests", name = "Patient Controller")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Operation(summary = "Patient Registration Service")
	@PostMapping("/patient")
	public Patient registerPatient(@RequestBody Patient patient, @RequestParam("userId") String userid) {
		return patientService.registerPatient(patient, userid);
	}

	@Operation(summary = "All Patients Data")
	@GetMapping("/patients")
	public List<Patient> getAllPatient() {
		return patientService.getAllPatient();
	}

	@Operation(summary = "Patient Data by PatientID")
	@GetMapping("/patient/{patientId}")
	public Patient getPatient(@RequestParam("patientId") String patientId) {
		return patientService.getPatientData(Long.parseLong(patientId));
	}

}

package com.citius.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citius.exception.PatientNotFoundExcepion;
import com.citius.models.User;
import com.citius.models.patientDetails.Patient;
import com.citius.models.patientDetails.PatientRelative;
import com.citius.repository.PatientRelativeRepository;
import com.citius.repository.PatientRepository;
import com.citius.repository.UsersRepository;
import com.citius.utils.CommonUtils;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private PatientRelativeRepository patientRelativeRepository;

	@Override
	public Patient registerPatient(Patient patient, String userid) {

		// Check if User Exists
		User user = userRepository.findById(Long.parseLong(userid)).get();
		Patient patientNew = new Patient();

		if (user != null) {
			patient.setUser(user);
			patient.setAge(CommonUtils.calculateAge(user.getUserDOB()));
			patient.getLanguages().forEach(lang -> {
				lang.setPatient(patient);
			});
			PatientRelative patientRelative = patientRelativeRepository.save(patient.getPatientRelative());
			if (patientRelative != null) {
				patient.setPatientRelative(patientRelative);
				patientNew = patientRepository.save(patient);
			}
		}

		return patientNew;
	}

	@Override
	public List<Patient> getAllPatient() {
		return patientRepository.findAll();
	}

	@Override
	public Patient getPatientData(long patientId) {
		return patientRepository.findById(patientId).orElseThrow(()-> new PatientNotFoundExcepion());
	}

}

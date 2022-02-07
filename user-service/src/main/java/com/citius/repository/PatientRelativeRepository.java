package com.citius.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citius.models.patientDetails.PatientRelative;

public interface PatientRelativeRepository extends JpaRepository<PatientRelative, Long> {

}

package com.citius.models.patientDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PatientRelative {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientRelativeId;
	private String firstName;
	private String lastName;
	private String relationship;
	private String emailAddress;
	private String contact;
	private String address;

	public Long getPatientRelativeId() {
		return patientRelativeId;
	}

	public void setPatientRelativeId(Long patientRelativeId) {
		this.patientRelativeId = patientRelativeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

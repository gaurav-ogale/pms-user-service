package com.citius.models.patientDetails;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.citius.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientId;
	@NotNull
	private String race;
	@NotNull
	@NotEmpty
	private String homeAddress;
	private String ethnicity;
	private String gender;
	@JoinColumn(name = "user_id")
	@OneToOne
	@JsonProperty(access = Access.READ_ONLY)
	private User user;
	private int age;
	@OneToOne
	@JoinColumn(name = "patient_relativeId")
	private PatientRelative patientRelative;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	@JsonIgnoreProperties("patient")
	private Set<Language> languages = new HashSet<Language>();

	// @OneToMany
	// private Set<Allergies> allergies = new HashSet<>();

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public PatientRelative getPatientRelative() {
		return patientRelative;
	}

	public void setPatientRelative(PatientRelative patientRelative) {
		this.patientRelative = patientRelative;
	}

	public Set<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<Language> languages) {
		this.languages = languages;
	}

}

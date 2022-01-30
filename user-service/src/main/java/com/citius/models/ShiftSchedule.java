package com.citius.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

//@Entity
public class ShiftSchedule {

	@Id
	private int id;
	@OneToOne
	@JoinColumn(name = "provider_id")
	private User user;

	
	private DaySchedule monday;

	private DaySchedule tuesday;

	private DaySchedule wednesday;

	private DaySchedule thrusday;

	private DaySchedule friday;

	private DaySchedule saturday;

	private DaySchedule sunday;

	public ShiftSchedule() {
		// TODO Auto-generated constructor stub
	}

	public ShiftSchedule(int id, User user, DaySchedule monday, DaySchedule tuesday, DaySchedule wednesday,
			DaySchedule thrusday, DaySchedule friday, DaySchedule saturday, DaySchedule sunday) {
		super();
		this.id = id;
		this.user = user;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thrusday = thrusday;
		this.friday = friday;
		this.saturday = saturday;
		this.sunday = sunday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DaySchedule getMonday() {
		return monday;
	}

	public void setMonday(DaySchedule monday) {
		this.monday = monday;
	}

	public DaySchedule getTuesday() {
		return tuesday;
	}

	public void setTuesday(DaySchedule tuesday) {
		this.tuesday = tuesday;
	}

	public DaySchedule getWednesday() {
		return wednesday;
	}

	public void setWednesday(DaySchedule wednesday) {
		this.wednesday = wednesday;
	}

	public DaySchedule getThrusday() {
		return thrusday;
	}

	public void setThrusday(DaySchedule thrusday) {
		this.thrusday = thrusday;
	}

	public DaySchedule getFriday() {
		return friday;
	}

	public void setFriday(DaySchedule friday) {
		this.friday = friday;
	}

	public DaySchedule getSaturday() {
		return saturday;
	}

	public void setSaturday(DaySchedule saturday) {
		this.saturday = saturday;
	}

	public DaySchedule getSunday() {
		return sunday;
	}

	public void setSunday(DaySchedule sunday) {
		this.sunday = sunday;
	}

}

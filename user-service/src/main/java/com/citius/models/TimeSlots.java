package com.citius.models;

import java.time.LocalTime;

public class TimeSlots {

	private LocalTime startTime;

	private LocalTime endTime;

	public TimeSlots() {
		
	}

	public TimeSlots(LocalTime startTime, LocalTime endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

}

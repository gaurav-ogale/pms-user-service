package com.citius.models;

import java.util.List;

public class DaySchedule {

	private TimeSlots workingTimes;
	private List<TimeSlots> breakTimes;

	public DaySchedule() {
		// TODO Auto-generated constructor stub
	}

	public DaySchedule(TimeSlots workingTimes, List<TimeSlots> breakTimes) {
		super();
		this.workingTimes = workingTimes;
		this.breakTimes = breakTimes;
	}

	public TimeSlots getWorkingTimes() {
		return workingTimes;
	}

	public void setWorkingTimes(TimeSlots workingTimes) {
		this.workingTimes = workingTimes;
	}

	public List<TimeSlots> getBreakTimes() {
		return breakTimes;
	}

	public void setBreakTimes(List<TimeSlots> breakTimes) {
		this.breakTimes = breakTimes;
	}

}

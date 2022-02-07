package com.citius.utils;

import java.time.LocalDate;
import java.time.Period;

public class CommonUtils {

	public static int calculateAge(LocalDate dob) {
		if (dob != null) {
			return Period.between(dob, LocalDate.now()).getYears();
		}
		return 0;
	}

}

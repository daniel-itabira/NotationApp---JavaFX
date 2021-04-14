package com.odanielrodrigues.notation.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

	private static final String DATE_PATTER = "dd//MM//yyyy";
	private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern(DATE_PATTER);

	public static String format(LocalDate data) {
		if (data == null) {
			return null;
		} else {
			return DATE_FORMATER.format(data);
		}
	}

	public static LocalDate parse(String value) {
		try {
			return DATE_FORMATER.parse(value, LocalDate::from);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	public static boolean validDate(String value) {
		return DateUtil.parse(value) != null;
	}
}

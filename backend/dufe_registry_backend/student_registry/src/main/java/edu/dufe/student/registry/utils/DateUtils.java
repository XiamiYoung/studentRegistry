package edu.dufe.student.registry.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import edu.dufe.student.registry.constants.Constants;

public class DateUtils {

	public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate) {
		if (utilDate == null) {
			return null;
		}
		return new java.sql.Date(utilDate.getTime());
	}

	public static java.util.Date sqlDateToUtilDate(java.sql.Date sqlDate) {
		if (sqlDate == null) {
			return null;
		}
		return new java.util.Date(sqlDate.getTime());
	}

	public static String formatLong(Long longTime) {
		if (longTime == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DEFAULT_LOCALE_FORMAT, Locale.JAPAN);
		return sdf.format(new Date(longTime));
	}
}

package com.test.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUtil {
	public static Timestamp getCurrent() {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+8"));

		return new Timestamp(calendar.getTimeInMillis());
	}

	public static Timestamp getToday() {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+8"));
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);

		return new Timestamp(calendar.getTimeInMillis());
	}

	public static Timestamp getLastDay(int n) {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+8"));
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);

		return new Timestamp(calendar.getTimeInMillis() - 86400000L * n);
	}

	public static String getRfc822DateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));

		return sdf.format(date);
	}

	public static Timestamp convertFromGMT(String gmt) {
		return new Timestamp(Timestamp.valueOf(gmt.substring(0, 10) + " " + gmt.substring(11, 19)).getTime() + 28800000);
	}

	public static Integer getYear() {
		return Integer.valueOf(getCurrent().toString().substring(0, 4));
	}
}
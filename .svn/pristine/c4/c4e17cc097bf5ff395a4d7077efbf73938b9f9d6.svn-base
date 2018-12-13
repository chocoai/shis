package com.mibo.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DateUtil {
	public static void main(String[] args) {
		System.out.println(dateFormat("2015-2-28 23:59:59"));
	}

	public static String getCurrentEndMonth() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(2, 1);
		calendar.set(5, 0);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}

	public static String addDateMinut(String day, int month) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(2, month);
		date = cal.getTime();
		cal = null;
		return format.format(date);
	}

	public static boolean timeFormat(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sdf.setLenient(false);
			sdf.parse(time);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static boolean dateFormat(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			sdf.setLenient(false);
			sdf.parse(time);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static String getFormatTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	public static String getFormatTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	public static String getFormatHours() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		return sdf.format(new Date());
	}

	public static String dateFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static boolean isSmallCurrentTime(Date date) {
		Date currentTime = new Date();
		if (date.getTime() > currentTime.getTime()) {
			return false;
		}
		return true;
	}

	public static Date getStrDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static Date getStrDate1(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static int getStaTime(String sta, String end) {
		Date staTime = getStrDate(sta);
		Date endTime = getStrDate(end);
		double time = Math.ceil((endTime.getTime() - staTime.getTime()) / 3600000.0D);
		return (int) time;
	}

	public static int getMonthSpace(Date date1, Date date2) {
		int day = 0;
		int month = 0;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(6);
		int day2 = cal2.get(6);
		int year1 = cal1.get(1);
		int year2 = cal2.get(1);
		if (year1 != year2) {
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0)) {
					timeDistance += 366;
				} else {
					timeDistance += 365;
				}
			}
			day = timeDistance + (day2 - day1);
		} else {
			day = day2 - day1;
		}
		month = day / 30;
		return month;
	}

	public static Date timeStampPlus(Date time, int minute) {
		return new Date(time.getTime() + minute * 60 * 1000);
	}

	public static Date timeStampSubtract() {
		return new Date(new Date().getTime() - 604800000L);
	}
}
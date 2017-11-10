package com.example.demo.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class ClockingUtil {

	/**
	 * 
	 * @param currentDate
	 * @return name of the current month
	 */

	public static String getMonthName(Date currentDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		int month = cal.get(Calendar.MONTH);

		return Month.of(month).name();
	}

	/**
	 * finds the current and last month so that they can be displayed in the UI
	 * selector the user will be restricted to see just these 2 months
	 * 
	 * @return last and current month
	 */

	public static List<String> getMonthsToDisplay() {
		List<String> monthsToDisplay = new ArrayList<String>();
		Date nowDate = new Date();
		String currentMonthName = getMonthName(nowDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(nowDate);
		cal.add(Calendar.MONTH, +1);
		String previousMonthName = getMonthName(cal.getTime());

		int previousMonthYear = cal.get(Calendar.YEAR);
		cal.setTime(nowDate);
		int currentMonthYear = cal.get(Calendar.YEAR);

		StringBuilder monthYear = new StringBuilder();
		monthYear.append(previousMonthName);
		monthYear.append("-");
		monthYear.append(previousMonthYear);
		monthsToDisplay.add(monthYear.toString());

		StringBuilder monthYear2 = new StringBuilder();
		monthYear2.append(currentMonthName);
		monthYear2.append("-");
		monthYear2.append(currentMonthYear);
		monthsToDisplay.add(monthYear2.toString());

		return monthsToDisplay;

	}

	/**
	 * 
	 * @param list
	 *            of all clocking details
	 * @return difference
	 */

	public static String workTime(List<ClockingDetails> list) {
		long diff = 0;
		long norma = 0;
		long workTime = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

		for (ClockingDetails cd : list) {
			try {
				Date start = dateFormat.parse(cd.getStart());
				Date stop = dateFormat.parse(cd.getStop());
				workTime += start.getTime() - stop.getTime(); // milliseconds

				Calendar cal = Calendar.getInstance();
				cal.setLenient(false);
				cal.setTime(cd.getCurrentDate());

				// if Friday
				norma += (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) ? dateFormat.parse("06:30").getTime()
						: dateFormat.parse("09:00").getTime();
				// norma+= dateFormat.parse("08:30").getTime();

			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		
		diff = norma - workTime;
		long diffMinutes = (diff / (60 * 1000)) % 60;
		long diffHours = (diff / (60 * 60 * 1000)) % 24;

		return diffHours + ":" + diffMinutes;
	}

	//
	// public static void main(String[] args) {
	// //ClockingUtil cl = new ClockingUtil(new Date());
	// List<String> monthsToDisplay = new ArrayList<String>();
	// monthsToDisplay = ClockingUtil.getMonthsToDisplay();
	// System.out.println(monthsToDisplay.get(0).toString());
	// }

}

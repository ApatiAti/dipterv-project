package hu.web.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {

	public static Date addYearsToCurrentDate(int numberOfYear){
		Calendar c = getCurrentDateCalendar();
		c.add(Calendar.YEAR, numberOfYear);
		return c.getTime();
	}
	
	public static Date addMonthsToCurrentDate(int month){
		Calendar c = getCurrentDateCalendar();
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}
	
	public static Date addDaysToCurrentDate(int day){
		Calendar c = getCurrentDateCalendar();
		c.add(Calendar.DAY_OF_YEAR, day);
		return c.getTime();
	}
	
	public static Date addMinutesToCurrentDate(int minutes){
		Calendar c = getCurrentDateCalendar();
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}
	
	public static boolean afterNotNull(Date startDate, Date endDate) {
		return startDate != null && endDate != null && startDate.after(endDate);
	}
	
	private static Calendar getCurrentDateCalendar(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c;
	}

	public static boolean beforeNotNull(Date startDate, Date endDate) {
		return startDate != null && endDate != null && startDate.before(endDate);
		
	}
}

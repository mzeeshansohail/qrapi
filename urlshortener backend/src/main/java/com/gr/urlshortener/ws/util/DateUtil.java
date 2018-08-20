package com.gr.urlshortener.ws.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Long getDaysBetween(Date startDate, Date endDate) {
    	LocalDate date1 = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	LocalDate date2 = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	return ChronoUnit.DAYS.between(date1, date2);
    }
	
	public static Date addMonthInDate(Date date, int month) {
		Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.MONTH, month);
		return cal.getTime();   
	}
}
	  

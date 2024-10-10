package com.course.login.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DateUtils {

	@Value("${jms.jwt.timezone}")
	private String TIMEZONE;
	
	private SimpleDateFormat dateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
		return sdf;
	}
	
	public String getDateString() {
		Date now = new Date();
		// Retornamos la fecha actual del sistema en modo string
		return dateFormat().format(now);
	}
	
	public Long getDateMillis() {
		Date now = new Date();
		String strDate = dateFormat().format(now);
		Date strNow = new Date();
		
		try {
			strNow = dateFormat().parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return strNow.getTime(); // Return milliseconds
	}
}

package com.winry.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

	public static String getNowTime() {
		ZoneId id = ZoneId.systemDefault();
		ZonedDateTime zoned = ZonedDateTime.of(LocalDateTime.now(), id);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("hhmmss");
	    return zoned.format(format);
	}

	public static String getDatePath() {
		ZoneId id = ZoneId.systemDefault();
		ZonedDateTime zoned = ZonedDateTime.of(LocalDateTime.now(), id);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd/");
		return zoned.format(format);
	}

}

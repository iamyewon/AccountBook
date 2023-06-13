package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	public static LocalDate stringToLocalDate(String date) {
		//사용자입룍 : YYYYMMDD
		//1. YYYYMMDD -> String YYYY-MM-DD
		//2. 
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
	} 
}

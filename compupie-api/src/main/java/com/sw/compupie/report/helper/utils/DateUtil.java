package com.sw.compupie.report.helper.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String changeDateToAmerican(String date)  {
		Date dates = null;
		
		int pos = date.indexOf('-');
		try {
			if(pos >2){
				dates = new SimpleDateFormat("yyyy-MM-DD").parse(date);
			}else{
				dates = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(dates);
	}
}

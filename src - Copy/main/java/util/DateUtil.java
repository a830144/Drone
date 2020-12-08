package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String dateToUSFormat(Date date){
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String result = format.format(date);
		return result;
	}

	
	public static void main(String args[]){
		String result = DateUtil.dateToUSFormat(new Date());
		System.out.println(result);
	}
}

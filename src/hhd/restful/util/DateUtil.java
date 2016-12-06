package hhd.restful.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static boolean isExpired(String time, int second){
		try {
			Date lastest = format.parse(time);
			Date now = new Date();
			long dis = now.getTime() - lastest.getTime();
			if(dis/1000 <= second){
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String getNowDateTime(){
		return format.format(new Date());
	}
}

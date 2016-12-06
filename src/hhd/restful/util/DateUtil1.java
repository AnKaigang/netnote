package hhd.restful.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import org.apache.commons.lang3.time.*;;

public class DateUtil1 extends DateUtils {

	/**
	 * 获取年份
	 * @return
	 */
	public static final int getYear(){
		Calendar c=Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * 获取月份
	 * @return
	 */
	public static final int getMonth(){
		Calendar c=Calendar.getInstance();
		return c.get(Calendar.MARCH);
	}
	
	/**
	 * 获取日期
	 * @return
	 */
	public static final int getDate(){
		Calendar c=Calendar.getInstance();
		return c.get(Calendar.DATE);
	}
	
	/**
	 * yyyy-MM-dd
	 * @return
	 */
	public static String getStringDate()
	  {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }

	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	  public static String getStringTimestamp()
	  {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }
	  
	  /**
	   * 
	   * @param dateStr yyyy-MM-dd
	   * @return
	 * @throws java.text.ParseException
	   */
	  public static Date getShortDate(String dateStr) throws ParseException
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    return formatter.parse(dateStr);
	  }
	  /**
	   * 
	   * @param dateStr yyyy-MM-dd  HH:mm:ss
	   * @return
	 * @throws java.text.ParseException
	   */
	  public static Date getLongDate(String dateStr) throws ParseException
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return formatter.parse(dateStr);
	  }
	  
	  /**
		 * "yyyy-MM-dd HH:mm:ss"
		 * @return
		 */
		  public static String getStringTimestamp(Date now)
		  {
		    Date currentTime = new Date();
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateString = formatter.format(currentTime);
		    return dateString;
		  }
}

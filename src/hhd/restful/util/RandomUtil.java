package hhd.restful.util;

import java.text.DecimalFormat;

public class RandomUtil {
	
	private static DecimalFormat  df = new DecimalFormat("######0.00");   

	
	public static Integer randomInt(int min, int max){
		return (int)(min + Math.random()*(max - min + 1));
	}
	
	public static String randomDouble(double min, double max){
		return df.format((double)(min + Math.random()*(max - min + 1)));
	}
	
}

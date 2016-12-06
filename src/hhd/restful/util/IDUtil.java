package hhd.restful.util;

import java.util.UUID;

public class IDUtil {
	
	public static String getUUID()  
    {  
        return UUID.randomUUID().toString().replaceAll("-", "");  
    }  

	
	public static void main(){
		System.out.println(getUUID());
	}
}

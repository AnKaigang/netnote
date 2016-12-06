package hhd.restful.util;

import java.util.HashMap;
import java.util.Map;
import hhd.restful.util.Security;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;

/**
 * 封装cookies操作
 * @author LIUKAI
 *
 */
public class addCokkies {
	
	/**
	 * liukai
	 * 设置cookies，保存用户账号及密码
	 * @param response
	 * @param name
	 * @param password
	 */
	public static void addCookie(HttpServletResponse response ,String name ,String password)
	{	
		/**对name、password加密
		 * 使用AES算法解密字符串
		 */
		name = Security.encryptAES(name,Security.generateAesKey("hangar"));
		password = Security.encryptAES(password,Security.generateAesKey("hangar"));
		Cookie cookies = new Cookie(name, password);
		cookies.setPath("/");
		cookies.setMaxAge(60*60*24*30);
		response.addCookie(cookies);
	}
	
	/**
	 * liuaki
	 * 根据名字，获取cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
		Map<String,Cookie> cookieMap = ReadCookieMap(request);
		name = Security.encryptAES(name,Security.generateAesKey("hangar"));
		if(cookieMap.containsKey(name)){
		  Cookie cookie = (Cookie)cookieMap.get(name);
		  return cookie;
		}else{
		  return null;
		} 
	}
	
	/**
	 * liukai
	 * 将cookie封装在Map里
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){ 
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		Cookie[] cookies = request.getCookies();
		if(null!=cookies){
		  for(Cookie cookie : cookies){
		   cookieMap.put(cookie.getName(), cookie);
		  }
		}
		return cookieMap;
	}
}

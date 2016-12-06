package hhd.restful.controller;

import hhd.restful.bean.User;
import hhd.restful.service.LoginService;
import hhd.restful.util.DateUtil;
import hhd.restful.util.DateUtil1;
import hhd.restful.util.IDUtil;
import hhd.restful.util.Security;
import hhd.restful.util.addCokkies;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;


	/**
	 *刘凯
	 *生成验证码页面
	 *用于在login，regitser页面中显示验证码图片
	 * @return
	 */
	@RequestMapping(value = "producecode")
	public ModelAndView Verifycode(){
			return new ModelAndView("base/producecoed");
	}
	
	/**
	 * liukai
	 * 登录页面
	 * @return
	 */
	@RequestMapping(value = "login")
	public ModelAndView login(){
			return new ModelAndView("user_login");
	}
	/**
	 * liukai
	 * 注册页面
	 * @return
	 */
	@RequestMapping(value = "register")
	public ModelAndView rigster(){
			return new ModelAndView("user_register");
	}
	@RequestMapping(value = "test")
	public ModelAndView test(){
			return new ModelAndView("artical_edit");
	}

  /**
   * 获取生成的验证码图片中的验证码，存放在session里面
   * @param session
   * @return
   */
	@RequestMapping(value = "getVerifyCode")
	@ResponseBody
	public String getVerifyCode(HttpSession session){
		       return (String)session.getAttribute("rCode");
	}
	/**
	 * liukai
	 * 用户注册时，当用户输入用户名是，检验用户名是否已经被使用
	 * @param request
	 * @param response
	 * @return
	 * @throws java.io.IOException
	 */
	@RequestMapping(value="VerifyName")
	@ResponseBody
	public String verifyname(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String name =request.getParameter("name");
		if(loginService.IsExsitName(name))
			return "error";
		return "true";
	}
	
	/**
	 * liukai
	 * 验证用户输入的用户账号是否正确
	 * @param request
	 * @param response
	 * @return
	 * @throws java.io.IOException
	 */
	@RequestMapping(value="VerifyAccount")
	@ResponseBody
	public String verifyAcount(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String account =request.getParameter("account");
		if(loginService.IsExsitAccount( account))
			return "true";
		return "error";
	}
	
	/**
	 * liukai
	 * 验证邮箱是否是用户的注册邮箱
	 * @param request
	 * @param response
	 * @return
	 * @throws java.io.IOException
	 */
	@RequestMapping(value="VerifyMail")
	@ResponseBody
	public String verifyMyMail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String mail =request.getParameter("mail");
		String account =request.getParameter("account");
		if(loginService.IsExsitMail(account, mail))
			return "true";
		return "error";
	}
	
	/**
	 * liukai
	 * 修改密码
	 * 根据邮箱与用户账号修改相应的密码
	 * @param request
	 * @param response
	 * @return
	 * @throws java.io.IOException
	 */
	@RequestMapping(value="ModifyPasswd")
	@ResponseBody
	public String verifyMyPawd(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String mail =request.getParameter("mail");
		String account =request.getParameter("account");
		String password = request.getParameter("password");
		if(loginService.UpdateMyPwd(account, mail,password))
			return "true";
		return "error";
	}
	/**
	 * liukai
	 * 向注册邮箱发送验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws java.io.IOException
	 */
	@RequestMapping(value="getVerifyCodeOfMail")
	@ResponseBody
	public String getMailVerify(HttpServletRequest request, HttpServletResponse response)throws IOException{
		String mail= request.getParameter("mail");
		HtmlEmail email = new HtmlEmail();
		String info ="" ;
        Random random = new Random(); 
		try{
			email.setHostName("smtp.163.com");//发送服务器的名字
            email.setCharset("utf-8");//设置编码集  
            email.addTo(mail);//收件人邮箱  
            email.setFrom("liukai28954@163.com");//发送人邮箱  
            email.setAuthentication("liukai28954@163.com", "685305liukaiwin");//发件人的用户名与密码
           
            email.setSubject("网络日记邮箱验证");//发送主题（邮件主题）  
            //email.setMsg(info);//邮件内容 
           
            for(int i=0;i<4;i++)
            {
            	int t =random.nextInt(10);
            	info += String.valueOf(t);
            }
            email.setMsg("<b><a href=\"http://www.google.com\"> "+info+"</a></b>");//邮件内容
             //email.setMsg(info); 
            email.send();//发送  
            System.out.println("发送完成");
		}catch(EmailException e){
			System.out.println("发送失败！");
			e.printStackTrace();
		}
		 return info ;
	}
	
	/**
	 * liukai
	 * 修改密码页面
	 * @return
	 */
	@RequestMapping(value="modifypassword")
	public ModelAndView modifyUserPassword()
	{
		return new  ModelAndView("ModifyPassword");
	}
	/**
	 * liukai
	 * 登录验证，判断用户登录账号（id、email）与密码是否正确、匹配
	 * 将登陆的成功的用户的用户名、账号保存在session里面
	 * 登陆失败返回登录页面
	 * @param session
	 * @param account
	 * @param password
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws java.io.IOException
	 */
	@RequestMapping(value = "loginCheck")
	public String loginCheck(HttpSession session,String account,String password,HttpServletRequest request, HttpServletResponse response,Model model) throws IOException{
		User user = loginService.isLogin(account, password); 
		String remember = request.getParameter("remember_me");
		if(user==null){//失败，返回登录页面
			model.addAttribute("error", "账号或密码错误！");
			model.addAttribute("color","#E3170D");
			return "user_login";
		}else{//登录成功
			//System.out.println(remember);
			model.addAttribute("error", "");
			if(remember !=null)
			{
				//System.out.println("111");
				addCokkies.addCookie(response, account, password);
			}
			String username ="";
			String userid = "";
			loginService.GetUserInfo(account,userid,username);
             session.setAttribute("username",user.getName());
             session.setAttribute("userid", user.getId());
             username=  user.getName();
             userid = user.getId();
			return "redirect:/personal_article_list";

		}
		
	}
	
	/**
	 * 使用记住密码功能，快捷登陆
	 * @param request
	 * @param response
	 * @return
	 * @throws java.io.IOException
	 */
	@RequestMapping(value="GetMyRemember")
	@ResponseBody
	public String  getremember(HttpServletRequest request,HttpServletResponse response)throws IOException{
		String name = request.getParameter("name") ;
		Cookie cookie = addCokkies.getCookieByName(request, name);
		if(cookie==null)
			return "";
		String password = cookie.getValue();
		password = Security.decryptAES(password,Security.generateAesKey("hangar"));
		return password;
	}
	
	/**
	 * liulai
	 * 注册用户信息，存入数据库
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws java.io.IOException
	 */
	@RequestMapping(value="RigistCheck")
	public String RegisterCheck(HttpSession session,HttpServletRequest request, HttpServletResponse response , Model model)throws IOException{
		
		User user = new User();
		String src="headImage/"+(String)session.getAttribute("Imagename");
		String registerdateString = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
		user.setAccount(request.getParameter("account"));
		user.setHeader(src);
		user.setMail(request.getParameter("mail"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setPoint(0);
		user.setRank(0);
		user.setRegisterdate(registerdateString);
		user.setId(IDUtil.getUUID());
		user.setIsEnable(1);
		boolean result = loginService.RegisterUser(user);
			model.addAttribute("error", "注册成功！");
			model.addAttribute("color","#0000FF");
			return "user_login";
	}

}

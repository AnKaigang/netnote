package hhd.restful.service;

import hhd.restful.bean.User;

public interface LoginService {

	public User isLogin(String account, String password); /*登陆验证用户名、密码*/
	public void GetUserInfo(String account, String userid, String username); /*获取登陆用户的用户名、用户id*/
	public boolean IsExsitName(String name);  /*验证用户名是否存在*/
	public boolean IsExsitAccount(String account);/*验证用户账号是否存在*/
	public boolean IsExsitMail(String account, String mail); /*验证email是否是用户的注册邮箱*/
	public boolean UpdateMyPwd(String account, String mail, String password); /*更新用户的密码*/
	public boolean RegisterUser(User user);  /*注册新用户*/
}

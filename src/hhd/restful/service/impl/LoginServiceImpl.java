package hhd.restful.service.impl;

import hhd.restful.bean.User;
import hhd.restful.dao.LoginDao;
import hhd.restful.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;




@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDao loginDao;
	/**
	 * liukai
	 * 登陆验证用户名、密码
	 */
	@Override
	public User isLogin(String account, String password) {
		// TODO Auto-generated method stub
		User user = (User)loginDao.selectUserByAccount(account);
		if(user==null)
			return null;
		if(password.equals(user.getPassword()))
			return user;
		return null;
	}
	/**
	 * liukai
	 * 获取登陆用户的用户名、用户id
	 */
	@Override
    public void GetUserInfo(String account,String userid,String username){
   	 userid = loginDao.selectUserByAccount(account).getAccount();
   	 username = loginDao.selectUserByAccount(account).getName() ;
   	 
    }
	
	/**
	 * liukai
	 * 验证用户名是否存在
	 */
	@Override
	public boolean IsExsitName(String name){
		User user = (User)loginDao.selectAllUserName(name);
		if(user==null)
			return false ;
		return true;
	}
	
	/**
	 * liukai
	 * 验证用户账号是否存在
	 */
	@Override
	public boolean IsExsitAccount(String account)
	{
		User user = (User)loginDao.selectUserByAccount(account);
		if(user==null)
			return false;
		return true;
	}
	
	/**
	 * liukai
	 * 验证email是否是用户的注册邮箱
	 *
	 */
	@Override
	public boolean IsExsitMail(String account,String mail)
	{
		User user = (User)loginDao.selectUserByAccount(account);
		if(mail.equals(user.getMail()))
			return true;
		return false;
	}
	
	/**
	 * liukai
	 * 更新用户的密码
	 */
	@Override
	public boolean UpdateMyPwd(String account,String mail,String password)
	{
		int result=loginDao.UpdatePassword(account, mail, password);
		if(result==0)
		{
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * liukai
	 * 注册新用户
	 */
	@Override
	public boolean  RegisterUser(User user){
		 return loginDao.RegisterNewUser(user);
	}
}

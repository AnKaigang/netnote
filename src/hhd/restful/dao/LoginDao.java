package hhd.restful.dao;

import hhd.restful.bean.User;

public interface LoginDao {
	
	public User selectUserByAccount(String account);
	public User selectAllUserName(String name);
	public int UpdatePassword(String account, String mail, String password);
	public boolean RegisterNewUser(User user);
}

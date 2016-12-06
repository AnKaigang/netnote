package hhd.restful.dao;

import hhd.restful.bean.User;

import java.util.List;

public interface UserLeftDao {
	
	public List<User> selectUserInfosByUserid(String userid);
	
	
	
}

package hhd.restful.service;

import hhd.restful.bean.User;

import java.util.List;


public interface UserLeftService {
	
	public List<User> searchUserInfoByUserid(String userid);
}

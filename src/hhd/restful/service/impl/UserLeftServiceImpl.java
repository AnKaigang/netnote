package hhd.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hhd.restful.bean.User;
import hhd.restful.dao.UserLeftDao;
import hhd.restful.service.UserLeftService;


@Service
public class UserLeftServiceImpl implements UserLeftService {
	
	
	@Autowired
	private UserLeftDao userLeftDao;
	
	

	@Override
	public List<User> searchUserInfoByUserid(String userid) {
		// TODO Auto-generated method stub
		return userLeftDao.selectUserInfosByUserid(userid);
	}
	
	
	

}

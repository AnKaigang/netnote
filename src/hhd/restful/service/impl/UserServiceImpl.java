/**
 * 
 */
package hhd.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hhd.restful.bean.User;
import hhd.restful.dao.UserDao;
import hhd.restful.service.UserService;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年6月30日下午10:30:46
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	
	@Override
	public List<User> selectByRank(int start, int num) {
		return userDao.selectUserByRank(start, num);		 
	}


	/* (non-Javadoc)
	 * @see hhd.restful.service.UserService#selectAllByOrder(java.lang.String, int, int)
	 */
	@Override
	public List<User> selectAllByOrder(String where, int i, int j) {
		
		return userDao.selectAllByOrder(where,i,j);
	}


	/* (non-Javadoc)
	 * @see hhd.restful.service.UserService#selectLikeByUsername(java.lang.String)
	 */
	@Override
	public List<User> selectLikeByUsername(String name) {
		// TODO Auto-generated method stub
		return userDao.selectLikeByUsername(name);
	}


	/* (non-Javadoc)
	 * @see hhd.restful.service.UserService#updateUserStatus(java.lang.String)
	 */
	@Override
	public int updateUserStatus(String id,int number) {
		return userDao.updateUserStatus(id,number);
	}


	/* (non-Javadoc)
	 * @see hhd.restful.service.UserService#selectById(java.lang.String)
	 */
	@Override
	public User selectById(String id) {
		// TODO Auto-generated method stub
		return userDao.selectById(id);
	}
	@Override
	public User searchUserByid(String userid) {
		// TODO Auto-generated method stub
		return userDao.selectUserByid(userid);
	}



	@Override
	public boolean editAccount(String userid, String newaccount) {
		// TODO Auto-generated method stub
		return userDao.updateAccountByid(userid,newaccount);
	}


	@Override
	public boolean editPassword(String userid, String newpassword) {
		// TODO Auto-generated method stub
		return userDao.updatePasswordByid(userid,newpassword);
	}


	@Override
	public boolean editMail(String userid, String newmail) {
		// TODO Auto-generated method stub
		return userDao.updateMail(userid,newmail);
	}


	@Override
	public boolean editUsername(String userid, String newusername) {
		// TODO Auto-generated method stub
		return userDao.updateUsername(userid,newusername);
	}


	@Override
	public boolean editUserHeader(String userid, String imgpath) {
		// TODO Auto-generated method stub
		return userDao.updateHeaderByUserid(userid,imgpath);
	}


	@Override
	public boolean addPoint(String userid, String point) {
		// TODO Auto-generated method stub
		return userDao.addPoint(userid,point);
	}


}

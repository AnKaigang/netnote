/**
 * 
 */
package hhd.restful.service;

import hhd.restful.bean.Sort;
import hhd.restful.bean.User;

import java.util.List;


/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年6月30日下午10:29:44
 */
public interface UserService {
	public List<User> selectByRank(int start, int num);

	/**
	 * @param where
	 * @param i
	 * @param j
	 * @return
	 */
	public List<User> selectAllByOrder(String where, int i, int j);

	/**
	 * @param name
	 * @return
	 */
	public List<User> selectLikeByUsername(String name);

	/**
	 * @param id
	 * @return
	 */
	public int updateUserStatus(String id, int number);

	/**
	 * @param id
	 * @return
	 */
	public User selectById(String id);
	public User searchUserByid(String userid);

	public boolean editAccount(String userid, String newaccount);

	public boolean editPassword(String userid, String newpassword);

	public boolean editMail(String userid, String newmail);

	public boolean editUsername(String userid, String newusername);

	public boolean editUserHeader(String userid, String imgpath);
	
	public boolean addPoint(String userid, String point);

}

package hhd.restful.dao;

import hhd.restful.bean.User;

import java.util.List;
/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年6月30日上午11:53:17
 */
public interface UserDao {
			public List<User> selectUserLimit(int start, int num);
			public List<User> selectUserByRank(int start, int num);
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
			public User selectUserByid(String userid);
			public boolean updateAccountByid(String userid, String newaccount);
			public boolean updatePasswordByid(String userid, String newpassword);
			public boolean updateMail(String userid, String newmail);
			public boolean updateUsername(String userid, String newusername);
			public boolean updateHeaderByUserid(String userid, String imgpath);
			public boolean addPoint(String userid, String point);
}
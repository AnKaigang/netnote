package hhd.restful.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hhd.restful.bean.User;
import hhd.restful.dao.UserDao;

/**
 * @作者：Administrator--安凯刚
 * 
 * @日期：2016年6月30日上午11:54:34
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<User> rowMapper = new RowMapper<User>() {

		@Override
		public User mapRow(ResultSet rs, int arg1) throws SQLException {
			User user = new User();
			user.setAccount(rs.getString("account"));
			user.setHeader(rs.getString("header"));
			user.setId(rs.getString("id"));
			user.setMail(rs.getString("mail"));
			user.setName(rs.getString("name"));
			user.setIsEnable(rs.getInt("isEnable"));
			user.setPassword(rs.getString("password"));
			user.setPoint(rs.getInt("point"));
			user.setRank(rs.getInt("rank"));
			user.setRegisterdate(rs.getString("date"));
			return user;
		}
	};

	/*
	 * 查询指定人数的用户
	 * 
	 * @akg
	 */
	@Override
	public List<User> selectUserByRank(int start, int num) {
		// TODO Auto-generated method stub
		String sql = "select * from user order by point desc limit ?, ?";
		return jdbcTemplate.query(sql, new Object[] { start, num },
				new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						User user = new User();
						user.setAccount(rs.getString("account"));
						user.setHeader(rs.getString("header"));
						user.setId(rs.getString("id"));
						user.setMail(rs.getString("mail"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						user.setPoint(rs.getInt("point"));
						user.setRank(rs.getInt("rank"));
						user.setIsEnable(rs.getInt("isEnable"));
						return user;
					}
				});
	}

	/*
	 * 根据等级排序查询指定数量的人数
	 * 
	 * @akg
	 */
	@Override
	public List<User> selectUserLimit(int start, int num) {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hhd.restful.dao.UserDao#selectAllByOrder(java.lang.String, int, int)
	 */
	@Override
	public List<User> selectAllByOrder(String where, int start, int num) {
		String sql = "";
		if (where != "") {
			sql = "select * from user " + where
					+ " order by point desc  limit ?, ?";
		} else {
			sql = "select * from user order by point desc limit ?, ?";
		}
		return jdbcTemplate.query(sql, new Object[] { start, num },
				new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						User user = new User();
						user.setAccount(rs.getString("account"));
						user.setHeader(rs.getString("header"));
						user.setId(rs.getString("id"));
						user.setMail(rs.getString("mail"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						user.setIsEnable(rs.getInt("isEnable"));
						user.setPoint(rs.getInt("point"));
						user.setRank(rs.getInt("rank"));
						user.setRegisterdate(rs.getString("registerDate"));
						return user;
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hhd.restful.dao.UserDao#selectLikeByUsername(java.lang.String)
	 */
	@Override
	public List<User> selectLikeByUsername(String name) {
		String sql = "select * from user where name like '%" + name + "%'";
		return jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setAccount(rs.getString("account"));
				user.setHeader(rs.getString("header"));
				user.setId(rs.getString("id"));
				user.setMail(rs.getString("mail"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setPoint(rs.getInt("point"));
				user.setRank(rs.getInt("rank"));
				user.setIsEnable(rs.getInt("isEnable"));
				return user;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hhd.restful.dao.UserDao#updateUserStatus(java.lang.String)
	 */
	@Override
	public int updateUserStatus(String id, int number) {
		String sql = "update user set isEnable= " + number + "  where id = '"
				+ id + "' ";
		System.out.println(sql);
		return jdbcTemplate.update(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hhd.restful.dao.UserDao#selectById(java.lang.String)
	 */
	@Override
	public User selectById(String id) {
		String sql = "select * from user where id= '" + id + "' ";
		return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setAccount(rs.getString("account"));
				user.setHeader(rs.getString("header"));
				user.setId(rs.getString("id"));
				user.setMail(rs.getString("mail"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setPoint(rs.getInt("point"));
				user.setRank(rs.getInt("rank"));
				user.setIsEnable(rs.getInt("isEnable"));
				return user;
			}
		});
	}

	@Override
	public User selectUserByid(String userid) {
		// TODO Auto-generated method stub
		String sql = "select * from user where id =? ";

		return jdbcTemplate.queryForObject(sql, new Object[] { userid },
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub

						User user = new User();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						user.setMail(rs.getString("mail"));
						user.setHeader(rs.getString("header"));
						user.setRank(rs.getInt("rank"));
						user.setAccount(rs.getString("account"));
						user.setRegisterdate(rs.getString("registerDate"));
						user.setPoint(rs.getInt("point"));
						return user;
					}
				});
	}

	@Override
	public boolean updateAccountByid(String userid, String newaccount) {
		// TODO Auto-generated method stub
		String sql = "update user set account = ? where id = ?";
		return jdbcTemplate.update(sql, new Object[] { newaccount, userid }) > 0 ? true
				: false;
	}

	@Override
	public boolean updatePasswordByid(String userid, String newpassword) {
		// TODO Auto-generated method stub
		String sql = "update user set password = ? where id = ?";
		return jdbcTemplate.update(sql, new Object[] { newpassword, userid }) > 0 ? true
				: false;
	}

	@Override
	public boolean updateMail(String userid, String newmail) {
		// TODO Auto-generated method stub
		String sql = "update user set mail = ? where id = ?";
		return jdbcTemplate.update(sql, new Object[] { newmail, userid }) > 0 ? true
				: false;
	}

	@Override
	public boolean updateUsername(String userid, String newusername) {
		// TODO Auto-generated method stub
		String sql = "update user set name = ? where id = ?";
		return jdbcTemplate.update(sql, new Object[] { newusername, userid }) > 0 ? true
				: false;
	}

	@Override
	public boolean updateHeaderByUserid(String userid, String imgpath) {
		// TODO Auto-generated method stub
		String sql = "update user set header = ? where id = ?";
		return jdbcTemplate.update(sql, new Object[] { imgpath, userid }) > 0 ? true
				: false;
	}

	@Override
	public boolean addPoint(String userid, String point) {
		// TODO Auto-generated method stub
		String sql = "update user set point=point+? where id = ?";

		return jdbcTemplate.update(sql, new Object[] {point, userid}) > 0 ? true
				: false;
	}

}
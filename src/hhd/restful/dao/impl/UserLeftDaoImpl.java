package hhd.restful.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hhd.restful.bean.User;
import hhd.restful.dao.UserLeftDao;

@Repository
public class UserLeftDaoImpl implements UserLeftDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<User> selectUserInfosByUserid(String userid) {
		// TODO Auto-generated method stub

		String sql = "select * from user where id = ?";
		return jdbcTemplate.query(sql, new Object[] { userid },
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						User user = new User();
						user.setName(rs.getString("name"));
						user.setHeader(rs.getString("header"));
						user.setRank(rs.getInt("rank"));
						user.setRegisterdate(rs.getString("registerDate"));
						user.setPoint(rs.getInt("point"));
						
						return user;
					}
				});

	}

}

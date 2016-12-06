package hhd.restful.dao.impl;

import hhd.restful.dao.AttentionDao;
import hhd.restful.util.IDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AttentionDaoImpl implements AttentionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean insertAttention(String userid, String concerned_userid) {
		// TODO Auto-generated method stub
		String sql = "insert into concern values(?,?,?)";
		return jdbcTemplate.update(sql, new Object[] { IDUtil.getUUID(),
				userid, concerned_userid }) > 0 ? true : false;
	}

	@Override
	public boolean deleteAttention(String userid, String concerned_userid) {
		// TODO Auto-generated method stub
		String sql = "delete from concern where u_id = ? and concerned_u_id = ?";
		return jdbcTemplate.update(sql,
				new Object[] { userid, concerned_userid }) > 0 ? true : false;
	}

}

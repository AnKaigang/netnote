package hhd.restful.dao.impl;


import hhd.restful.bean.User;
import hhd.restful.dao.LoginDao;
import hhd.restful.util.IJdbcTemplate;
import hhd.restful.util.Security;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



@Repository
public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	private IJdbcTemplate jdbcTemplate;
	@Autowired
	private IJdbcTemplate jdbcTemplate2;

	
	@Override
	public User selectUserByAccount(String account){
		// TODO Auto-generated method stub
		String sql = "select * from user where account = ? or mail =? and isEnable=1";
		
		return jdbcTemplate.queryForObject(sql, new Object[]{account,account}, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
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
	public User selectAllUserName(String name){
		// TODO Auto-generated method stub
		String sql = "select * from user where name =? and isEnable=1";
		
		return jdbcTemplate2.queryForObject(sql, new Object[]{name}, new RowMapper<User>(){
			
			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
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
	public int UpdatePassword(String account,String mail,String password){
		
		String sql="update user set password='"+password+"' where account ='"+account+"'  and mail = '"+mail+"' and isEnable=1";
		return jdbcTemplate2.update(sql);
	}
	
	@Override
	public boolean RegisterNewUser(User user){
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[]{user.getId(),user.getName(),user.getPassword(),
				                      user.getMail(),user.getHeader(),user.getRank(),user.getAccount(),
				                      user.getRegisterdate(),user.getPoint(),user.getIsEnable()};
		return jdbcTemplate2.update(sql, params) > 0 ? true : false;
	}
	
}

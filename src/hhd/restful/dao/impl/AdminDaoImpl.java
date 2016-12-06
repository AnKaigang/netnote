/**
 * 
 */
package hhd.restful.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hhd.restful.bean.Admin;
import hhd.restful.dao.AdminDao;
import hhd.restful.util.IJdbcTemplate;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月5日上午9:28:27
 */
@Repository
public class AdminDaoImpl implements AdminDao{

	
	@Autowired
	private IJdbcTemplate jdbcTemplate;
	@Override
	public Admin selectByName(String name) {
		String sql = "select * from admin where name =? ";

		return jdbcTemplate.queryForObject(sql,
				new Object[] { name }, new RowMapper<Admin>() {
					@Override
					public Admin mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub

						Admin Admin = new Admin();
						Admin.setId(rs.getString("id"));
						Admin.setName(rs.getString("name"));
						Admin.setPassword(rs.getString("password"));
						return Admin;
					}
				});
	}
	/* (non-Javadoc)
	 * @see hhd.restful.dao.AdminDao#selectAll()
	 */
	@Override
	public List<Admin> selectAll() {
		String sql = "select * from admin ";

		return jdbcTemplate.query(sql, new RowMapper<Admin>() {
					@Override
					public Admin mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub

						Admin Admin = new Admin();
						Admin.setId(rs.getString("id"));
						Admin.setName(rs.getString("name"));
						Admin.setPassword(rs.getString("password"));
						return Admin;
					}
				});
	}
	/* (non-Javadoc)
	 * @see hhd.restful.dao.AdminDao#selectById(java.lang.String)
	 */
	@Override
	public Admin selectById(String id) {
		String sql = "select * from admin where id =? ";

		return jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new RowMapper<Admin>() {
					@Override
					public Admin mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub

						Admin Admin = new Admin();
						Admin.setId(rs.getString("id"));
						Admin.setName(rs.getString("name"));
						Admin.setPassword(rs.getString("password"));
						return Admin;
					}
				});
	}
	/* (non-Javadoc)
	 * @see hhd.restful.dao.AdminDao#updateAdminById(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateAdminById(String id, String name, String sha1) {
		String sql="";
		if(name!=""&&sha1!=""){
			sql = "update admin set name= '"+name+"' ,password= '"+sha1+"'  where id= '"+id+"'";
		}else if(name!=""){
			sql = "update admin set name= '"+name+"'  where id= '"+id+"'";
		}else if(sha1!=""){
			sql = "update admin set password= '"+sha1+"'  where id= '"+id+"'";
		}else{
			sql = "update admin  where id= '"+id+"'";
		}
		int index=jdbcTemplate.update(sql);
		if(index==0)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see hhd.restful.dao.AdminDao#insertAdmin(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean insertAdmin(String uuid, String name, String password) {
		String sql="insert into admin (id,name,password) values ( '"+uuid+"', '"+name+"','"+password+"')";
		if(jdbcTemplate.update(sql)==0)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see hhd.restful.dao.AdminDao#deleteAdminByid(java.lang.String)
	 */
	@Override
	public boolean deleteAdminByid(String id) {
		String sql="delete from admin where id=?";
		return jdbcTemplate.update(sql, new Object[] { id }) > 0 ? true
				: false;
	}

}

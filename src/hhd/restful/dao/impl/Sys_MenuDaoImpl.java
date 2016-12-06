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

import hhd.restful.bean.Sys_Menu;
import hhd.restful.dao.Sys_MenuDao;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月3日下午4:17:16
 */
@Repository
public class Sys_MenuDaoImpl implements Sys_MenuDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Sys_Menu selectById(int id) {
		// TODO Auto-generated method stub
				String sql = "select * from sys_menu where id=? ";
				
				return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Sys_Menu>(){

					@Override
					public Sys_Menu mapRow(ResultSet rs, int arg1) throws SQLException {
						// TODO Auto-generated method stub
						
						Sys_Menu sys_menu = new Sys_Menu();
						sys_menu.setChildStr(rs.getString("childStr"));
						sys_menu.setId(rs.getInt("id"));
						sys_menu.setPid(rs.getInt("pId"));
						sys_menu.setCode(rs.getString("code"));
						sys_menu.setChildStr(rs.getString("childStr"));
						sys_menu.setParentStr(rs.getString("parentStr"));
						sys_menu.setImage(rs.getString("image"));
						sys_menu.setUrl(rs.getString("url"));
						sys_menu.setTarget(rs.getString("target"));
						sys_menu.setDepth(rs.getString("depth"));
						sys_menu.setKorder(rs.getString("enable"));
						sys_menu.setNote(rs.getString("note"));
						sys_menu.setName(rs.getString("name"));
						return sys_menu;
					}
				});
	}

	@Override
	public List<Sys_Menu> selectALL() {
		String sql = "select * from sys_menu ";
		return jdbcTemplate.query(sql, new RowMapper<Sys_Menu>(){

			@Override
			public Sys_Menu mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Sys_Menu sys_menu = new Sys_Menu();
				sys_menu.setChildStr(rs.getString("childStr"));
				sys_menu.setId(rs.getInt("id"));
				sys_menu.setPid(rs.getInt("pId"));
				sys_menu.setCode(rs.getString("code"));
				sys_menu.setChildStr(rs.getString("childStr"));
				sys_menu.setParentStr(rs.getString("parentStr"));
				sys_menu.setImage(rs.getString("image"));
				sys_menu.setUrl(rs.getString("url"));
				sys_menu.setTarget(rs.getString("target"));
				sys_menu.setDepth(rs.getString("depth"));
				sys_menu.setKorder(rs.getString("enable"));
				sys_menu.setNote(rs.getString("note"));
				sys_menu.setName(rs.getString("name"));
				return sys_menu;
			}
		});
	}

	@Override
	public void deleteById(int id) {

		
	}


	@Override
	public void insert(Sys_Menu m) {
		
		
	}


	@Override
	public void updateById(Sys_Menu m) {

		
	}


	@Override
	public List<Sys_Menu> selectByPid(int pid) {
		String sql = "select * from sys_menu where pId= "+pid;
		return jdbcTemplate.query(sql, new RowMapper<Sys_Menu>(){

			@Override
			public Sys_Menu mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Sys_Menu sys_menu = new Sys_Menu();
				sys_menu.setChildStr(rs.getString("childStr"));
				sys_menu.setId(rs.getInt("id"));
				sys_menu.setPid(rs.getInt("pId"));
				sys_menu.setCode(rs.getString("code"));
				sys_menu.setChildStr(rs.getString("childStr"));
				sys_menu.setParentStr(rs.getString("parentStr"));
				sys_menu.setImage(rs.getString("image"));
				sys_menu.setUrl(rs.getString("url"));
				sys_menu.setTarget(rs.getString("target"));
				sys_menu.setDepth(rs.getString("depth"));
				sys_menu.setKorder(rs.getString("enable"));
				sys_menu.setNote(rs.getString("note"));
				sys_menu.setName(rs.getString("name"));
				return sys_menu;
			}
		});
	}

}

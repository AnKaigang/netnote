package hhd.restful.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hhd.restful.bean.Sort;
import hhd.restful.dao.SortDao;
import hhd.restful.util.IDUtil;

@Repository
public class SortDaoImpl implements SortDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Sort> selectSortsByUserid(String userid) {
		// TODO Auto-generated method stub
		String sql = "select * from sort where u_id = ?";
		return jdbcTemplate.query(sql, new Object[] { userid },
				new RowMapper<Sort>() {

					@Override
					public Sort mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						Sort sort = new Sort();
						sort.setId(rs.getString("id"));
						sort.setUId(rs.getString("u_id"));
						sort.setName(rs.getString("name"));
						sort.setNumber(rs.getInt("number"));
						return sort;
					}
				});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hhd.restful.dao.SortDao#searchSortById(java.lang.String)
	 */
	@Override
	public Sort searchSortById(String sortId) {
		String sql = "select * from sort where id = " + sortId;
		return jdbcTemplate.queryForObject(sql, new RowMapper<Sort>() {

			@Override
			public Sort mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Sort sort = new Sort();
				sort.setId(rs.getString("id"));
				sort.setUId(rs.getString("u_id"));
				sort.setName(rs.getString("name"));
				sort.setNumber(rs.getInt("number"));
				return sort;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hhd.restful.dao.SortDao#searchSortByUsename(java.lang.String)
	 */
	@Override
	public List<Sort> searchSortByUsename(String name) {
		String sql = "select * from sort ,user where sort.u_id=user.id and user.name ='"
				+ name + "'";
		return jdbcTemplate.query(sql, new RowMapper<Sort>() {

			@Override
			public Sort mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Sort sort = new Sort();
				sort.setId(rs.getString("id"));
				sort.setUId(rs.getString("u_id"));
				sort.setName(rs.getString("name"));
				sort.setNumber(rs.getInt("number"));
				return sort;
			}
		});
	}

	@Override
	public Sort selectSortsByid(String sortid) {
		// TODO Auto-generated method stub
		String sql = "select * from sort where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { sortid },
				new RowMapper<Sort>() {

					@Override
					public Sort mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						Sort sort = new Sort();
						sort.setId(rs.getString("id"));
						sort.setUId(rs.getString("u_id"));
						sort.setName(rs.getString("name"));
						sort.setNumber(rs.getInt("number"));
						return sort;

					}
				});
	}

	@Override
	public boolean updateSortNameByid(String sortid, String sortname) {
		// TODO Auto-generated method stub
		String sql = "update sort set name = ? where id = ?";
		return jdbcTemplate.update(sql, new Object[] { sortname, sortid }) > 0 ? true
				: false;
	}

	@Override
	public boolean insertSort(String sortname, String userid) {
		// TODO Auto-generated method stub
		String sql = "insert into sort values(?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] { IDUtil.getUUID(),
				sortname, userid, 0 }) > 0 ? true : false;
	}

	@Override
	public boolean deleteSortByid(String sortid) {
		// TODO Auto-generated method stub
		String sql = "delete from sort where id = ? ";
		return jdbcTemplate.update(sql, new Object[] { sortid }) > 0 ? true
				: false;
	}

	@Override
	public boolean updateSortNumByid(Sort sort) {
		// TODO Auto-generated method stub
		String sql = "update sort set number= ? where id = ? ";
		return jdbcTemplate.update(sql,
				new Object[] { sort.getNumber(), sort.getId() }) > 0 ? true
				: false;
	}

	@Override
	public boolean decreaseSortNumByidAndUserid(String sId, String uId) {
		// TODO Auto-generated method stub
		String sql = "update sort set number=number-1 where id = ? and u_id = ?";
		return jdbcTemplate.update(sql, new Object[] { sId, uId }) > 0 ? true
				: false;
	}
}

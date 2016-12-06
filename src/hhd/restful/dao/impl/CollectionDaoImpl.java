package hhd.restful.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hhd.restful.bean.Collection;
import hhd.restful.dao.CollectionDao;
import hhd.restful.util.IDUtil;

@Repository
public class CollectionDaoImpl implements CollectionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean insertCollect(String userid, String articleid) {
		// TODO Auto-generated method stub
		String sql = "insert into collection values(?,?,?,?)";
		Object[] params = new Object[] { IDUtil.getUUID(), userid, articleid,
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) };
		return jdbcTemplate.update(sql, params) > 0 ? true : false;
	}

	@Override
	public boolean deleteCollect(String userid, String articleid) {
		// TODO Auto-generated method stub
		String sql = "delete from collection where u_id = ? and a_id = ?";
		Object[] params = new Object[] { userid, articleid };
		return jdbcTemplate.update(sql, params) > 0 ? true : false;
	}

	@Override
	public List<Collection> selectCollectionByUserid(String userid) {
		// TODO Auto-generated method stub
		String sql = "select * from collection where u_id = ?";
		
		return jdbcTemplate.query(sql, new Object[]{userid},new RowMapper<Collection>(){

			@Override
			public Collection mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				
				Collection collection = new Collection();
				collection.setId(rs.getString("id"));
				collection.setUId(rs.getString("u_id"));
				collection.setAId(rs.getString("a_id"));
				collection.setDate(rs.getString("date"));
				
				return collection;
			}
			
		});
	}

}

package hhd.restful.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hhd.restful.bean.Concern;
import hhd.restful.dao.ConcernDao;

@Repository
public class ConcernDaoImpl implements ConcernDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	

	@Override
	public List<Concern> selectConcernByUserid(String userid) {
		// TODO Auto-generated method stub
		String sql = "select * from concern where u_id = ?";

		return jdbcTemplate.query(sql, new Object[] { userid },
				new RowMapper<Concern>() {

					@Override
					public Concern mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						
						Concern concern = new Concern();
						concern.setId(rs.getString(1));
						concern.setUId(rs.getString(2));
						concern.setConcernedUId(rs.getString(3));
						return concern;
					}
				});
	}

	@Override
	public List<Concern> selectConcernedByUserid(String userid) {
		// TODO Auto-generated method stub
		String sql = "select * from concern where concerned_u_id = ?";

		return jdbcTemplate.query(sql, new Object[] { userid },
				new RowMapper<Concern>() {

					@Override
					public Concern mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						
						Concern concern = new Concern();
						concern.setId(rs.getString(1));
						concern.setUId(rs.getString(2));
						concern.setConcernedUId(rs.getString(3));
						return concern;
					}
				});
	}

}

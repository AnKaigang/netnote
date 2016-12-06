package hhd.restful.dao.impl;

import hhd.restful.bean.Artitle;
import hhd.restful.dao.ShowDao;
import hhd.restful.util.IJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ShowDaoImpl implements ShowDao {

	@Autowired
	private IJdbcTemplate jdbcTemplate;

	@Override
	public List<Artitle> selectArticalsByUserid(String userid) {
		// TODO Auto-generated method stub

		String sql = "select * from artitle,user where artitle.u_id = user.id and u_id = ? order by date desc";
		return jdbcTemplate.query(sql, new Object[] { userid },
				new RowMapper<Artitle>() {
					@Override
					public Artitle mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						Artitle article = new Artitle();
						article.setId(rs.getString(1));
						article.setContent(rs.getString(2));
						article.setUId(rs.getString(3));
						article.setDate(rs.getString(4));
						article.setAgree(rs.getInt(5));
						article.setDisagree(rs.getInt(6));
						article.setIsopen(rs.getInt(7));
						article.setTitle(rs.getString(8));
						article.setReadnum(rs.getInt(9));
						article.setCommentnum(rs.getInt(10));
						article.setStatus(rs.getInt(11));
						article.setSId(rs.getString(12));
						article.setByzd1(rs.getString("name"));
						return article;
					}
				});
	}
	
	@Override
	public Artitle selectArtitleById(String id) {
		// TODO Auto-generated method stub
		String sql = "select isOpen,status,a.id as id,a.u_id, content, u.name as uname,date,agree,disagree,title,readNum,commentNum,s.name as sort,a.s_id"
				+ " from artitle as a,user as u,sort as s where a.s_id=s.id and a.u_id=u.id and a.id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id},new RowMapper<Artitle>(){

			@Override
			public Artitle mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Artitle artitle=new Artitle();
				artitle.setAddParam1(rs.getString("uname"));//作者名
				artitle.setAddParam2(rs.getString("u_id"));//所属分类
				artitle.setId(rs.getString("id"));
				artitle.setContent(rs.getString("content"));
				artitle.setUId(rs.getString("u_id"));
				artitle.setDate(rs.getString("date"));
				artitle.setAgree(rs.getInt("agree"));
				artitle.setDisagree(rs.getInt("disagree"));
				artitle.setIsopen(rs.getInt("isOpen"));
				artitle.setTitle(rs.getString("Title"));
				artitle.setReadnum(rs.getInt("readNum"));
				artitle.setCommentnum(rs.getInt("commentNum"));
				artitle.setStatus(rs.getInt("status"));
				artitle.setSId(rs.getString("s_id"));
				return artitle;
				
			}
			
		});
	}
	@Override
	public List<Artitle> searchArticalByUseridOrderByDate(String userid) {
		// TODO Auto-generated method stub
		String sql = "select * from artitle where u_id = ? order by date desc";
		return jdbcTemplate.query(sql, new Object[] { userid },
				new RowMapper<Artitle>() {
					@Override
					public Artitle mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						Artitle article = new Artitle();
						article.setId(rs.getString(1));
						article.setContent(rs.getString(2));
						article.setUId(rs.getString(3));
						article.setDate(rs.getString(4));
						article.setAgree(rs.getInt(5));
						article.setDisagree(rs.getInt(6));
						article.setIsopen(rs.getInt(7));
						article.setTitle(rs.getString(8));
						article.setReadnum(rs.getInt(9));
						article.setCommentnum(rs.getInt(10));
						article.setStatus(rs.getInt(11));
						article.setSId(rs.getString(12));
						return article;
					}
				});
	}
}

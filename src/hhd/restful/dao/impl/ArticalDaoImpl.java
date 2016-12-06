package hhd.restful.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hhd.restful.bean.Artitle;
import hhd.restful.dao.ArticalDao;
import hhd.restful.util.IDUtil;

@Repository
public class ArticalDaoImpl implements ArticalDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean insertArtical(Artitle article) {
		// TODO Auto-generated method stub
		String sql = "insert into artitle values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { IDUtil.getUUID(),
				article.getContent(), article.getUId(), article.getDate(),
				article.getAgree(), article.getDisagree(), article.getIsopen(),
				article.getTitle(), article.getReadnum(),
				article.getCommentnum(), article.getStatus(), article.getSId() };

		return jdbcTemplate.update(sql, params) > 0 ? true : false;
	}

	@Override
	public List<Artitle> selectArticalByUserid(String userid) {
		// TODO Auto-generated method stub
		String sql = "select * from artitle where u_id = ? and status<>2";
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

	@Override
	public List<Artitle> selectDraftArtitalByUserid(String userid) {
		// TODO Auto-generated method stub
		String sql = "select * from artitle where u_id = ? and status=1";
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

	@Override
	public List<Artitle> selectRecycleArtitalByUserid(String userid) {
		// TODO Auto-generated method stub
		String sql = "select * from artitle where u_id = ? and status=2";
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

	@Override
	public List<Artitle> selectArticleBySortAndUserid(String userid,
			String sortid) {
		// TODO Auto-generated method stub
		String sql = "select * from artitle where u_id = ? and isOpen=3 and status=0 and s_id=?";
		return jdbcTemplate.query(sql, new Object[] { userid, sortid },
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

	@Override
	public Artitle selectArticleByid(String articleid) {
		// TODO Auto-generated method stub
		String sql = "select * from artitle where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { articleid },
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

	@Override
	public boolean upadteArticle(Artitle article) {
		// TODO Auto-generated method stub
		String sql = "update artitle set title=?,content=?,date=?,isOpen=?,status=?,s_id=?,readNum=?,commentNum=? where id=?";
		Object[] params = new Object[] { article.getTitle(),
				article.getContent(), article.getDate(), article.getIsopen(),
				article.getStatus(), article.getSId(), article.getReadnum(),article.getCommentnum(),article.getId() };
		System.out.println(sql+article.getCommentnum());
		return jdbcTemplate.update(sql, params) > 0 ? true : false;
	}

	@Override
	public boolean deleteArticle(String articleid) {
		// TODO Auto-generated method stub
		String sql = "update artitle set status=2 where id =?";

		return jdbcTemplate.update(sql, new Object[] { articleid }) > 0 ? true
				: false;
	}

	/**
	 * akg 按照日期查找帖子（限制10条）
	 */
	@Override
	public List<Artitle> selectArticleByDate() {
		// TODO Auto-generated method stub
		String sql = "select * from artitle where isopen=3 and status=0  order by date desc ";
		return jdbcTemplate.query(sql, new RowMapper<Artitle>() {

			@Override
			public Artitle mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Artitle artitle = new Artitle();
				artitle.setId(rs.getString("id"));
				artitle.setTitle(rs.getString("title"));
				return artitle;
			}
		});
	}

	/**
	 * 查找赞总数 akg
	 */
	@Override
	public int selectCountAllAgree() {
		String sql = "select  sum(agree)  from artitle ";
		return jdbcTemplate.execute(new PreparedStatementCreator() {
			@Override
			public java.sql.PreparedStatement createPreparedStatement(
					Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				 return conn.prepareStatement( "select  sum(agree)  from artitle "); 
			}}, new PreparedStatementCallback<Integer>() {  

			@Override
			public Integer doInPreparedStatement(java.sql.PreparedStatement pstmt)
					throws SQLException, DataAccessException {

		         pstmt.execute();  

		         ResultSet rs = pstmt.getResultSet();  

		         rs.next();  

		         return rs.getInt(1);  
			}}); 
	}

	/*
	 * 查找评论总数
	 * akg
	 */
	@Override
	public int selectCountAllComment() {
		String sql = "select  count(*)  from comment ";
		 return jdbcTemplate.execute(new PreparedStatementCreator() {  
			@Override
			public java.sql.PreparedStatement createPreparedStatement(
					Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				 return conn.prepareStatement( "select  count(*)  from comment "); 
			}}, new PreparedStatementCallback<Integer>() {  

			@Override
			public Integer doInPreparedStatement(java.sql.PreparedStatement pstmt)
					throws SQLException, DataAccessException {

		         pstmt.execute();  

		         ResultSet rs = pstmt.getResultSet();  

		         rs.next();  

		         return rs.getInt(1);  
			}}); 
	}


	/* 
	 * akg
	 * 查找日记总数
	 */
	@Override
	public int selectCountAllArticle() {
		String sql = "select  count(*)  from artitle where status<>2";
		 return jdbcTemplate.execute(new PreparedStatementCreator() {  
			@Override
			public java.sql.PreparedStatement createPreparedStatement(
					Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				 return conn.prepareStatement( "select  count(*)  from artitle "); 
			}}, new PreparedStatementCallback<Integer>() {  

			@Override
			public Integer doInPreparedStatement(java.sql.PreparedStatement pstmt)
					throws SQLException, DataAccessException {

		         pstmt.execute();  

		         ResultSet rs = pstmt.getResultSet();  

		         rs.next();  

		         return rs.getInt(1);  
			}}); 
	}



	/* akg
	 * @see hhd.restful.dao.ArticalDao#searchArtitalByAgree(int, int)
	 */
	@Override
	public List<Artitle> searchArtitalByAgree(int start, int num) {
		String sql = "select artitle.u_id, artitle.id as artitcleId,title,artitle.content as content,artitle.date as date ,artitle.agree  as agree, readNum,commentNum ,name ,header"
						+ " FROM artitle LEFT JOIN user ON user.id=artitle.u_id "
						+" where isOpen=3 and status=0"
						+ " order by agree desc "
						+ " limit "+start+" , "+num;
		return jdbcTemplate.query(sql, new RowMapper<Artitle>() {

					@Override
					public Artitle mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						Artitle article = new Artitle();
						article.setId(rs.getString("artitcleId"));
						article.setContent(rs.getString("content"));
						article.setDate(rs.getString("date"));
						article.setAgree(rs.getInt("agree"));
						article.setTitle(rs.getString("title"));
						article.setReadnum(rs.getInt("readNum"));
						article.setCommentnum(rs.getInt("commentNum"));
						article.setAddParam1(rs.getString("name"));
						article.setAddParam2(rs.getString("header"));
						article.setByzd1(rs.getString("u_id"));
						return article;
					}
				});
	}

	/* (non-Javadoc)
	 * @see hhd.restful.dao.ArticalDao#searchArticleBySortId(java.lang.String)
	 */
	@Override
	public List<Artitle> searchArticleBySortId(String sortId, int start, int num) {
		String sql = "select artitle.u_id,artitle.id as artitcleId,title,artitle.content as content,artitle.date as date ,artitle.agree  as agree, readNum,commentNum ,name ,header"
				+ " FROM artitle LEFT JOIN user ON user.id=artitle.u_id "
				+" where isOpen=3 and status=0 and artitle.s_id= "+sortId
				+ " order by agree desc "
				+ " limit "+start+" , "+num;
			return jdbcTemplate.query(sql, new RowMapper<Artitle>() {

			@Override
			public Artitle mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Artitle article = new Artitle();
				article.setId(rs.getString("artitcleId"));
				article.setContent(rs.getString("content"));
				article.setDate(rs.getString("date"));
				article.setAgree(rs.getInt("agree"));
				article.setTitle(rs.getString("title"));
				article.setReadnum(rs.getInt("readNum"));
				article.setCommentnum(rs.getInt("commentNum"));
				article.setAddParam1(rs.getString("name"));
				article.setAddParam2(rs.getString("header"));
				article.setByzd1(rs.getString("u_id"));
				return article;
			}
		});
	}

	/* (non-Javadoc)
	 * @see hhd.restful.dao.ArticalDao#searchAllArtitalByOrder(java.lang.String, int, int)
	 */
	@Override
	public List<Artitle> searchAllArtitalByOrder(String where, int start, int num) {
		String sql="";
		if(where=="")
		{
				sql = "select artitle.id as artitcleId,title,artitle.content as content,artitle.date as date ,artitle.agree  as agree, readNum,commentNum ,name ,header"
				+ " FROM artitle LEFT JOIN user ON user.id=artitle.u_id "
				+" where isOpen >1  "
				+ " limit "+start+" , "+num;
		}else{
			sql = "select artitle.id as artitcleId,title,artitle.content as content,artitle.date as date ,artitle.agree  as agree, readNum,commentNum ,name ,header"
					+ " FROM artitle LEFT JOIN user ON user.id=artitle.u_id "
					+" where isOpen >1  " +where
					+ " limit "+start+" , "+num;
		}
		System.out.println(sql);
			return jdbcTemplate.query(sql, new RowMapper<Artitle>() {

			@Override
			public Artitle mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Artitle article = new Artitle();
				article.setId(rs.getString("artitcleId"));
				article.setContent(rs.getString("content"));
				article.setDate(rs.getString("date"));
				article.setAgree(rs.getInt("agree"));
				article.setTitle(rs.getString("title"));
				article.setReadnum(rs.getInt("readNum"));
				article.setCommentnum(rs.getInt("commentNum"));
				article.setAddParam1(rs.getString("name"));
				article.setAddParam2(rs.getString("header"));
				return article;
			}
		});
	}

	@Override
	public boolean deleteArticleTrue(String articleid) {
		// TODO Auto-generated method stub
		String sql = "delete from artitle where id = ?";
		return jdbcTemplate.update(sql, new Object[] { articleid }) > 0 ? true
				: false;
	}

	@Override
	public boolean recoverArticleByid(String articleid) {
		// TODO Auto-generated method stub
		String sql = "update artitle set status=0 where id =?";
		return jdbcTemplate.update(sql, new Object[] {articleid}) > 0 ? true : false;
	}

	@Override
	public boolean addAgree(String articleid, String userid) {
		// TODO Auto-generated method stub
		String sql = "update artitle set agree=agree+1 where id = ?";

		return jdbcTemplate.update(sql, new Object[] { articleid }) > 0 ? true
				: false;
	}

	@Override
	public boolean disagree(String articleid, String userid) {
		// TODO Auto-generated method stub
		String sql = "update artitle set disagree = disagree+1 where id = ?";
		return jdbcTemplate.update(sql, new Object[] { articleid }) > 0 ? true
				: false;
	}

	/* (non-Javadoc)
	 * @see hhd.restful.dao.ArticalDao#selectByDate()
	 */
	@Override
	public List<Artitle> selectByDate() {
		String sql = "select * from artitle where isOpen=3 and status=0 order by date limit 0,10";
		return jdbcTemplate.query(sql, new RowMapper<Artitle>() {

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

	/* (non-Javadoc)
	 * @see hhd.restful.dao.ArticalDao#searchArtitalCount()
	 */
	@Override
	public int searchArtitalCount() {
		String sql = "select  count(*)  from artitle where isOpen=3 and status=0 ";
		 return jdbcTemplate.execute(new PreparedStatementCreator() {  
			@Override
			public java.sql.PreparedStatement createPreparedStatement(
					Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				 return conn.prepareStatement( "select  count(*)  from artitle where isOpen=3 and status=0 "); 
			}}, new PreparedStatementCallback<Integer>() {  

			@Override
			public Integer doInPreparedStatement(java.sql.PreparedStatement pstmt)
					throws SQLException, DataAccessException {

		         pstmt.execute();  

		         ResultSet rs = pstmt.getResultSet();  

		         rs.next();  

		         return rs.getInt(1);  
			}}); 
	}
}

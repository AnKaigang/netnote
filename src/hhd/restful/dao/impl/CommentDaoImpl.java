/**
 * 
 */
package hhd.restful.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hhd.restful.bean.Artitle;
import hhd.restful.bean.Comment;
import hhd.restful.dao.CommentDao;
import hhd.restful.service.ArticleService;
import hhd.restful.util.IJdbcTemplate;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月5日下午6:36:04
 */
@Repository
public class CommentDaoImpl implements CommentDao{

	@Autowired
	private IJdbcTemplate jdbcTemplate;
	@Autowired
	private ArticleService articleService;
	@Override
	public List<Comment> selectAllCommentByDate(String aId) {
		String sql = "select c.a_id as cAId, c.id as cId, c.content as content, c.date as cDate, c.u_id as uId, u1.name as uName,c.reply_u_id as rUId, u1.name as uName , u2.name as rUName  "
				+ " from comment as c LEFT JOIN user as u1 ON  c.u_id=u1.id  LEFT JOIN user as u2 ON u2.id=c.reply_u_id"
				+ " where  c.reply_u_id=0   and  c.reply_id=0 and c.a_id='"+aId+"'  and  c.reply_id='"+"0' order by c.date";
		List<Comment> parentList=jdbcTemplate.query(sql, new RowMapper<Comment>() {
					@Override
					public Comment mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						Comment comment = new Comment();
						comment.setId(rs.getString("cId"));
						comment.setContent(rs.getString("content"));
						comment.setUId(rs.getString("uId"));
						comment.setAId(rs.getString("cAId"));
						comment.setDate(rs.getString("cDate"));
						comment.setReplyId("0");
						comment.setReplyUId(rs.getString("rUId"));
						comment.setUId(rs.getString("uId"));
						comment.setAddParam1(rs.getString("uName"));//评论人
						comment.setAddParam2(rs.getString("rUName"));//bei
						comment.setAddParam3(rs.getString("uId"));
						return comment;
					}
				});
		searchChildList(parentList);
		return parentList;
	}

	public void searchChildList(List<Comment> parentList) {
		for (Comment parent : parentList) {
			String childSql = "select c.a_id as cAId, c.id as cId, c.content as content, c.date as cDate, c.u_id as uId, u1.name as uName,c.reply_u_id as rUId, u1.name as uName, u2.name as rUName"
					+ " from comment as c, artitle as a, user as u1, user as u2"
					+ " where a.id=c.a_id and c.u_id=u1.id and c.reply_u_id=u2.id and c.reply_id='"+parent.getId()+"' ";
			List<Comment> childList = jdbcTemplate.query(childSql,
					new RowMapper<Comment>() {
						@Override
						public Comment mapRow(ResultSet rs, int arg1)
								throws SQLException {
							// TODO Auto-generated method stub
							Comment comment = new Comment();
							comment.setId(rs.getString("cId"));
							comment.setContent(rs.getString("content"));
							comment.setUId(rs.getString("uId"));
							comment.setAId(rs.getString("cAId"));
							comment.setDate(rs.getString("cDate"));
							comment.setReplyId("0");
							comment.setReplyUId(rs.getString("rUId"));
							comment.setUId(rs.getString("uId"));
							comment.setAddParam1(rs.getString("uName"));// 评论人
							comment.setAddParam2(rs.getString("rUName"));// bei
							comment.setAddParam3(rs.getString("uId"));
							return comment;
						}
					});
			if(childList.size()!=0){
				searchChildList(childList);
				parent.setChildList(childList);
			}else{
				return;
			}
		}
	}

	/* (non-Javadoc)
	 * @see hhd.restful.dao.CommentDao#insertReply(hhd.restful.bean.Comment)
	 */
	@Override
	public int insertReply(Comment comment) {
		String sql="insert into comment (id,content,date,u_id,a_id,reply_id,reply_u_id) values(?,?,?,?,?,?,?)";
		return  jdbcTemplate.update(sql, new Object[]{comment.getId(),comment.getContent(),comment.getDate(),comment.getUId(),comment.getAId(),comment.getReplyId(),comment.getReplyUId()});
	}

	/* (non-Javadoc)
	 * @see hhd.restful.dao.CommentDao#insertComment(hhd.restful.bean.Comment)
	 */
	@Override
	public int insertComment(Comment comment) {
		String sql="insert into comment (id,content,date,u_id,a_id,reply_id,reply_u_id) values(?,?,?,?,?,?,?)";
		int number=  jdbcTemplate.update(sql, new Object[]{comment.getId(),comment.getContent(),comment.getDate(),comment.getUId(),comment.getAId(),"0","0"});
		return number;
	}
}

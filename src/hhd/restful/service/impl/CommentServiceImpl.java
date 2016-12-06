/**
 * 
 */
package hhd.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hhd.restful.bean.Comment;
import hhd.restful.dao.CommentDao;
import hhd.restful.service.CommentService;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月5日下午6:34:12
 */
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDao commentDao;
	@Override
	public List<Comment> selectAllCommentByDate(String aId) {
		// TODO Auto-generated method stub
		return commentDao.selectAllCommentByDate(aId);
	}
	/* (non-Javadoc)
	 * @see hhd.restful.service.CommentService#insertReply(hhd.restful.bean.Comment)
	 */
	@Override
	public int insertReply(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.insertReply(comment);
	}
	/* (non-Javadoc)
	 * @see hhd.restful.service.CommentService#insertComment(hhd.restful.bean.Comment)
	 */
	@Override
	public int insertComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.insertComment(comment);
	}

}

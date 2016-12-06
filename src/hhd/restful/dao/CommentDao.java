/**
 * 
 */
package hhd.restful.dao;

import hhd.restful.bean.Comment;

import java.util.List;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月5日下午6:35:03
 */
public interface CommentDao {
		List<Comment>selectAllCommentByDate(String aId);

		/**
		 * @param comment
		 * @return
		 */
		int insertReply(Comment comment);

		/**
		 * @param comment
		 * @return
		 */
		int insertComment(Comment comment);
}

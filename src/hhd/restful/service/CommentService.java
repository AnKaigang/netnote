/**
 * 
 */
package hhd.restful.service;

import hhd.restful.bean.Comment;

import java.util.List;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月5日下午6:32:55
 */
public interface CommentService {
		List<Comment> selectAllCommentByDate(String aId);

		/**
		 * @param comment
		 * @return 
		 */
		int insertReply(Comment comment);

		/**
		 * @param comment1
		 * @return
		 */
		int insertComment(Comment comment1);
}

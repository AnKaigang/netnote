/**
 * 
 */
package hhd.restful.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hhd.restful.bean.Comment;
import hhd.restful.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年6月27日上午11:14:58
 */
@Controller

public class testController {

	@Autowired
	private CommentService commentService;
	@RequestMapping("test1")
	public String testIndex(Model model,String aId)
	{
		Comment comment1=new Comment();
		int i=commentService.insertReply(comment1);
		i=commentService.insertComment(comment1);
		
		return "test";
	}
	
}

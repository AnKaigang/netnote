package hhd.restful.controller;

import hhd.restful.service.ArticleService;
import hhd.restful.service.CollectionService;
import hhd.restful.util.AuthorityVerify;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CollectionController {

	@Autowired
	private CollectionService collectionService;

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "collect")
	@ResponseBody
	public String collect(String articleid, HttpSession session) {

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "user_login";
		}

		if (collectionService.collect(userid, articleid))
			return "true";
		return "false";
	}

	@RequestMapping(value = "discollect")
	public String discollect(String articleid, HttpSession session) {

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "user_login";
		}

		collectionService.discollect(userid, articleid);
		return "redirect:personal_article_collect_list";
	}

	@RequestMapping(value = "agree")
	@ResponseBody
	public String agree(String articleid, HttpSession session) {

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "false";
		}

		if (articleService.agree(articleid, userid))
			return "true";
		return "false";
	}

	@RequestMapping(value = "disagree")
	@ResponseBody
	public String disagree(String articleid, HttpSession session) {
		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "user_login";
		}

		if (articleService.disagree(articleid, userid))
			return "true";
		return "false";
	}
	
	

}

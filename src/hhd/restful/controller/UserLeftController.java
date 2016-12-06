package hhd.restful.controller;

import hhd.restful.bean.User;
import hhd.restful.service.UserLeftService;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Lqy
 *
 */
@Controller
public class UserLeftController {
	
	@Autowired
	private UserLeftService userLeftService;
	
	
//	@RequestMapping(value = "userleft")
//// 跳转到显示个人首页界面
//	public ModelAndView user_left(Model model, HttpSession session) {
//
//		session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");	
//		String userid = (String) session.getAttribute("userid");
//		List<User> userInfo = userLeftService.searchUserInfoByUserid(userid);
//		model.addAttribute("userInfo", userInfo);
//
//		return new ModelAndView("user_left");
//	}
	

}

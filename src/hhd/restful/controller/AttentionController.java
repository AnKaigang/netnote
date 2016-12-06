package hhd.restful.controller;

import javax.servlet.http.HttpSession;

import hhd.restful.service.AttentionService;
import hhd.restful.util.AuthorityVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AttentionController {
	
	@Autowired
	private AttentionService attentionService;
	
	@RequestMapping(value="concern")
    public String concern(Model model,HttpSession session){
		session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");
		
		String userid = (String) session.getAttribute("userid");
		if(!AuthorityVerify.verifyLogin(userid)){
			return "user_login"; 
		}
		return userid;
		
	}
	

	
}

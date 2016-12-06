package hhd.restful.controller;

import hhd.restful.bean.Artitle;
import hhd.restful.bean.Concern;
import hhd.restful.bean.User;
import hhd.restful.service.ArticleService;
import hhd.restful.service.AttentionService;
import hhd.restful.service.ConcernService;
import hhd.restful.service.ShowService;
import hhd.restful.service.UserService;
import hhd.restful.util.AuthorityVerify;
import hhd.restful.util.DeleteHtml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private ShowService showService;

	@Autowired
	private UserService userService;

	@Autowired
	private ConcernService concernService;
	@Autowired
	private AttentionService attentionService;

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "userindex")
	// 跳转到显示个人首页界面
	public ModelAndView homepage(String userid, Model model, HttpSession session)
			throws ParseException {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");

		//userid = "b01ca4a5-3c1a-11e6-92da-e353f6d9";
		String loginuserid = (String) session.getAttribute("userid");

		List<Artitle> articles = showService
				.searchArticalByUserid(userid);
		for (Artitle artitle : articles) {
			String content = artitle.getContent();
			if (content == null)
				continue;
			content = new DeleteHtml().deleHtml(artitle.getContent());
			artitle.setContent(content);
		}
		model.addAttribute("articles", articles);


		if (loginuserid == null) {
			model.addAttribute("concernsign", "0");// 0 daibiao wei deng lu
		} else if (loginuserid.equals(userid)) {
			model.addAttribute("concernsign", "1");// 1 ben ren
		} else {
			List<Concern> userconcerned = concernService
					.searchConcernByUserid(userid);
			int i;
			for (i = 0; i < userconcerned.size(); i++) {  //目标用户 关注了 登陆用户
				if (userconcerned.get(i).getConcernedUId().equals(loginuserid)) {
					model.addAttribute("concernsign", "2");
					break;
				}
			}
			if (userconcerned.size() == i)// 目标用户 未关注 登陆用户
				model.addAttribute("concernsign", "3");
		}





		User user = userService.searchUserByid(userid);
		model.addAttribute("user", user);

		long nowdate = new Date().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long registdate = sdf.parse(user.getRegisterdate()).getTime();
		long datecha = (nowdate - registdate) / (24 * 60 * 60 * 1000);
		model.addAttribute("days", datecha);

		List<Concern> myconcern = concernService.searchConcernByUserid(userid);
		List<Concern> concernme = concernService.searchConcernedByUserid(userid);
		model.addAttribute("myconcern", myconcern);
		model.addAttribute("concernme", concernme);

		List<User> myconcernUserlist = new ArrayList<>();
		List<User> concernmeUserlist = new ArrayList<>();
		for (int i = 0; i < concernme.size(); i++) {
			User user1 = userService.searchUserByid(concernme.get(i).getUId());
			concernmeUserlist.add(user1);
		}
		for (int i = 0; i < myconcern.size(); i++) {
			User user1 = userService.searchUserByid(myconcern.get(i)
					.getConcernedUId());
			myconcernUserlist.add(user1);
		}
		model.addAttribute("myconcernUserlist", myconcernUserlist);
		model.addAttribute("concernmeUserlist", concernmeUserlist);

		if (loginuserid == null) {
			model.addAttribute("concernsignleft", "0");// 0 daibiao wei deng lu
		} else if (loginuserid.equals(userid)) {
			model.addAttribute("concernsignleft", "1");// 1 ben ren
		} else {
			List<Concern> loginuserconcern = concernService
					.searchConcernByUserid(loginuserid);// deng lu zhe guan zhu
														// de ren lie biao
			int i;
			for (i = 0; i < loginuserconcern.size(); i++) {
				if (loginuserconcern.get(i).getConcernedUId().equals(userid)) {
					model.addAttribute("concernsignleft", "2");// 2 deng lu zhe guan
															// zhu le zhe ge ren
															// ,xian shi qu xiao
															// guan zhu
					break;
				}
			}
			if (loginuserconcern.size() == i)
				model.addAttribute("concernsignleft", "3");// 3 dai biao deng lu zhe
														// wei guan zhu zhe ge
														// ren ,xian shi jia
														// guan zhu
		}


		return new ModelAndView("user_index");
	}

	@RequestMapping(value = "personal_info")
	// 跳转到显示个人首页界面
	public ModelAndView personal_info(String userid, Model model,
			HttpSession session) throws ParseException {

		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");



		//userid = "b01ca4a5-3c1a-11e6-92da-e353f6d9";

		String loginuserid = (String) session.getAttribute("userid");

		List<Artitle> articles = showService
				.searchArticalByUseridOrderByDate(userid);
		model.addAttribute("articles", articles);
		DeleteHtml deleteHtml = new DeleteHtml();
		for (int i = 0; i < articles.size(); i++) {
			Artitle article = articles.get(i);
			article.setContent(deleteHtml.deleHtml(article.getContent()));// shan
																			// chu
																			// html
																			// biao
																			// qian
			if (article.getContent().length() > 100)
				article.setContent(article.getContent().substring(0, 100));// jie
																			// qu
																			// qian
																			// 100
																			// ge
																			// zi
		}

		User user = userService.searchUserByid(userid);
		model.addAttribute("user", user);

		long nowdate = new Date().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long registdate = sdf.parse(user.getRegisterdate()).getTime();
		long datecha = (nowdate - registdate) / (24 * 60 * 60 * 1000);
		model.addAttribute("days", datecha);

		List<Concern> myconcern = concernService.searchConcernByUserid(userid);
		List<Concern> concernme = concernService
				.searchConcernedByUserid(userid);
		model.addAttribute("myconcern", myconcern);
		model.addAttribute("concernme", concernme);

		List<User> myconcernUserlist = new ArrayList<>();
		List<User> concernmeUserlist = new ArrayList<>();
		for (int i = 0; i < concernme.size(); i++) {
			User user1 = userService.searchUserByid(concernme.get(i).getUId());
			concernmeUserlist.add(user1);
		}
		for (int i = 0; i < myconcern.size(); i++) {
			User user1 = userService.searchUserByid(myconcern.get(i)
					.getConcernedUId());
			myconcernUserlist.add(user1);
		}
		model.addAttribute("myconcernUserlist", myconcernUserlist);
		model.addAttribute("concernmeUserlist", concernmeUserlist);

		if (loginuserid == null) {
			model.addAttribute("concernsign", "0");// 0 daibiao wei deng lu
		} else if (loginuserid.equals(userid)) {
			model.addAttribute("concernsign", "1");// 1 ben ren
		} else {
			List<Concern> loginuserconcern = concernService
					.searchConcernByUserid(loginuserid);// deng lu zhe guan zhu
														// de ren lie biao
			int i;
			for (i = 0; i < loginuserconcern.size(); i++) {
				if (loginuserconcern.get(i).getConcernedUId().equals(userid)) {
					model.addAttribute("concernsign", "2");// 2 deng lu zhe guan
															// zhu le zhe ge ren
															// ,xian shi qu xiao
															// guan zhu
					break;
				}
			}
			if (loginuserconcern.size() == i)
				model.addAttribute("concernsign", "3");// 3 dai biao deng lu zhe
														// wei guan zhu zhe ge
														// ren ,xian shi jia
														// guan zhu
		}

		return new ModelAndView("personal_info");
	}

	@RequestMapping(value = "addConcern")
	@ResponseBody
	public String addConcern(String concerned_user_id, HttpSession session) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f688");

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "not login";
		}

		if (attentionService.addAttention(userid, concerned_user_id))
			return "true";
		return "false";
	}

	@RequestMapping(value = "deleteConcern")
	@ResponseBody
	public String deleteConcern(String concerned_user_id, HttpSession session) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f688");

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "not login";
		}

		if (attentionService.deleteAttention(userid, concerned_user_id))
			return "true";
		return "false";
	}

	@RequestMapping(value = "editHeader")
	public String editHeader() {
		return "header_edit";
	}

	@RequestMapping(value = "personal_info_detail")
	public String personal_info_detail(HttpSession session,Model model,String userid) {

		User user = userService.searchUserByid(userid);
		model.addAttribute("user",user);
		return "personal_info_detail";
	}

	@RequestMapping(value = "personal_info_edit")
	public String personal_info_edit(HttpSession session,Model model) {

		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "user_login";
		}

		User user = userService.searchUserByid(userid);
		model.addAttribute("user",user);


		return "personal_info_edit";
	}


	@RequestMapping(value = "editAccount")
	@ResponseBody
	public String editAccount(String newaccount, HttpSession session) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "not login";
		}

		if (userService.editAccount(userid,newaccount))
			return "true";
		return "false";
	}

	@RequestMapping(value = "editPassword")
	@ResponseBody
	public String editPassword(String newpassword, HttpSession session,String oldpassword) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "not login";
		}

		User user = userService.searchUserByid(userid);// yanzhengmima
		if(!user.getPassword().equals(oldpassword))
			return "false";


		if (userService.editPassword(userid, newpassword))
			return "true";
		return "false";
	}

	@RequestMapping(value = "editUsername")
	@ResponseBody
	public String editUsername( HttpSession session,String newusername) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "not login";
		}
		session.setAttribute("username", newusername);
		if (userService.editUsername(userid, newusername))
			return "true";
		return "false";
	}

	@RequestMapping(value = "editMail")
	@ResponseBody
	public String editMail(String newmail, HttpSession session,String password) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "not login";
		}

		User user = userService.searchUserByid(userid);// yanzhengmima
		if(!user.getPassword().equals(password))
			return "false";


		if (userService.editMail(userid, newmail))
			return "true";
		return "false";
	}


	@RequestMapping(value = "editUserHeader")
	public String editUserHeader(String imgpath, HttpSession session,RedirectAttributes attrs) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");


		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "user_login";
		}

		userService.editUserHeader(userid, imgpath);
		attrs.addFlashAttribute("userid", userid);
		return "redirect:/personal_info?userid="+userid;
	}


	/*@RequestMapping(value = "userleft")*/
	// 跳转到显示个人首页界面
	public void diffhomepage(String userid, Model model,
			HttpSession session) throws ParseException {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");

		//userid = "b01ca4a5-3c1a-11e6-92da-e353f6d9";

		String loginuserid = (String) session.getAttribute("userid");

		List<Artitle> articles = showService
				.searchArticalByUseridOrderByDate(userid);
		model.addAttribute("articles", articles);
		DeleteHtml deleteHtml = new DeleteHtml();
		for (int i = 0; i < articles.size(); i++) {
			Artitle article = articles.get(i);
			article.setContent(deleteHtml.deleHtml(article.getContent()));// shan
																			// chu
																			// html
																			// biao
																			// qian
			if (article.getContent().length() > 100)
				article.setContent(article.getContent().substring(0, 100));// jie
																			// qu
																			// qian
																			// 100
																			// ge
																			// zi
		}

		User user = userService.searchUserByid(userid);
		model.addAttribute("user", user);

		long nowdate = new Date().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long registdate = sdf.parse(user.getRegisterdate()).getTime();
		long datecha = (nowdate - registdate) / (24 * 60 * 60 * 1000);
		model.addAttribute("days", datecha);

		List<Concern> myconcern = concernService.searchConcernByUserid(userid);
		List<Concern> concernme = concernService.searchConcernedByUserid(userid);
		model.addAttribute("myconcern", myconcern);
		model.addAttribute("concernme", concernme);

		List<User> myconcernUserlist = new ArrayList<>();
		List<User> concernmeUserlist = new ArrayList<>();
		for (int i = 0; i < concernme.size(); i++) {
			User user1 = userService.searchUserByid(concernme.get(i).getUId());
			concernmeUserlist.add(user1);
		}
		for (int i = 0; i < myconcern.size(); i++) {
			User user1 = userService.searchUserByid(myconcern.get(i)
					.getConcernedUId());
			myconcernUserlist.add(user1);
		}
		model.addAttribute("myconcernUserlist", myconcernUserlist);
		model.addAttribute("concernmeUserlist", concernmeUserlist);

		if (loginuserid == null) {
			model.addAttribute("concernsign", "0");// 0 daibiao wei deng lu
		} else if (loginuserid.equals(userid)) {
			model.addAttribute("concernsign", "1");// 1 ben ren
		} else {
			List<Concern> loginuserconcern = concernService
					.searchConcernByUserid(loginuserid);// deng lu zhe guan zhu
														// de ren lie biao
			int i;
			for (i = 0; i < loginuserconcern.size(); i++) {
				if (loginuserconcern.get(i).getConcernedUId().equals(userid)) {
					model.addAttribute("concernsign", "2");// 2 deng lu zhe guan
															// zhu le zhe ge ren
															// ,xian shi qu xiao
															// guan zhu
					break;
				}
			}
			if (loginuserconcern.size() == i)
				model.addAttribute("concernsign", "3");// 3 dai biao deng lu zhe
														// wei guan zhu zhe ge
														// ren ,xian shi jia
														// guan zhu
		}

		/*return new ModelAndView("user_left");*/

	}
	@RequestMapping(value="logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:index";
	}
	@RequestMapping(value = "userbottom")
	public ModelAndView bottom(String userid, Model model) throws ParseException {
		System.out.println("2");
		return new ModelAndView("base/ddiary-detail-bottom");
	}

}

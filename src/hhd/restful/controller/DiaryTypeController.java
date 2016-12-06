package hhd.restful.controller;

import hhd.restful.bean.Sort;
import hhd.restful.service.SortService;
import hhd.restful.util.AuthorityVerify;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DiaryTypeController {

	@Autowired
	private SortService sortService;

	@RequestMapping(value = "diaryType")
	// 跳转到日记分类
	public String diaryType(HttpSession session, Model model) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "user_login";
		}

		List<Sort> sort = sortService.searchSortByUserid(userid);
		model.addAttribute("sorts", sort);

		return "personal_sort_edit";
	}

	@RequestMapping(value = "updateSort")
	@ResponseBody
	public String edit_sort(String sortid, String sortname, Model model,
			HttpSession session) {

		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "user_login";
		}

		try {// buzhidaoweishenme luan ma le
			sortname = new String(sortname.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * System.out.println(sortid); System.out.println(sortname);
		 */

		if (sortService.updateSortNameByid(sortid, sortname))
			return "true";
		return "false";
	}

	@RequestMapping(value = "showType")
	// 显示分类
	public ModelAndView showType(Model model, HttpSession session) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");

		List<Sort> mysort = sortService.searchSortByUserid((String) session
				.getAttribute("userid"));

		model.addAttribute("mysort", mysort);
		return new ModelAndView("diary_type");
	}

	@RequestMapping(value = "addSort")
	@ResponseBody
	public String addSort(Model model, HttpSession session, String sortname) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "false";
		}

		try {// buzhidaoweishenme luan ma le
			sortname = new String(sortname.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (sortService.addSort(sortname, userid)) {
			return "true";
		}
		return "false";

	}

	@RequestMapping(value = "deleteSort")
	@ResponseBody
	public String deleteSort(Model model, HttpSession session, String sortid) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");

		String userid = (String) session.getAttribute("userid");// denglu
																// yanzheng
		if (!AuthorityVerify.verifyLogin(userid)) {
			return "false";
		}
		
		Sort mysort = sortService.searchSortByid(sortid);
		if(mysort.getNumber()>0)
			return "false";
		if (sortService.deleteSortByid(sortid)) {
			return "true";
		}
		return "false";
	}

}

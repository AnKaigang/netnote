/**
 * 
 */
package hhd.restful.controller;

import hhd.restful.bean.Admin;
import hhd.restful.bean.Artitle;
import hhd.restful.bean.Sort;
import hhd.restful.bean.Sys_Menu;
import hhd.restful.bean.User;
import hhd.restful.dao.ArticalDao;
import hhd.restful.service.AdminService;
import hhd.restful.service.ArticleService;
import hhd.restful.service.SortService;
import hhd.restful.service.Sys_MenuService;
import hhd.restful.service.UserService;
import hhd.restful.util.IDUtil;
import hhd.restful.util.PageInfo;
import hhd.restful.util.Security;
import hhd.restful.util.VerifyCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月3日下午2:41:10
 */
@RequestMapping("admin/")
@Controller
public class AdminController {


	@Resource
	private Sys_MenuService sys_MenuService;
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private SortService sortService;
	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;
	
	/*@Autowired
	private AdminService adminService;*/
	
	@RequestMapping("main")
	public String adminIndex(HttpServletRequest request,Model model)
	{
		List<Sys_Menu> list = new ArrayList<Sys_Menu>();
		List<Sys_Menu> list1=new ArrayList<Sys_Menu>();
		list1 = sys_MenuService.selectALL();
		String contextPath=request.getServletContext().getContextPath();
		
		for(Sys_Menu sm1 : list1){
			// 处理图标
			if (sm1.getImage() != null) {
				if (sm1.getImage().startsWith("/")) {
					sm1.setImage((contextPath.endsWith("/") ? contextPath
							.substring(0, contextPath.length() - 1)
							: contextPath)
							+ sm1.getImage());
				}
			}

			if (sm1.getPid() == 0) {
				for (Sys_Menu sm2 : list1) {
					if (sm2.getPid() == sm1.getId()) {
						for (Sys_Menu sm3 : list1) {
							if (sm3.getPid() == sm2.getId()) {
								// 有三级节点
								sm1.getChildMenu().add(sm2);
								break;
							}
						}
					}

				}
				list.add(sm1);
			}
		}
		model.addAttribute("list1",list);
		return "admin/main";
	}
	@RequestMapping("login")
	public String adminLogin()
	{
		return "admin/login";
	}
	@RequestMapping("index")
	public String adminmain()
	{
		return "admin/index";
	}
	
	@RequestMapping("openDiary")
	public String openDiaryManage(PageInfo page,Model model,@RequestParam(required=false) String select, @RequestParam(required=false) String startDate,
			@RequestParam(required=false) String endDate,@RequestParam(required=false) String username)
	{
		String where=" and status = 0 ";
		if(select!=""&&select!=null){
			where+=(" and s_id = '"+select+"' ");
			model.addAttribute("sId", select);
		}
		if(startDate!=""&&startDate!=null){
			where+=(" and date > '"+startDate+"' ");
			model.addAttribute("start", startDate);
		}
		if(endDate!=""&&endDate!=null){
				where+=(" and date < '"+endDate+"' ");
				model.addAttribute("end", endDate);
		}
		if(username!=""&&username!=null){
				where+=(" and user.name = '"+username+"' ");
				model.addAttribute("user", username);
		}
		List<Sort> sortList=sortService.searchSortByUserid("0");
		List<Sort> sortList1=sortService.searchSortByUsename(username);
		for(Sort sort: sortList1){
			sortList.add(sort);
		}
		model.addAttribute("sortList", sortList);
	
			List<Artitle> artitleList=articleService.searchAllArtitalByOrder(where,0, 10000);

			model.addAttribute("articleList", artitleList);
			
		
		return "admin/diary/openDiaryList";
	}
	@RequestMapping("recycleDiary")
	public String recycleDiaryManage(Model model,@RequestParam(required=false) String select, @RequestParam(required=false) String startDate,
			@RequestParam(required=false) String endDate,@RequestParam(required=false) String username)
	{
		String where=" and status=2 ";
		if(select!=""&&select!=null){
			where+=(" and s_id = '"+select+"' ");
			model.addAttribute("sId", select);
		}
		if(startDate!=""&&startDate!=null){
			where+=(" and date > '"+startDate+"' ");
			model.addAttribute("start", startDate);
		}
		if(endDate!=""&&endDate!=null){
				where+=(" and date < '"+endDate+"' ");
				model.addAttribute("end", endDate);
		}
		if(username!=""&&username!=null){
				where+=(" and user.name = '"+username+"' ");
				model.addAttribute("user", username);
		}
		List<Sort> sortList=sortService.searchSortByUserid("0");
		List<Sort> sortList1=sortService.searchSortByUsename(username);
		for(Sort sort: sortList1){
			sortList.add(sort);
		}
		model.addAttribute("sortList", sortList);
		List<Artitle> artitleList=articleService.searchAllArtitalByOrder(where,0, 10000);
		model.addAttribute("articleList", artitleList);
		return "admin/diary/recycleDiaryList";
	}
	
	@RequestMapping("requestDiarySort")
	public @ResponseBody List<Sort> requestDiarySort(String name,Model model)
	{
			List<Sort> sortList=sortService.searchSortByUserid("0");
			List<Sort> sortList1=sortService.searchSortByUsename(name);
			for(Sort sort: sortList1){
				sortList.add(sort);
			}
			return sortList;
	}
	@RequestMapping("requestUserList")
	public @ResponseBody List<User> requestUserList(String name,Model model)
	{
			List<User> userList=userService.selectLikeByUsername(name);
			return userList;
	}
	
	@RequestMapping("rebackDiary")
	public @ResponseBody String rebackDiary(String id)
	{
			if(articleService.recoverArticleByid(id))
			{
				return "sucess";
			}
			return "fail";
	}
	@RequestMapping("deleteRecycleDiary")
	public @ResponseBody String clearDiary(String data)
	{
		String[] idList=data.split(",");
		int i=0;
		for(String id:idList)
		{
			if(articleService.deleteArticleTrueByid(id))
			{
				i++;
			}
		}
		
		return String.valueOf(i);
	}
	
	@RequestMapping("userOnAndOff")
	public @ResponseBody String userOnAndOff(String id)
	{
		User u=userService.selectById(id);
		if(u.getIsEnable()==1)
		{
			if(userService.updateUserStatus(u.getId(),0)==1)
				return "secess";
			else
				return "failure";
		}
		else
		{
			if(userService.updateUserStatus(u.getId(),1)==1)
				return "secess";
			else
				return "failure";
		}
	}
	@RequestMapping("deleteDiary")
	public @ResponseBody String deleteDiary(String data)
	{
		String[] idList=data.split(",");
		int i=0;
		for(String id:idList)
		{
			if(articleService.deleteArticleByid(id))
			{
				i++;
			}
		}
		
		return String.valueOf(i);
	}
	@RequestMapping("administratorUpdate")
	public String administratorUpdate(Model model,@RequestParam(required=false) String username)
	{
		String where="";
		
		if(username!=""&&username!=null){
				where+=(" and id = '"+username+"' ");
				model.addAttribute("user", username);
		}
		List<User> userList=userService.selectAllByOrder(where,0, 10000);
		model.addAttribute("userList", userList);
		return "admin/user/userList";
	}
	@RequestMapping("addAdmin")
	public String addAdmin(Model model,@RequestParam(required=false) String username)
	{
		return "admin/administrator/administratorUpdate";
	}
	@RequestMapping("adminList")
	public String administratorManage(Model model,@RequestParam(required=false) String username)
	{
		List<Admin> adminList=adminService.selectAll();
		model.addAttribute("adminList", adminList);
		return "admin/administrator/administratorList";
	}
	@RequestMapping(value = "verifyCode", method = RequestMethod.GET)
	public String getVerifyCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		VerifyCode vc=new VerifyCode();
		vc.getVerifyCode(req, resp);
		return null;
	}
	/**
	 * 获取密码加密的字符串，用于客户端密码加密
	 * @param request
	 * @return
	 */
    @RequestMapping("passwordCode")
    @ResponseBody
    public String getCode(HttpServletRequest request){
    	String code=UUID.randomUUID()+"@#%%$#***"+UUID.randomUUID();
    	request.getSession().setAttribute("passwordCode", code);
        return code;
         
    }
    
    @RequestMapping("deleteAdmin")
    public  @ResponseBody  String daleteAdmin(HttpServletRequest request, Model model,
			String data) {
		String[] idList = data.split(",");
		int i = 0;
		for (String id : idList) {
			if (adminService.deleteAdminByid(id)) {
				i++;
			}
		}
		
		return String.valueOf(i);
	}
    @RequestMapping("quit")
    public String quitAdmin(HttpServletRequest request,Model model,String id){
    	request.getSession().removeAttribute("name");
    	request.getSession().removeAttribute("adminId");	
        return "admin/login";
         
    }
    @RequestMapping("updateAdmin")
    public String updateAdmin(HttpServletRequest request,Model model,String id){
    	Admin admin=adminService.selectById(id);
    	model.addAttribute("admin", admin);
        return "admin/administrator/administratorUpdate";
         
    }
    @RequestMapping("add")
    public @ResponseBody String add(String id,String name,String ePassword1,Model model){
    	if(id!=null&&id!=""){
    		if(adminService.updateAdminById(id,name,Security.getSHA1(ePassword1))){
    			return "1";
    		}
    	}else{
        	if(adminService.insertAdmin(IDUtil.getUUID(),name,Security.getSHA1(ePassword1))){
        		return "1";
        	}
    	}
    	model.addAttribute("error","失败");
        return "0";
         
    }
    
	@RequestMapping("pass")
	@ResponseBody public Map<String,Object> Login(HttpServletRequest request, Model model,HttpServletResponse response){
		String mname = request.getParameter("username");
		String mpassword = request.getParameter("password");
		String checkCode = request.getParameter("checkCode");
		String bingoCheck= request.getSession().getAttribute("validateCode").toString();
		Map <String ,Object> result=new HashMap<String, Object>();
		int code=adminService.loginCheck(request,mname,mpassword,checkCode,bingoCheck);
		switch(code)
		{
		case 0:
	    	result.put("info", 0);
			result.put("error", "用户名不存在！");
			break;
		case 1:
			request.getSession().setAttribute("name", mname);
			result.put("info", 1);
			break;
		case 2:
	    	result.put("info", 0);
			result.put("error", "验证码不正确！");
			break;
		case 3:
			result.put("info", 0);
			result.put("error", "密码不正确！");
			break;
		default:
			result.put("info", 0);
			result.put("error", "未知错误！");
				break;
		}
		return result;
	}
	@RequestMapping("userList")
	public String userManage(Model model,@RequestParam(required=false) String startRank, @RequestParam(required=false) String endRank,
			@RequestParam(required=false) String startDate,@RequestParam(required=false) String endDate,
			@RequestParam(required=false) String username,
			@RequestParam(required=false) String startPoint,@RequestParam(required=false) String endPoint)
	{
		String where="";
		if(startRank!=""&&startRank!=null){
			where+=(" and rank > "+startRank+" ");
			model.addAttribute("startRank", startRank);
		}
		if(endRank!=""&&endRank!=null){
			where+=(" and rank < "+endRank+" ");
			model.addAttribute("endRank", endRank);
		}
		if(startDate!=""&&startDate!=null){
			where+=(" and registerDate > '"+startDate+"' ");
			model.addAttribute("startDate", startDate);
		}
		if(endDate!=""&&endDate!=null){
				where+=(" and registerDate < '"+endDate+"' ");
				model.addAttribute("endDate", endDate);
		}
		if(username!=""&&username!=null){
				where+=(" and id = '"+username+"' ");
				model.addAttribute("user", username);
		}
		if(startPoint!=""&&startPoint!=null){
			where+=(" and point > "+startPoint+" ");
			model.addAttribute("startPoint", startPoint);
		}
		if(endPoint!=""&&endPoint!=null){
			where+=(" and point < "+endPoint+" ");
			model.addAttribute("endPoint", endPoint);
		}
		List<User> userList=userService.selectAllByOrder(where,0, 10000);
		model.addAttribute("userList", userList);
		return "admin/user/userList";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "menu/left/{pid}")
	public List<Sys_Menu> selectPid(@PathVariable String pid, HttpServletRequest request,Model model){
		List<Sys_Menu> list = sys_MenuService.selectByPid(Integer.valueOf(pid));
		String contextPath=request.getServletContext().getContextPath();
		for(Sys_Menu sm1 : list){
			//处理图标
			if(sm1.getImage()!=null){
				if(sm1.getImage().startsWith("/")){
					sm1.setImage((contextPath.endsWith("/")?contextPath.substring(0, contextPath.length()-1):contextPath)+sm1.getImage());
				}
			}
			//处理链接
			if(sm1.getUrl()!=null){
				if(sm1.getUrl().startsWith("/")){
					sm1.setUrl((contextPath.endsWith("/")?contextPath.substring(0, contextPath.length()-1):contextPath)+sm1.getUrl());
				}
			}
		}
		return list;
	}
}

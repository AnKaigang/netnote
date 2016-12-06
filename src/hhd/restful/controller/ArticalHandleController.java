package hhd.restful.controller;

import hhd.restful.bean.Artitle;
import hhd.restful.bean.Collection;
import hhd.restful.bean.Sort;
import hhd.restful.service.ArticleService;
import hhd.restful.service.CollectionService;
import hhd.restful.service.SortService;
import hhd.restful.service.UserService;
import hhd.restful.util.AuthorityVerify;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ArticalHandleController {

	@Autowired
	private SortService sortService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CollectionService collectionService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "tshtest")
	public String tshuatest(){
		
		
		return "personal_artical_edit";//文章编辑页面
	}
	
	@RequestMapping(value = "personal_article_list")//wenzhangliebiao
	public String personal_article_list(Model model,HttpSession session){
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");	
		
		String userid = (String) session.getAttribute("userid");//denglu yanzheng
		if(!AuthorityVerify.verifyLogin(userid)){
			return "user_login"; 
		}
		
		
		List<Artitle> artitles = articleService.searchArtitalByUserid(userid);
		model.addAttribute("articles", artitles);
		
		
		List<Sort> sorts =  sortService.searchSortByUserid(userid);
		model.addAttribute("sorts",sorts);
		
		return "personal_article_list";//文章列表页面
	}
	
	@RequestMapping(value = "personal_article_draft_list")//caogaoxiang
	public String personal_article_draft_list(Model model,HttpSession session){
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");	
		
		String userid = (String) session.getAttribute("userid");
		
		if(!AuthorityVerify.verifyLogin(userid)){
			return "user_login"; 
		}
		
		
		
		List<Artitle> artitles = articleService.searchDraftArtitalByUserid(userid);
		model.addAttribute("articles", artitles);
		
		List<Sort> sorts =  sortService.searchSortByUserid(userid);
		model.addAttribute("sorts",sorts);
				
		return "personal_article_list";//文章列表页面
	}
	
	@RequestMapping(value = "personal_article_recycle_list")//hui shou zhan
	public String personal_article_recycle_list(Model model,HttpSession session){
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");	
		
		String userid = (String) session.getAttribute("userid");

		
		if(!AuthorityVerify.verifyLogin(userid)){
			return "user_login"; 
		}
		
		
		List<Artitle> artitles = articleService.searchRecycleArtitalByUserid(userid);
		model.addAttribute("articles", artitles);
		
		List<Sort> sorts =  sortService.searchSortByUserid(userid);
		model.addAttribute("sorts",sorts);
		
		model.addAttribute("deletepage","1");
		
		return "personal_article_list";//文章列表页面
	}
	
	@RequestMapping(value = "personal_article_sorted_list")//fenlei wenzhang
	public String personal_article_sorted_list(Model model,HttpSession session,String sortid){
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");	
		
		String userid = (String) session.getAttribute("userid");
		
		if(!AuthorityVerify.verifyLogin(userid)){
			return "user_login"; 
		}
		
		List<Artitle> artitles = articleService.searchArticleBySortAndUserid(userid, sortid);
		model.addAttribute("articles", artitles);
		
		List<Sort> sorts =  sortService.searchSortByUserid(userid);
		model.addAttribute("sorts",sorts);
		
		return "personal_article_list";//文章列表页面
	}
	
	@RequestMapping(value="personal_article_collect_list")
	public String personal_article_collect_list(Model model,HttpSession session){
		
		String userid = (String) session.getAttribute("userid");
		
		if(!AuthorityVerify.verifyLogin(userid)){
			return "user_login"; 
		}
		
	
		
		
		List<Sort> sorts =  sortService.searchSortByUserid(userid);
		model.addAttribute("sorts",sorts);
		
		List<Collection> mycollections =  collectionService.searchCollectionByUserid(userid);
		
		List<Artitle> articles = new ArrayList<>();
		for(int i = 0;i<mycollections.size();i++){
			Artitle at = articleService.searchArticleById(mycollections.get(i).getAId());
			articles.add(at);
		}
		
		model.addAttribute("articles",articles);
		
		
		return "personal_article_collect";
		
		
	}
	

	@RequestMapping(value = "edit_article")
	public String edit_Article(String articleid,Model model,HttpSession session){
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");
		
		String userid = (String) session.getAttribute("userid");
		
		
		if(!AuthorityVerify.verifyLogin(userid)&&AuthorityVerify.verifyOperateArticle(userid,articleid)){
			return "user_login"; 
		}
		
		Artitle article =  articleService.searchArticleById(articleid);
		model.addAttribute("article",article);
		
		
		List<Sort> defaultSort = sortService.searchSortByUserid("0");
		List<Sort> userSort = sortService.searchSortByUserid(userid);
		model.addAttribute("defaultSort",defaultSort);
		model.addAttribute("userSort",userSort);
		
		
		return "personal_artical_edit_exist";
	}
	
	
	
	
	
	
	@RequestMapping(value = "addArtical")//跳转到增加日记页面
	public String addArtical(HttpSession session,Model model) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");
		
		String userid = (String) session.getAttribute("userid");
		
		if(!AuthorityVerify.verifyLogin(userid)){
			return "user_login"; 
		}
		List<Sort> defaultSort = sortService.searchSortByUserid("0");
		List<Sort> userSort = sortService.searchSortByUserid(userid);
		model.addAttribute("defaultSort",defaultSort);
		model.addAttribute("userSort",userSort);
		
		
		List<Sort> sorts =  sortService.searchSortByUserid(userid);
		model.addAttribute("sorts",sorts);
		
		return "personal_artical_edit";
	}
	

	@RequestMapping(value = "updateArtical")//gengxinwenzhang
	public String upadteArtical(HttpSession session,Model model,Artitle article) {
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");
		
		
		String userid = (String) session.getAttribute("userid");
		
		
		if(!AuthorityVerify.verifyLogin(userid)&&AuthorityVerify.verifyOperateArticle(userid,article.getId())){
			return "user_login"; 
		}
		
		
		
		article.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//gengxin shi jian
		articleService.updateArticle(article);
		
		
		List<Sort> sorts =  sortService.searchSortByUserid(userid);
		model.addAttribute("sorts",sorts);
		
		return "redirect:/personal_article_list";
	}
	
			
	@RequestMapping(value = "delete_article")
	public String delete_article(String articleid,HttpSession session,Model model){
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");
		String userid = (String) session.getAttribute("userid");
		
		if(!AuthorityVerify.verifyLogin(userid)&&AuthorityVerify.verifyOperateArticle(userid,articleid)){
			return "user_login"; 
		}
		
		
		articleService.deleteArticleByid(articleid);//buzhenzhengshanchu,status gaicehng 2
		
		return "redirect:/personal_article_list";
	}
	
	
	
	@RequestMapping(value = "recover_article")//huan yuan shanchu riji 
	public String recover_article(String articleid,HttpSession session,Model model){
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");
		String userid = (String) session.getAttribute("userid");
		
		if(!AuthorityVerify.verifyLogin(userid)&&AuthorityVerify.verifyOperateArticle(userid,articleid)){
			return "user_login"; 
		}
		
		
		articleService.recoverArticleByid(articleid);//huanyuan,status gaicehng 0
		
		return "redirect:/personal_article_recycle_list";
	}
	
	
	
	@RequestMapping(value = "delete_article_true")
	public String delete_article_true(String articleid,HttpSession session){
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");
		String userid = (String) session.getAttribute("userid");
		
		if(!AuthorityVerify.verifyLogin(userid)&&AuthorityVerify.verifyOperateArticle(userid,articleid)){
			return "user_login"; 
		}
		
		
		Artitle article =  articleService.searchArticleById(articleid);
		
		sortService.decreaseSortNumberByid(article.getSId(),article.getUId());//fen lei shu mu jian yi
		
		articleService.deleteArticleTrueByid(articleid);//zhen zheng shan chu 
		
		
		return "redirect:/personal_article_recycle_list";
	}
	
	
	@RequestMapping(value = "storeArtical")//存储日记
	public String storeArtical(Model model, Artitle artical,String defaultsort, HttpSession session) {
		
		//session.setAttribute("userid", "b01ca4a5-3c1a-11e6-92da-e353f6d9");
		String userid = (String) session.getAttribute("userid");
		
		if(!AuthorityVerify.verifyLogin(userid)){
			return "user_login"; 
		}
		
		artical.setAgree(0);
		artical.setCommentnum(0);
		artical.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		artical.setDisagree(0);
		artical.setReadnum(0);
		artical.setUId(userid);
		
		articleService.addArticle(artical);
		
		Sort sort = sortService.searchSortByid(artical.getSId());//gai fen lei wen zhang jia yi 
		sort.setNumber(sort.getNumber()+1);
		sortService.updateSortNumByid(sort);
		
		List<Sort> sorts =  sortService.searchSortByUserid(userid);
		model.addAttribute("sorts",sorts);
		
		
		userService.addPoint(userid, "5");
		return "redirect:/personal_article_list";
	}

}

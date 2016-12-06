package hhd.restful.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import hhd.restful.bean.Artitle;
import hhd.restful.bean.Comment;
import hhd.restful.bean.Concern;
import hhd.restful.bean.User;
import hhd.restful.service.ArticleService;
import hhd.restful.service.CollectionService;
import hhd.restful.service.CommentService;
import hhd.restful.service.ConcernService;
import hhd.restful.service.ShowService;
import hhd.restful.service.UserService;
import hhd.restful.util.DateUtil;
import hhd.restful.util.DateUtil1;
import hhd.restful.util.IDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



/**
 * @作者：Administrator--李基铖
**/
@Controller
public class ShowController {

	@Autowired
	private ShowService showService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private ConcernService concernService;
	@Autowired
	private CollectionService collectionService;
	
	
	@RequestMapping(value = "reply")
	public ModelAndView reply(Model model,String aId,String rId,String id,String rUId)
	{
		model.addAttribute("aId", aId);
		model.addAttribute("rId", id);
		model.addAttribute("rUId", rUId);
		return new ModelAndView("reply");
		
	}
	@RequestMapping(value = "addReply")
	 public @ResponseBody String  addReply(String aId,String rId,String content,String rUId,HttpSession session)
	{
		Comment comment=new Comment();
		comment.setId(IDUtil.getUUID());
		comment.setAId(aId);    
		comment.setContent(content);
		comment.setDate(DateUtil.getNowDateTime());
		comment.setReplyId(rId);
		comment.setUId(session.getAttribute("userid").toString());
		comment.setReplyUId(rUId);
		int num=commentService.insertReply(comment);
		if(num==1){
			return "1";
		}
		return "0";
		
	}
	@RequestMapping(value = "addComment")
	public String addComment(Model model,String aId,String rId,String content,String rUId,HttpSession session)
	{
		Comment comment=new Comment();
		comment.setId(IDUtil.getUUID());
		comment.setAId(aId);
		comment.setContent(content);
		comment.setDate(DateUtil.getNowDateTime());
		comment.setReplyId("0");
		comment.setUId(session.getAttribute("userid").toString());
		int num=commentService.insertComment(comment);
		String error="";
		if(num==1){
			Artitle article= articleService.searchArticleById(aId);
			article.setCommentnum(article.getCommentnum()+1);
			articleService.updateArticle(article);
		}else{
			error= "评论失败！";
		}
		
		userService.addPoint(session.getAttribute("userid").toString(), "2");
		
		return "redirect:articledetail?articleid="+aId+"&error="+error;
	}
	@RequestMapping(value = "articledetail")
	public ModelAndView showArticleDetail(Model model,String articleid,String error,HttpSession session) throws ParseException
	{
		//articleid = "5da09ab25cfc4ffea79586dffe3cc7a3";	
		model.addAttribute("error", error);
		Artitle artitle =  showService.isShow(articleid);
		List<Comment> parentList= commentService.selectAllCommentByDate(articleid);
		artitle.setReadnum(artitle.getReadnum()+1);
		articleService.updateArticle(artitle);
		for(Comment comment:parentList){
			List<Comment> childList=new ArrayList<Comment>();
			if(comment.getChildList()!=null){
				for(Comment c:comment.getChildList()){
					childList.add(c);
				}
			addChld(comment.getChildList(), childList);
			}
			comment.setChildList(childList);
		}
		model.addAttribute("parentList", parentList);
		model.addAttribute("artitle",artitle);
		
		
		
		//left
		String loginuserid = (String) session.getAttribute("userid");
		User user = userService.searchUserByid(artitle.getUId());
		model.addAttribute("user", user);

		long nowdate = new Date().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long registdate = sdf.parse(user.getRegisterdate()).getTime();
		long datecha = (nowdate - registdate) / (24 * 60 * 60 * 1000);
		model.addAttribute("days", datecha);

		List<Concern> myconcern = concernService.searchConcernByUserid(artitle.getUId());
		List<Concern> concernme = concernService.searchConcernedByUserid(artitle.getUId());
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
		} else if (loginuserid.equals(artitle.getUId())) {
			model.addAttribute("concernsignleft", "1");// 1 ben ren
		} else {
			List<Concern> loginuserconcern = concernService
					.searchConcernByUserid(loginuserid);// deng lu zhe guan zhu
														// de ren lie biao
			int i;
			for (i = 0; i < loginuserconcern.size(); i++) {
				if (loginuserconcern.get(i).getConcernedUId().equals(artitle.getUId())) {
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
		
		
		//collect
		List<hhd.restful.bean.Collection> loginuserCollect =  collectionService.searchCollectionByUserid(loginuserid);
		String isCollected = "0";
		for(int i = 0;i<loginuserCollect.size();i++)
		{
			if(loginuserid.equals(artitle.getUId()))
			{
				isCollected = "1";
			}
		}
		model.addAttribute("isCollected",isCollected);
		
		return new ModelAndView("article_detail");
		
	}
	public void addChld(List<Comment> parentList,List<Comment> common){
		for (Comment parent : parentList) {
			List<Comment> childList=parent.getChildList();
			if(childList!=null&&childList.size()!=0){
				for(Comment c:childList){
					common.add(c);
				}
				addChld(childList,common);
			}else{
				return;
			}
		}
	}
}

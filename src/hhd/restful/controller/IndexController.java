/**
 * 
 */
package hhd.restful.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hhd.restful.bean.Artitle;
import hhd.restful.bean.Sort;
import hhd.restful.bean.User;
import hhd.restful.service.ArticleService;
import hhd.restful.service.SortService;
import hhd.restful.service.UserService;
import hhd.restful.util.DeleteHtml;
import hhd.restful.util.PageInfo;
import hhd.restful.util.addCokkies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页跳转操作
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年6月28日上午10:13:29
 */

@Controller
public class IndexController {
		
	
		@Autowired
		private UserService userService;
		
		@Autowired
		private SortService sortService;
		
		@Autowired
		private ArticleService articleService;
	
		
		@RequestMapping("indexContent")
		public String indexContent(String sortId,Model model,PageInfo page)
		{
				int count= articleService.searchArtitalCount();
				page.setBeginPage(1);
				page.setPage(1);
				page.setPageSize(10);
				page.setCount(count);
				List<Artitle> goodArticleList=articleService.searchArticleBySortId(sortId,0,page.getCount());
				for( Artitle artitle : goodArticleList)
				{
					String content=artitle.getContent();
					if(content==null)
						continue;
					content=new DeleteHtml().deleHtml(artitle.getContent());
					if(content.length()<100)
					{
						artitle.setContent(content+"...");
						continue;
					}
					artitle.setContent(content.substring(0, 100)+"...");
				}
				model.addAttribute("goodArticleList", goodArticleList);
				
			Sort sort=sortService.searchSortById(sortId);
			model.addAttribute("sort", sort);
			List<Sort> sortList=sortService.searchSortByUserid("0");
			model.addAttribute("sortList", sortList);
			List<User> userList=userService.selectByRank(0,10);
			model.addAttribute("userList", userList);
			List<Artitle> articleList=articleService.selectByDate();
			model.addAttribute("articleList",articleList);
			int agreeCount=articleService.selectCountAllAgree();
			model.addAttribute("agreeNum",agreeCount);
			int commentCount=articleService.selectCountAllComment();
			model.addAttribute("commentNum", commentCount);
			int articleCount=articleService.selectCountAllComment();
			model.addAttribute("articletNum", articleCount);
			return "index";
		}
		
		/**
		 * 点赞时判断是否已经赞过
		 * 将文档id与点赞人id 做name，点赞人id做value存放在浏览器的cookie里
		 * 每次点赞时进行判断
		 * @param request
		 * @param response
		 * @return
		 * @throws java.io.IOException
		 */
		@RequestMapping(value="IsAgreed")
		@ResponseBody
		public String Isagreed(HttpServletRequest request,HttpServletResponse response)throws IOException{
			
			String articalid = request.getParameter("agreearticleid");
			String agreeuserid =request.getParameter("AgreeUserId");
			Cookie cookie = addCokkies.getCookieByName(request, articalid+agreeuserid);
			if(cookie==null){
				addCokkies.addCookie(response,articalid+agreeuserid, agreeuserid);
				return "false";
			}else{
				return "true";
			}
		}
		
		/**
		 * akg 
		 * 首页跳转
		 * @return
		 */
		@RequestMapping("index")
		public String index(Model model,PageInfo page)
		{ 	
				int count= articleService.searchArtitalCount();
				page.setBeginPage(1);
				page.setPage(1);
				page.setPageSize(10);
				page.setCount(count);
				List<Artitle> goodArticleList= articleService.searchArtitalByAgree(0,page.getCount());
				for( Artitle artitle : goodArticleList)
				{
					String content=artitle.getContent();
					if(content==null)
						continue;
					content=new DeleteHtml().deleHtml(artitle.getContent());
					if(content.length()<100)
					{
						artitle.setContent(content+"...");
						continue;
					}
					artitle.setContent(content.substring(0, 100)+"...");
				}
				model.addAttribute("goodArticleList", goodArticleList);
	
			List<Sort> sortList=sortService.searchSortByUserid("0");
			model.addAttribute("sortList", sortList);
			List<User> userList=userService.selectByRank(0,10);
			model.addAttribute("userList", userList);
			List<Artitle> articleList=articleService.selectByDate();
			model.addAttribute("articleList",articleList);
			int agreeCount=articleService.selectCountAllAgree();
			model.addAttribute("agreeNum",agreeCount);
			int commentCount=articleService.selectCountAllComment();
			model.addAttribute("commentNum", commentCount);
			int articleCount=articleService.selectCountAllComment();
			model.addAttribute("articletNum", articleCount);
			
			
		
			return "index";
		}
}

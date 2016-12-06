package hhd.restful.util;

import hhd.restful.bean.Artitle;
import hhd.restful.service.ArticleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class AuthorityVerify {
	
	@Autowired
	private static ArticleService articleService;
	
	public static boolean verifyLogin(String userid)//yanzheng denglu
	{
		if(null==userid)
			return false;
		return true;
	}
	
	public static boolean verifyOperateArticle(String userid,String articleid)//yanzheng shifoushizijidewenzhang 
	{
		
		boolean isaccess = false;
		
		List<Artitle> myarticles =  articleService.searchArtitalByUserid(articleid);
		
		for(int i = 0;i<myarticles.size();i++){
			if(myarticles.get(i).getId().equals(articleid))
				isaccess = true;
		}
		return isaccess;
	}

}

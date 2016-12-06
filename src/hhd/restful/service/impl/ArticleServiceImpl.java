package hhd.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hhd.restful.bean.Artitle;
import hhd.restful.dao.ArticalDao;
import hhd.restful.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{

	
	@Autowired
	private ArticalDao articleDao;
	@Override
	public boolean addArticle(Artitle article) {
		// TODO Auto-generated method stub
		return articleDao.insertArtical(article);
	}
	@Override
	public List<Artitle> searchArtitalByUserid(String userid) {
		// TODO Auto-generated method stub
		return articleDao.selectArticalByUserid(userid);
	}
	@Override
	public List<Artitle> searchDraftArtitalByUserid(String userid) {
		// TODO Auto-generated method stub
		return articleDao.selectDraftArtitalByUserid(userid);
	}
	@Override
	public List<Artitle> searchRecycleArtitalByUserid(String userid) {
		// TODO Auto-generated method stub
		return articleDao.selectRecycleArtitalByUserid(userid);
	}
	@Override
	public List<Artitle> searchArticleBySortAndUserid(String userid,
			String sortid) {
		// TODO Auto-generated method stub
		return articleDao.selectArticleBySortAndUserid(userid,sortid);
	}
	@Override
	public Artitle searchArticleById(String articleid) {
		// TODO Auto-generated method stub
		return articleDao.selectArticleByid(articleid);
	}
	@Override
	public boolean updateArticle(Artitle article) {
		// TODO Auto-generated method stub
		return articleDao.upadteArticle(article);
	}
	@Override
	public boolean deleteArticleByid(String articleid) {
		// TODO Auto-generated method stub
		return articleDao.deleteArticle(articleid);
	}
	/* (non-Javadoc)
	 * @see hhd.restful.service.ArticleService#selectByDate()
	 */
	@Override
	public List<Artitle> selectByDate() {
		
		return articleDao.selectByDate();
	}

	
	@Override
	public int selectCountAllComment() {
		// TODO Auto-generated method stub
		return articleDao.selectCountAllComment();
	}


	@Override
	public int selectCountAllAgree() {
		// TODO Auto-generated method stub
		return articleDao.selectCountAllAgree();
	}


	/* (non-Javadoc)
	 * @see hhd.restful.service.ArticleService#selectCountAllArticle()
	 */
	@Override
	public int selectCountAllArticle() {
		// TODO Auto-generated method stub
		return articleDao.selectCountAllArticle();
	}
	/* (non-Javadoc)
	 * @see hhd.restful.service.ArticleService#searchArtitalByAgree(int, int)
	 */
	@Override
	public List<Artitle> searchArtitalByAgree(int start, int num) {
		return articleDao.searchArtitalByAgree(start,num);		
	}

	@Override
	public boolean deleteArticleTrueByid(String articleid) {
		// TODO Auto-generated method stub
		return articleDao.deleteArticleTrue(articleid);
		
	}
	@Override
	public boolean recoverArticleByid(String articleid) {
		// TODO Auto-generated method stub
		return articleDao.recoverArticleByid(articleid);
		
	}

	/* (non-Javadoc)
	 * @see hhd.restful.service.ArticleService#searchArticleBySortId(java.lang.String)
	 */
	@Override
	public List<Artitle> searchArticleBySortId(String sortId,int start,int num) {
		return articleDao.searchArticleBySortId(sortId,start,num);
	}
	/* (non-Javadoc)
	 * @see hhd.restful.service.ArticleService#searchAllArtitalByOrder(java.lang.String, int, int)
	 */
	@Override
	public List<Artitle> searchAllArtitalByOrder(String where, int i, int j) {
		return articleDao.searchAllArtitalByOrder(where,i,j);
	}
	@Override
	public boolean agree(String articleid, String userid) {
		// TODO Auto-generated method stub
		return articleDao.addAgree(articleid,userid);
	}
	@Override
	public boolean disagree(String articleid, String userid) {
		// TODO Auto-generated method stub
		return articleDao.disagree(articleid,userid);
	}
	/* (non-Javadoc)
	 * @see hhd.restful.service.ArticleService#searchArtitalCount()
	 */
	@Override
	public int searchArtitalCount() {
		return articleDao.searchArtitalCount();
	}

}

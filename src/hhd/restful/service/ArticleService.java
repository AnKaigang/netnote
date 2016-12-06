/**
 * 
 */
package hhd.restful.service;

import hhd.restful.bean.Artitle;

import java.util.List;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月1日上午8:40:27
 */
public interface ArticleService {
	public List<Artitle> selectByDate();
	public int selectCountAllComment();
	public int selectCountAllAgree();
	public int selectCountAllArticle();
	public boolean addArticle(Artitle article);
	
	public List<Artitle> searchArtitalByUserid(String userid);
	
	public List<Artitle> searchDraftArtitalByUserid(String userid);//caogaoxiang
	
	public List<Artitle> searchRecycleArtitalByUserid(String userid);//hui shou zhan
	
	public List<Artitle> searchArticleBySortAndUserid(String userid, String sortid);//an fen lei cha ge ren ri ji

	public Artitle searchArticleById(String articleid);

	public boolean updateArticle(Artitle article);

	public boolean deleteArticleByid(String articleid);
	/**
	 * akg
	 * 根据赞数查找文章（降序）
	 */
	public List<Artitle> searchArtitalByAgree(int start, int num);

	public boolean deleteArticleTrueByid(String articleid);
	public boolean recoverArticleByid(String articleid);

	/**
	 * akg
	 * 根据分类id获取文章列表
	 * @param sortId
	 * @param num 
	 * @param start 
	 * @return
	 */
	public List<Artitle> searchArticleBySortId(String sortId, int start, int num);
	/**
	 * @param where
	 * @param i
	 * @param j
	 * @return
	 */
	public List<Artitle> searchAllArtitalByOrder(String where, int i, int j);
	public boolean agree(String articleid, String userid);
	public boolean disagree(String articleid, String userid);
	/**
	 * @return
	 */
	public int searchArtitalCount();

}

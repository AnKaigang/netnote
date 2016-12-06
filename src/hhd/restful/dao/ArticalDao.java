package hhd.restful.dao;

import java.util.List;

import hhd.restful.bean.Artitle;

public interface ArticalDao {
	
	public 	boolean insertArtical(Artitle article);

	public List<Artitle> selectArticalByUserid(String userid);

	public List<Artitle> selectDraftArtitalByUserid(String userid);

	public List<Artitle> selectRecycleArtitalByUserid(String userid);

	public List<Artitle> selectArticleBySortAndUserid(String userid,
													  String sortid);

	public Artitle selectArticleByid(String articleid);

	public boolean upadteArticle(Artitle article);

	public boolean deleteArticle(String articleid);
	
	public List<Artitle> selectArticleByDate();
	public int selectCountAllAgree();
	public int selectCountAllComment();
	public int selectCountAllArticle();
	

	public boolean deleteArticleTrue(String articleid);

	public boolean recoverArticleByid(String articleid);

	/**
	 * akg
	 * @param start
	 * @param num
	 * @return
	 */
	public List<Artitle> searchArtitalByAgree(int start, int num);

	/**
	 * akg
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

	public boolean addAgree(String articleid, String userid);

	public boolean disagree(String articleid, String userid);

	/**
	 * @return
	 */
	public List<Artitle> selectByDate();

	/**
	 * @return
	 */
	public int searchArtitalCount();
}

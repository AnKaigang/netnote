package hhd.restful.dao;

import hhd.restful.bean.Sort;

import java.util.List;

public interface SortDao {
	
	public List<Sort> selectSortsByUserid(String userid);

	public Sort selectSortsByid(String sortid);

	public boolean updateSortNameByid(String sortid, String sortname);

	public boolean insertSort(String sortname, String userid);

	public boolean deleteSortByid(String sortid);


	/**
	 * akg
	 * @param sortId
	 * @return
	 */
	public Sort searchSortById(String sortId);


	public boolean updateSortNumByid(Sort sort);

	/**
	 * @param name
	 * @return
	 */
	public List<Sort> searchSortByUsename(String name);

	public boolean decreaseSortNumByidAndUserid(String sId, String uId);



}

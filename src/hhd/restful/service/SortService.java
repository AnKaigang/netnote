package hhd.restful.service;

import hhd.restful.bean.Sort;

import java.util.List;

public interface SortService {
	
	public List<Sort> searchSortByUserid(String userid);


	public Sort searchSortByid(String sortid);

	public boolean updateSortNameByid(String sortid, String sortname);

	public boolean addSort(String sortname, String userid);

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


	public boolean decreaseSortNumberByid(String sId, String uId);

	
	
	

}

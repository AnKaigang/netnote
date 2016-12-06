package hhd.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hhd.restful.bean.Sort;
import hhd.restful.dao.SortDao;
import hhd.restful.service.SortService;

@Service
public class SortServiceImpl implements SortService{
	
	@Autowired
	private SortDao sortDao;

	@Override
	public List<Sort> searchSortByUserid(String userid) {
		// TODO Auto-generated method stub
		return sortDao.selectSortsByUserid(userid);
	}

	@Override
	public Sort searchSortByid(String sortid) {
		// TODO Auto-generated method stub
		return sortDao.selectSortsByid(sortid);
	}

	@Override
	public boolean updateSortNameByid(String sortid, String sortname) {
		// TODO Auto-generated method stub
		return sortDao.updateSortNameByid(sortid,sortname);
	}

	@Override
	public boolean addSort(String sortname, String userid) {
		// TODO Auto-generated method stub
		return sortDao.insertSort(sortname,userid);
	}

	@Override
	public boolean deleteSortByid(String sortid) {
		// TODO Auto-generated method stub
		return sortDao.deleteSortByid(sortid);
	}

	/* (non-Javadoc)
	 * @see hhd.restful.service.SortService#searchSortById(java.lang.String)
	 */
	@Override
	public Sort searchSortById(String sortId) {
		return sortDao.searchSortById(sortId);
	}


	@Override
	public boolean updateSortNumByid(Sort sort) {
		// TODO Auto-generated method stub
		return sortDao.updateSortNumByid(sort);
	}


	/* (non-Javadoc)
	 * @see hhd.restful.service.SortService#searchSortByUsename(java.lang.String)
	 */
	@Override
	public List<Sort> searchSortByUsename(String name) {
		// TODO Auto-generated method stub
		return sortDao.searchSortByUsename(name);
	}

	@Override
	public boolean decreaseSortNumberByid(String sId, String uId) {
		// TODO Auto-generated method stub
		return sortDao.decreaseSortNumByidAndUserid(sId,uId);
	}

}

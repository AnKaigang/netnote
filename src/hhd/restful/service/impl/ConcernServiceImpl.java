package hhd.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hhd.restful.bean.Concern;
import hhd.restful.dao.ConcernDao;
import hhd.restful.service.ConcernService;

@Service
public class ConcernServiceImpl implements ConcernService{

	@Autowired
	public ConcernDao concernDao;
	


	@Override
	public List<Concern> searchConcernByUserid(String userid) {
		// TODO Auto-generated method stub
		return concernDao.selectConcernByUserid(userid);
	}

	@Override
	public List<Concern> searchConcernedByUserid(String userid) {
		// TODO Auto-generated method stub
		return concernDao.selectConcernedByUserid(userid);
	}
	
	

}

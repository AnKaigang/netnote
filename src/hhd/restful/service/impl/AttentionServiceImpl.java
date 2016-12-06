package hhd.restful.service.impl;

import hhd.restful.dao.AttentionDao;
import hhd.restful.service.AttentionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttentionServiceImpl implements AttentionService {
	
	@Autowired
	private AttentionDao attentionDao;
	
	@Override
	public boolean addAttention(String userid,String concerned_userid) {
		// TODO Auto-generated method stub
		return attentionDao.insertAttention(userid,concerned_userid);

	}

	@Override
	public boolean deleteAttention(String userid, String concerned_userid) {
		// TODO Auto-generated method stub
		return attentionDao.deleteAttention(userid,concerned_userid);
	}


}
package hhd.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hhd.restful.bean.Collection;
import hhd.restful.dao.CollectionDao;
import hhd.restful.service.CollectionService;

@Service
public class CollectionServiceImpl implements CollectionService {
	
	@Autowired
	private CollectionDao collectionDao;
	
	@Override
	public boolean collect(String userid, String articleid) {
		// TODO Auto-generated method stub
		return collectionDao.insertCollect(userid,articleid);
	}

	@Override
	public boolean discollect(String userid, String articleid) {
		// TODO Auto-generated method stub
		return collectionDao.deleteCollect(userid,articleid);
	}

	@Override
	public List<Collection> searchCollectionByUserid(String userid) {
		// TODO Auto-generated method stub
		return collectionDao.selectCollectionByUserid(userid);
	}

}

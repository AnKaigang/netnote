package hhd.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hhd.restful.bean.Artitle;
import hhd.restful.dao.ShowDao;
import hhd.restful.service.ShowService;

@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	private ShowDao showDao;

	@Override
	public List<Artitle> searchArticalByUserid(String userid) {
		// TODO Auto-generated method stub
		return showDao.selectArticalsByUserid(userid);
	}

	@Override
	public Artitle isShow(String id) {
		// TODO Auto-generated method stub
		return showDao.selectArtitleById(id);
	}

	@Override
	public List<Artitle> searchArticalByUseridOrderByDate(String userid) {
		// TODO Auto-generated method stub
		return showDao.searchArticalByUseridOrderByDate(userid);
	}

}

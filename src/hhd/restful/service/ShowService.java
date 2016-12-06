package hhd.restful.service;

import hhd.restful.bean.Artitle;

import java.util.List;

public interface ShowService {
	public List<Artitle> searchArticalByUserid(String userid);
	public Artitle isShow(String id);
	public List<Artitle> searchArticalByUseridOrderByDate(String userid);
}

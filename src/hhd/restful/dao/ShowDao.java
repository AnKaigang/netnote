package hhd.restful.dao;

import hhd.restful.bean.Artitle;

import java.util.List;

public interface ShowDao {
	
	public List<Artitle> selectArticalsByUserid(String userid);
	
	public Artitle selectArtitleById(String id);

	public List<Artitle> searchArticalByUseridOrderByDate(String userid);
}

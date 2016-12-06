package hhd.restful.dao;

import hhd.restful.bean.Concern;

import java.util.List;

public interface ConcernDao {

	public List<Concern> selectConcernByUserid(String userid);
	
	public List<Concern> selectConcernedByUserid(String userid);
	
}

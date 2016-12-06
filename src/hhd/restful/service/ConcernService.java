package hhd.restful.service;

import hhd.restful.bean.Concern;

import java.util.List;

public interface ConcernService {
	
	public List<Concern> searchConcernByUserid(String userid);//ziji guanzhuderen 
	
	public List<Concern> searchConcernedByUserid(String userid);//guanzhu ziji de ren

}

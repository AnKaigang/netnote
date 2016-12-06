package hhd.restful.service;


public interface AttentionService {
	
	public boolean addAttention(String userid, String concerned_userid);
	
	public boolean deleteAttention(String userid, String concerned_userid);

}

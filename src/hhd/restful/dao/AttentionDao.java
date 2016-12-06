package hhd.restful.dao;


public interface AttentionDao {


	boolean insertAttention(String userid, String concerned_userid);

	boolean deleteAttention(String userid, String concerned_userid);
}

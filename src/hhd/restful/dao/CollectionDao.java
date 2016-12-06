package hhd.restful.dao;

import hhd.restful.bean.Collection;

import java.util.List;

public interface CollectionDao {

	boolean insertCollect(String userid, String articleid);

	boolean deleteCollect(String userid, String articleid);

	List<Collection> selectCollectionByUserid(String userid);

}

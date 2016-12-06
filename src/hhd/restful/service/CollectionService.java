package hhd.restful.service;

import hhd.restful.bean.Collection;

import java.util.List;

public interface CollectionService {

	boolean collect(String userid, String articleid);

	boolean discollect(String userid, String articleid);

	List<Collection> searchCollectionByUserid(String userid);
}

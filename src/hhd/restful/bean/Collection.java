package hhd.restful.bean;

import java.util.Date;

public class Collection{
	/**
	 *收藏id
	 */
	private String id;
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	/**
	 *收藏人id
	 */
	private String uId;
	public String getUId(){
		return uId;
	}
	public void setUId(String uId){
		this.uId = uId;
	}
	/**
	 *收藏文章的id
	 */
	private String aId;
	public String getAId(){
		return aId;
	}
	public void setAId(String aId){
		this.aId = aId;
	}
	/**
	 *收藏日期
	 */
	private String date;
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date = date;
	}
}
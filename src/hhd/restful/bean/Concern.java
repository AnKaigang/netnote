package hhd.restful.bean;

public class Concern{
	/**
	 *关注id
	 */
	private String id;
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	/**
	 *用户id

	 */
	private String uId;
	public String getUId(){
		return uId;
	}
	public void setUId(String uId){
		this.uId = uId;
	}
	/**
	 *被关注人id
	 */
	private String concernedUId;
	public String getConcernedUId(){
		return concernedUId;
	}
	public void setConcernedUId(String concernedUId){
		this.concernedUId = concernedUId;
	}
}
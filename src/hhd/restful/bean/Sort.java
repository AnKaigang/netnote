package hhd.restful.bean;

public class Sort{
	/**
	 *分类标签id
	 */
	private String id;
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	/**
	 *分类名
	 */
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
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
	 *各类文章数量
	 */
	private Integer number;
	public Integer getNumber(){
		return number;
	}
	public void setNumber(Integer number){
		this.number = number;
	}
}
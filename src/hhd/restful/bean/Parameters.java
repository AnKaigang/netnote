package hhd.restful.bean;

public class Parameters{
	/**
	 *参数id
	 */
	private String id;
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	/**
	 *参数类型id
	 */
	private String dId;
	public String getDId(){
		return dId;
	}
	public void setDId(String dId){
		this.dId = dId;
	}
	/**
	 *参数内容,草稿箱，回收站
	 */
	private String value;
	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value = value;
	}
	/**
	 *键,0,1,2
	 */
	private Integer key;
	public Integer getKey(){
		return key;
	}
	public void setKey(Integer key){
		this.key = key;
	}
}
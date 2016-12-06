package hhd.restful.bean;

import java.util.Date;

public class User{
	/**
	 *用户id
	 */
	private String id;
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	/**
	 *用户名
	 */
	private String name;
	public String getName(){
		return name;
	}
	private int isEnable;
	public void setName(String name){
		this.name = name;
	}
	/**
	 *密码
	 */
	private String password;
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	/**
	 *邮箱
	 */
	private String mail;
	public String getMail(){
		return mail;
	}
	public void setMail(String mail){
		this.mail = mail;
	}
	/**
	 *头像
	 */
	private String header;
	public String getHeader(){
		return header;
	}
	public void setHeader(String header){
		this.header = header;
	}
	/**
	 *等级
	 */
	private Integer rank;
	public Integer getRank(){
		return rank;
	}
	public void setRank(Integer rank){
		this.rank = rank;
	}
	/**
	 *账号
	 */
	private String account;
	public String getAccount(){
		return account;
	}
	public void setAccount(String account){
		this.account = account;
	}
	/**
	 *注册日期
	 */
	private String registerdate;
	public String getRegisterdate(){
		return registerdate;
	}
	public void setRegisterdate(String registerdate){
		this.registerdate = registerdate;
	}
	/**
	 *积分
	 */
	private Integer point;
	public Integer getPoint(){
		return point;
	}
	public void setPoint(Integer point){
		this.point = point;
	}
	/**
	 * @return the isenable
	 */
	public int getIsEnable() {
		return isEnable;
	}
	/**
	 * @param isenable the isenable to set
	 */
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
}
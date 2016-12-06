package hhd.restful.bean;

import java.util.Date;
import java.util.List;

public class Comment{
	/**
	 *评论id
	 */
	private String id;
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	/**
	 *评论内容
	 */
	private String content;
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	/**
	 *评论日期
	 */
	private String date;
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date = date;
	}
	/**
	 *评论人id
	 */
	private String uId;
	public String getUId(){
		return uId;
	}
	public void setUId(String uId){
		this.uId = uId;
	}
	/**
	 *文章id
	 */
	private String aId;
	public String getAId(){
		return aId;
	}
	public void setAId(String aId){
		this.aId = aId;
	}
	/**
	 *所回复评论的id，非回复为0
	 */
	private String replyId;
	public String getReplyId(){
		return replyId;
	}
	public void setReplyId(String replyId){
		this.replyId = replyId;
	}
	/**
	 *被回复人id
	 */
	private String replyUId;
	public String getReplyUId(){
		return replyUId;
	}
	public void setReplyUId(String replyUId){
		this.replyUId = replyUId;
	}
	/**
	 * @return the childList
	 */
	public List<Comment> getChildList() {
		return childList;
	}
	/**
	 * @param childList the childList to set
	 */
	public void setChildList(List<Comment> childList) {
		this.childList = childList;
	}
	/**
	 * @return the addParam1
	 */
	public String getAddParam1() {
		return addParam1;
	}
	/**
	 * @param addParam1 the addParam1 to set
	 */
	public void setAddParam1(String addParam1) {
		this.addParam1 = addParam1;
	}
	/**
	 * @return the addParam2
	 */
	public String getAddParam2() {
		return addParam2;
	}
	/**
	 * @param addParam2 the addParam2 to set
	 */
	public void setAddParam2(String addParam2) {
		this.addParam2 = addParam2;
	}
	/**
	 * @return the addParam3
	 */
	public String getAddParam3() {
		return addParam3;
	}
	/**
	 * @param addParam3 the addParam3 to set
	 */
	public void setAddParam3(String addParam3) {
		this.addParam3 = addParam3;
	}
	/**
	 * @return the addParam4
	 */
	public String getAddParam4() {
		return addParam4;
	}
	/**
	 * @param addParam4 the addParam4 to set
	 */
	public void setAddParam4(String addParam4) {
		this.addParam4 = addParam4;
	}
	private List<Comment> childList;
	private String addParam1;
	private String addParam2;
	private String addParam3;
	private String addParam4;
}
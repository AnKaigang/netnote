package hhd.restful.bean;


public class Artitle{
	/**
	 *文章id
	 */
	private String id;
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	/**
	 *文章内容
	 */
	private String content;
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	/**
	 *用户id
	 */
	public String uId;

	public String getUId() {
		return uId;
	}
	public void setUId(String uId) {
		this.uId = uId;
	}

	/**
	 *发表日期
	 */
	private String date;
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date = date;
	}
	/**
	 *点赞数量
	 */
	private Integer agree;
	public Integer getAgree(){
		return agree;
	}
	public void setAgree(Integer agree){
		this.agree = agree;
	}
	/**
	 *踩数量
	 */
	private Integer disagree;
	public Integer getDisagree(){
		return disagree;
	}
	public void setDisagree(Integer disagree){
		this.disagree = disagree;
	}
	/**
	 *是否公开到所有人，或者自己关注的人
	 */
	private Integer isopen;
	public Integer getIsopen(){
		return isopen;
	}
	public void setIsopen(Integer isopen){
		this.isopen = isopen;
	}
	/**
	 *标题
	 */
	private String title;
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	/**
	 *阅读数量
	 */
	private Integer readnum;
	public Integer getReadnum(){
		return readnum;
	}
	public void setReadnum(Integer readnum){
		this.readnum = readnum;
	}
	/**
	 *评论数量
	 */
	private Integer commentnum;
	public Integer getCommentnum(){
		return commentnum;
	}
	public void setCommentnum(Integer commentnum){
		this.commentnum = commentnum;
	}
	/**
	 *文章状态，草稿、删除等
	 */
	private Integer status;
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	 *分类id
	 */
	private String sId;
	public String getSId(){
		return sId;
	}
	public void setSId(String sId){
		this.sId = sId;
	}

	
	private String byzd1;
	
	private String byzd2;
	
	private String byzd3;
	
	
	public String getByzd1() {
		return byzd1;
	}
	public void setByzd1(String byzd1) {
		this.byzd1 = byzd1;
	}
	public String getByzd2() {
		return byzd2;
	}
	public void setByzd2(String byzd2) {
		this.byzd2 = byzd2;
	}
	public String getByzd3() {
		return byzd3;
	}
	public void setByzd3(String byzd3) {
		this.byzd3 = byzd3;
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
	private String addParam1;
	private String addParam2;
}
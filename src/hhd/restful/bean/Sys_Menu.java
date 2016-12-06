package hhd.restful.bean;


import java.util.ArrayList;
import java.util.List;

public class Sys_Menu {
	private int id;
	private int pid;
	private String name;
	private String code;
	private String folder;
	private String childStr;
	private String parentStr;
	private String image;
	private String url;
	private String target;
	private String type;
	private String depth;
	private String korder;
	private String enable;
	private String note;
	private List<Sys_Menu> childMenu=new ArrayList<Sys_Menu>();
	
	public List<Sys_Menu> getChildMenu() {
		return childMenu;
	}
	public void setChildMenu(List<Sys_Menu> childMenu) {
		this.childMenu = childMenu;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getChildStr() {
		return childStr;
	}
	public void setChildStr(String childStr) {
		this.childStr = childStr;
	}
	public String getParentStr() {
		return parentStr;
	}
	public void setParentStr(String parentStr) {
		this.parentStr = parentStr;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getKorder() {
		return korder;
	}
	public void setKorder(String korder) {
		this.korder = korder;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}

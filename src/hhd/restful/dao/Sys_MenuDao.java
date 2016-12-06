/**
 * 
 */
package hhd.restful.dao;

import java.util.List;

import hhd.restful.bean.Sys_Menu;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月3日下午4:15:30
 */
public interface Sys_MenuDao {
	public Sys_Menu selectById(int id);
		public List<Sys_Menu> selectALL();
		public void deleteById(int id);
		public void insert(Sys_Menu m);
		public void updateById(Sys_Menu m);
		public List<Sys_Menu> selectByPid(int pid);
}


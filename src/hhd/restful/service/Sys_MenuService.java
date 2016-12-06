/**
 * 
 */
package hhd.restful.service;

import java.util.List;

import hhd.restful.bean.Sys_Menu;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月3日下午4:22:39
 */
public interface Sys_MenuService {

	public Sys_Menu selectById(int id);//按ID查询
	public List<Sys_Menu> selectALL();//查询所有
	public void deleteById(int id);//按ID删除
	public void insert(Sys_Menu m);//增加
	public void updateById(Sys_Menu m);//改
	public List<Sys_Menu> selectByPid(int pid);//根据PID得到记录
}

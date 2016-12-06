/**
 * 
 */
package hhd.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hhd.restful.bean.Sys_Menu;
import hhd.restful.dao.Sys_MenuDao;
import hhd.restful.service.Sys_MenuService;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月3日下午4:23:39
 */
@Service
public class Sys_MenuServiceImpl implements Sys_MenuService{
	@Autowired
	private Sys_MenuDao sys_MenuDao;
	
	@Override
	public Sys_Menu selectById(int id) {
		Sys_Menu m = sys_MenuDao.selectById(id);
		return m;
	}

	@Override
	public List<Sys_Menu> selectALL() {
		List<Sys_Menu> list = sys_MenuDao.selectALL();
		return list;
	}

	@Override
	public void deleteById(int id) {
		sys_MenuDao.deleteById(id);
	}

	@Override
	public void insert(Sys_Menu m) {
		sys_MenuDao.insert(m);
	}

	@Override
	public void updateById(Sys_Menu m) {
		sys_MenuDao.updateById(m);

	}

	@Override
	public List<Sys_Menu> selectByPid(int pid) {
		List<Sys_Menu> list = sys_MenuDao.selectByPid(pid);
		return list;
	}
}

/**
 * 
 */
package hhd.restful.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hhd.restful.bean.Admin;
import hhd.restful.dao.AdminDao;
import hhd.restful.service.AdminService;
import hhd.restful.util.Security;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月5日上午9:27:20
 */
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;
	@Override
	public int loginCheck(HttpServletRequest request,String mname, String mpassword, String checkCode,
			String bingoCheck) {
		Admin admin= adminDao.selectByName(mname);
		if (admin != null) {
			if (mpassword.equals(Security.getSHA1(request.getSession().getAttribute("passwordCode")+admin.getPassword()))) {
				if (checkCode.equals(bingoCheck)) {
						return 1;
				} else {
						return 2;
				}
			} else {
					return 3;
			}
		} else {
				return 0;
		}
	}
	/* (non-Javadoc)
	 * @see hhd.restful.service.AdminService#selectAll()
	 */
	@Override
	public List<Admin> selectAll() {
		// TODO Auto-generated method stub
		return adminDao.selectAll();
	}
	/* (non-Javadoc)
	 * @see hhd.restful.service.AdminService#selectById(java.lang.String)
	 */
	@Override
	public Admin selectById(String id) {
		// TODO Auto-generated method stub
		return adminDao.selectById(id);
	}
	/* (non-Javadoc)
	 * @see hhd.restful.service.AdminService#updateAdminById(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateAdminById(String id, String name, String sha1) {
		// TODO Auto-generated method stub
		return adminDao.updateAdminById(id,name,sha1);
	}
	/* (non-Javadoc)
	 * @see hhd.restful.service.AdminService#insertAdmin(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean insertAdmin(String uuid, String name, String password) {
		// TODO Auto-generated method stub
		return adminDao.insertAdmin(uuid,name,password);
	}
	/* (non-Javadoc)
	 * @see hhd.restful.service.AdminService#deleteAdminByid(java.lang.String)
	 */
	@Override
	public boolean deleteAdminByid(String id) {
		// TODO Auto-generated method stub
		return adminDao.deleteAdminByid(id);
	}

}

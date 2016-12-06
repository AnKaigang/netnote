/**
 * 
 */
package hhd.restful.service;

import hhd.restful.bean.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月5日上午9:26:50
 */
public interface AdminService {

	/**
	 * @param mname
	 * @param mpassword
	 * @param checkCode
	 * @param bingoCheck
	 * @return
	 */
	int loginCheck(HttpServletRequest request, String mname, String mpassword, String checkCode,
				   String bingoCheck);

	/**
	 * @return
	 */
	List<Admin> selectAll();

	/**
	 * @param id
	 * @return
	 */
	Admin selectById(String id);

	/**
	 * @param id
	 * @param name
	 * @param sha1
	 */
	boolean updateAdminById(String id, String name, String sha1);

	/**
	 * @param uuid
	 * @param name
	 * @param password
	 * @return
	 */
	boolean insertAdmin(String uuid, String name, String password);

	/**
	 * @param id
	 * @return
	 */
	boolean deleteAdminByid(String id);
		
}

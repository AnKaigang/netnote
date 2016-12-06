/**
 * 
 */
package hhd.restful.dao;

import java.util.List;

import hhd.restful.bean.Admin;

/**
 * @作者：Administrator--安凯刚
 *
 *@日期：2016年7月5日上午9:27:47
 */
public interface AdminDao {

	/**
	 * @return
	 */
	Admin selectByName(String name);

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
	 * @return
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

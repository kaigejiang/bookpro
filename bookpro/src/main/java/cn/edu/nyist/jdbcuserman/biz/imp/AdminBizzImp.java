package cn.edu.nyist.jdbcuserman.biz.imp;

import cn.edu.nyist.jdbcuserman.biz.AdminBizz;
import cn.edu.nyist.jdbcuserman.dao.AdminDao;
import cn.edu.nyist.jdbcuserman.dao.imp.AdminDaoImp;

public class AdminBizzImp implements AdminBizz {

	@Override
	public boolean findAdminByNameAndPwd(String name, String pwd) {
		AdminDao admindao = new AdminDaoImp();
		return admindao.set(name,pwd);
	}

}

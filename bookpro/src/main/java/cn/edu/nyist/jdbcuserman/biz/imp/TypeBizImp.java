package cn.edu.nyist.jdbcuserman.biz.imp;

import java.util.List;

import cn.edu.nyist.jdbcuserman.biz.TypeBiz;
import cn.edu.nyist.jdbcuserman.dao.TypeDao;
import cn.edu.nyist.jdbcuserman.dao.imp.TypeDaoImp;
import cn.edu.nyist.jdbcuserman.vo.TypeVo;

public class TypeBizImp implements TypeBiz {

	@Override
	public List<TypeVo> findAllTypes() {
		TypeDao typeDao = new TypeDaoImp();
		return typeDao.findAllTypes();
	}

}

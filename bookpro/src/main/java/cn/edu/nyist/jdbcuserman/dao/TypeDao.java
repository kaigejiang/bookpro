package cn.edu.nyist.jdbcuserman.dao;

import java.util.List;

import cn.edu.nyist.jdbcuserman.vo.TypeVo;

public interface TypeDao {

	List<TypeVo> findAllTypes();

}

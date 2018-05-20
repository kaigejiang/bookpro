package cn.edu.nyist.jdbcuserman.dao;

import java.util.List;

import cn.edu.nyist.jdbcuserman.vo.BookVo;

public interface BookDao {

	int save(BookVo bookVo);

	List<BookVo> findAllBooks(int pageNumber);

	int findTotal(String name);


}

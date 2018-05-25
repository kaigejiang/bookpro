package cn.edu.nyist.jdbcuserman.biz;

import java.util.List;

import cn.edu.nyist.jdbcuserman.vo.BookVo;

public interface BookBiz {

	int saveBook(BookVo bookVo);

	List<BookVo> findAllBooks(int pageNumber);

	int findTotal(String name);

	boolean del(int id);

	BookVo findBookId(int id);

	int editBook(BookVo bookVo);

}

package cn.edu.nyist.jdbcuserman.biz;

import java.util.List;

import cn.edu.nyist.jdbcuserman.vo.BookVo;

public interface BookBiz {

	int saveBook(BookVo bookVo);

	List<BookVo> findAllBooks(int pageNumber);

	int findTotal();

}

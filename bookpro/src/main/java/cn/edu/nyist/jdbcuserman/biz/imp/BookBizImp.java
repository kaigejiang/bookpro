package cn.edu.nyist.jdbcuserman.biz.imp;

import cn.edu.nyist.jdbcuserman.biz.BookBiz;
import cn.edu.nyist.jdbcuserman.dao.BookDao;
import cn.edu.nyist.jdbcuserman.dao.imp.BookDaoImp;
import cn.edu.nyist.jdbcuserman.vo.BookVo;

public class BookBizImp implements BookBiz {

	public BookBizImp() {
		
	}

	@Override
	public int saveBook(BookVo bookVo) {
		BookDao bookDao = new BookDaoImp();
		return bookDao.save(bookVo);
	}

}

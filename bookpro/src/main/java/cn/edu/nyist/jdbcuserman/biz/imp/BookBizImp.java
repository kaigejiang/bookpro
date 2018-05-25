package cn.edu.nyist.jdbcuserman.biz.imp;

import java.util.List;

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

	@Override
	public List<BookVo> findAllBooks(int pageNumber) {
		BookDao bookDao = new BookDaoImp();
		return bookDao.findAllBooks(pageNumber);
	}

	@Override
	public int findTotal(String name) {
		BookDao bookDao = new BookDaoImp();
		return bookDao.findTotal(name);
	}

	@Override
	public boolean del(int id) {
		BookDao bookDao = new BookDaoImp();
		return bookDao.del(id);
	}

	@Override
	public BookVo findBookId(int id) {
		BookDao bookDao = new BookDaoImp();
		return bookDao.findId(id);
	}

	@Override
	public int editBook(BookVo bookVo) {
		BookDao bookDao = new BookDaoImp();
		return bookDao.edit(bookVo);
	}


}

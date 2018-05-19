package cn.edu.nyist.jdbcuserman.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.jdbcuserman.biz.BookBiz;
import cn.edu.nyist.jdbcuserman.biz.imp.BookBizImp;
import cn.edu.nyist.jdbcuserman.vo.BookVo;
import cn.edu.nyist.jdbcuserman.vo.PageConstant;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 获取参数
		String strPageNumber = request.getParameter("pageNumber");
		int pageNumber;
		try {
			pageNumber= Integer.parseInt(strPageNumber);
		} catch (NumberFormatException e) {
			pageNumber = 1;//转到第一页，默认页面
		}
		//2 调用业务层
		BookBiz bookBiz = new BookBizImp();
		List<BookVo> ls = bookBiz.findAllBooks(pageNumber);
		int total = bookBiz.findTotal();
		//3 给用户响应
		if(total % PageConstant.PAGE_SIZE==0) {
			request.setAttribute("totalPage", total/PageConstant.PAGE_SIZE);
		}else {
			request.setAttribute("totalPage", total/PageConstant.PAGE_SIZE+1);
		}
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("ls", ls);
		request.getRequestDispatcher("bookList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

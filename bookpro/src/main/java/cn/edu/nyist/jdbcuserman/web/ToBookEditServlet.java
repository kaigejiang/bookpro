package cn.edu.nyist.jdbcuserman.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.jdbcuserman.biz.BookBiz;
import cn.edu.nyist.jdbcuserman.biz.imp.BookBizImp;
import cn.edu.nyist.jdbcuserman.vo.BookVo;


@WebServlet("/toBookEdit")
public class ToBookEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ToBookEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 获取参数
		String strid = request.getParameter("id");
		int id = Integer.parseInt(strid);
		//2 调用业务层
		BookBiz bookBiz = new BookBizImp();
		BookVo bookVo = bookBiz.findBookId(id);
		//3 给响应
		request.setAttribute("bookVo", bookVo);
		request.getRequestDispatcher("bookEdit.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

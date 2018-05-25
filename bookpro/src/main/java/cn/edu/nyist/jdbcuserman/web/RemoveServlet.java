package cn.edu.nyist.jdbcuserman.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.jdbcuserman.biz.BookBiz;
import cn.edu.nyist.jdbcuserman.biz.imp.BookBizImp;

@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
		//调用业务层
			BookBiz bookBiz = new BookBizImp();
			boolean ret = bookBiz.del(id);
		//给出响应
			if(!ret) {
				request.setAttribute("msg", "删除失败");
			}
			request.getRequestDispatcher("bookList").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

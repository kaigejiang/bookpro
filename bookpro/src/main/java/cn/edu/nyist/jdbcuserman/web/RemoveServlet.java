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
		//��ȡ����
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
		//����ҵ���
			BookBiz bookBiz = new BookBizImp();
			boolean ret = bookBiz.del(id);
		//������Ӧ
			if(!ret) {
				request.setAttribute("msg", "ɾ��ʧ��");
			}
			request.getRequestDispatcher("bookList").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

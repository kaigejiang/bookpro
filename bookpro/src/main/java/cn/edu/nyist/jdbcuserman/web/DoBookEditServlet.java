package cn.edu.nyist.jdbcuserman.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cn.edu.nyist.jdbcuserman.biz.BookBiz;
import cn.edu.nyist.jdbcuserman.biz.imp.BookBizImp;
import cn.edu.nyist.jdbcuserman.util.MyBeanUtils;
import cn.edu.nyist.jdbcuserman.vo.BookVo;

/**
 * Servlet implementation class DoBookEditServlet
 */
@WebServlet("/doBookEdit")
@MultipartConfig
public class DoBookEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoBookEditServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// �޸��������
		// 1 ��ȡ����
		Part part = request.getPart("photo");
		System.out.println("payt="+part);
		String filename = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		// ���ie���������������
		String newFileName ="";
		if (!filename.equals("")) {
			
			filename = filename.lastIndexOf("\\") == -1 ? filename : filename.substring((filename.lastIndexOf("\\") + 1));
			
			String ext = filename.substring(filename.lastIndexOf('.') + 1);
			newFileName = UUID.randomUUID().toString() + "." + ext;
			part.write(request.getServletContext().getRealPath("upload/" + newFileName));
		}
		BookVo bookVo = new BookVo();
		// ��������
		MyBeanUtils.populate(bookVo, request.getParameterMap(), "yyyy-MM-dd");
		if (!filename.equals("")) {
		bookVo.setPhoto(newFileName);
		}
		// ����ҵ���
		BookBiz booBiz = new BookBizImp();
		int ret = booBiz.editBook(bookVo);
		// ���û���ʾ
		response.setContentType("text/html;charset=utf-8");
		if (ret > 0) {
			//
			response.getWriter().write("�޸ĳɹ�");
		} else {
			request.setAttribute("msg", "�޸�ʧ��");
			request.setAttribute("bookVo", bookVo);
			request.getRequestDispatcher("bookEdit.jsp").forward(request, response);;
		}
	}

}

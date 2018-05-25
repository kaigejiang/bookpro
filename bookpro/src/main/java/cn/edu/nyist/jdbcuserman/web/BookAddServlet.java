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

@WebServlet("/bookAdd")
@MultipartConfig
public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BookAddServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//修改字体编码
		//得到phpto的part
		Part part = request.getPart("photo");
		System.out.println(part);
		String filename = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		//解决ie浏览器不兼容问题
		filename = filename.lastIndexOf("\\")==-1 ? filename : filename.substring((filename.lastIndexOf("\\")+1));
		
		String ext = filename.substring(filename.lastIndexOf('.')+1);
		String newFileName = UUID.randomUUID().toString()+"."+ext;
		part.write(request.getServletContext().getRealPath("upload/"+newFileName));
		
		BookVo bookVo = new BookVo();
		//参数储存
		MyBeanUtils.populate(bookVo, request.getParameterMap(), "yyyy-MM-dd");
		bookVo.setPhoto(newFileName);
		//调用业务层
		BookBiz booBiz = new BookBizImp();
		//制定一个值来确定是否保存
		int ret = booBiz.saveBook(bookVo);
		//给用户提示
		response.setContentType("text/html;charset=utf-8");
		if (ret > 0) {
			//
			response.getWriter().write("保存成功");
		} else {
			
			request.setAttribute("msg","保存失败");
			request.getRequestDispatcher("bookAdd.jsp").forward(request, response);
		}
	}

}

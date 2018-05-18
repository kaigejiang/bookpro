package cn.edu.nyist.jdbcuserman.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.jdbcuserman.biz.TypeBiz;
import cn.edu.nyist.jdbcuserman.biz.imp.TypeBizImp;
import cn.edu.nyist.jdbcuserman.vo.TypeVo;

@WebServlet("/findAllTypes")
public class FindAllTypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FindAllTypesServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����
		//����ҵ���
		TypeBiz typeBiz = new TypeBizImp();
		List<TypeVo> ls = typeBiz.findAllTypes();
		//���û�����
		request.setAttribute("ls", ls);
		request.getRequestDispatcher("bookAdd.jsp").forward(request, response);
	}

}

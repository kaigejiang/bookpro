package cn.edu.nyist.jdbcuserman.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 ��ȡ����
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String vcode = request.getParameter("vcode");
		//ִ����֤��
		HttpSession session = request.getSession();
		String serverVcode = (String) session.getAttribute("validateCode");
		//��֤�벻��
		if(!serverVcode.equalsIgnoreCase(vcode)) {
			//��¼������Ϣ
			request.setAttribute("msg", "��֤�����");
			request.setAttribute("name", name);
		}
	}

}

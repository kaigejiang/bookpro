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
		//1 获取参数
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String vcode = request.getParameter("vcode");
		//执行验证码
		HttpSession session = request.getSession();
		String serverVcode = (String) session.getAttribute("validateCode");
		//验证码不对
		if(!serverVcode.equalsIgnoreCase(vcode)) {
			//记录错误信息
			request.setAttribute("msg", "验证码错误");
			request.setAttribute("name", name);
		}
	}

}

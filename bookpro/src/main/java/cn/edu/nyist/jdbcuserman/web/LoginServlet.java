package cn.edu.nyist.jdbcuserman.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.nyist.jdbcuserman.util.DsUtil;

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
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return ;
		}
		//2 到数据库查询
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean ret = false;
		try {
			connection = DsUtil.getConn();
			String sql = "select * from t_user where name=? and pwd=?";
			System.out.println(sql);
			statement = connection.prepareStatement(sql);
			//转义
			statement.setString(1, name);
			statement.setString(2, pwd);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				ret= true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(resultSet, statement, connection);
		}
		//响应
		if (ret) {
			response.sendRedirect("main.jsp");
		}else {
			//失败
			request.setAttribute("mesg", "用户名或密码错误");
			request.setAttribute("name", name);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}

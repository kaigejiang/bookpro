package cn.edu.nyist.jdbcuserman.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class LoginFilter implements Filter {

 
    public LoginFilter() {
      
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if(uri.contains("/bower_components/")||uri.endsWith("login.jsp")||uri.endsWith("/vcode.png")||uri.endsWith("/login")) {
			chain.doFilter(request, response);
			return;
		}
		if(req.getSession().getAttribute("loginSe")==null||!req.getSession().getAttribute("loginSe").equals("1")) {
			resp.sendRedirect("login.jsp");
			return;
		}else {
			chain.doFilter(request, response);
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {

	}

}

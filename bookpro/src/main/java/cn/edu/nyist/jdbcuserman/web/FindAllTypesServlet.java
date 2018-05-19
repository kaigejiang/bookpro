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
		//获取参数
		//调用业务层
		TypeBiz typeBiz = new TypeBizImp();
		List<TypeVo> ls = typeBiz.findAllTypes();
		//给用户反馈
		response.setContentType("text/javascript;charset=utf-8");
		String js="var types=[";
		for (int i=0;i<ls.size();i++) {
			js+="{id:"+ls.get(i).getId()+",name:'"+ls.get(i).getName()+"'}"; 
			if (i<ls.size()-1) {
				js+=",";
			}
		}
		js+="]";
		response.getWriter().write(js);
	}

}

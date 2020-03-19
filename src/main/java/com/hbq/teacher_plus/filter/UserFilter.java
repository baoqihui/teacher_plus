package com.hbq.teacher_plus.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2=(HttpServletRequest) request;
		HttpServletResponse response2=(HttpServletResponse) response;
		
		String url=request2.getScheme()+"://"+request2.getServerName()+":"+request2.getServerPort()+request2.getSession().getServletContext().getContextPath()+"/";
		
		Object o=request2.getSession().getAttribute("user");

		System.out.println("进入过滤器！"+o);

        //得到当前页面所在目录下全名称
		String urlPattern=request2.getServletPath();
		
		//得到页面所在服务器的绝对路径
		String path = request2.getRequestURI();

	
		if(o==null){		
			response2.sendRedirect(url+"login.html");
		}else{
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}



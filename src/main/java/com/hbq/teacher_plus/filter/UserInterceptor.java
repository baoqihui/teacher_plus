package com.hbq.teacher_plus.filter;

/**
 * 拦截器
 * @author XiaoHui
 *
 */

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	/*	@Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

            Jedis jedis= JedisConnect.Conn();
            String json_user=jedis.get("user");
            Users user= JSONObject.parseObject(json_user, Users.class);
		Users user=(Users)session.getAttribute("user");
		System.out.println("进入拦截器");
		//不为空，已登录放行；
		if(user!=null){
			//放行
			System.out.println("放行");
			return true;
		}
		System.out.println("拦截");
		return false;
	}
*/
}


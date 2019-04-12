package com.sinmn.core.utils.interceptor;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sinmn.core.utils.vo.BaseBean;

public abstract class AbstractInterceptor implements HandlerInterceptor{

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	protected boolean sendResult(BaseBean result,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json; charset=utf-8");
		try {
			response.getWriter().print(result.toJsonString());
			response.flushBuffer();
		} catch (Exception e) {
		}
		return false;
	}
}

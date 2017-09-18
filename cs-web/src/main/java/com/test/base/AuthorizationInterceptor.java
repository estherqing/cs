package com.test.base;

import com.test.model.ZaUser;
import com.test.util.WebUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* Author:  lijunjun
 *   Time:  2013-5-23
 *  Email:  lizhijun1103@163.com
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	//private static Log logger = LogFactory.getLog("securityLogger");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		if (requestURI.endsWith("/login") || requestURI.endsWith(".html") || requestURI.endsWith("/getSystemName") || requestURI.endsWith("/loan/payment/backPayment") || requestURI.endsWith("/loan/payment/balance") || requestURI.endsWith("/monitor/serverStatus")) {
			return true;
		}
		ZaUser user = (ZaUser) request.getSession().getAttribute(AbstractBaseController.SESSION_KEY_USER);
		if (user == null) {
			try {
				response.setContentType(WebUtil.CONTEXT_TYPE);
				response.getWriter().write(WebUtil.getFailureJson("SESSION超时", WebUtil.TIMEOUT));
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		// pass
		return true;
	}

}

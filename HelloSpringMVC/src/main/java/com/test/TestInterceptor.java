package com.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.util.HttpUtil;
import com.util.SSOClientUtil;

public class TestInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("after");
	}

	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj, ModelAndView MAV) throws Exception {
		System.out.println("post");
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj) throws Exception {
		System.out.println("pre");
		
		HttpSession session = req.getSession();
		//1.�ж��Ƿ��оֲ��ĻỰ
		Boolean isLogin = (Boolean) session.getAttribute("isLogin");
		if(isLogin!=null && isLogin){
			//�оֲ��Ự,ֱ�ӷ���.
			return true;
		}
		//�жϵ�ַ�����Ƿ���Я��token����.
		String token = req.getParameter("token");
		if(token!=null&&token!=""){
			//token��Ϣ��Ϊnull,˵����ַ�а�����token,ӵ������.
			//�ж�token��Ϣ�Ƿ�����֤���Ĳ�����.
			String httpURL = SSOClientUtil.SERVER_URL_PREFIX+"/verify";
			Map<String,String> params = new HashMap<String,String>();
			params.put("token", token);
			params.put("clientUrl", SSOClientUtil.getClientLogOutUrl());
			params.put("jsessionid", session.getId());
			try {
				String isVerify = HttpUtil.sendHttpRequest(httpURL, params);
				if("true".equals(isVerify)){
					//������ص��ַ�����true,˵�����token����ͳһ��֤���Ĳ�����.
					//�����ֲ��ĻỰ.
					session.setAttribute("isLogin", true);
					//���иôε�����
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//û�оֲ��Ự,�ض���ͳһ��֤����,����Ƿ���������ϵͳ�Ѿ���¼��.
		// http://www.sso.com:8443/checkLogin?redirectUrl=http://www.crm.com:8088
		SSOClientUtil.redirectToSSOURL(req, res);
		return false;
	}

}

package com.util;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SSOClientUtil {
	private static Properties ssoProperties = new Properties();
	public static String SERVER_URL_PREFIX;//ͳһ��֤���ĵ�ַ:http://www.sso.com:8443,��sso.properties����
	public static String CLIENT_HOST_URL;//��ǰ�ͻ��˵�ַ:http://www.crm.com:8088,��sso.properties����
	static{
		try {
			ssoProperties.load(SSOClientUtil.class.getClassLoader().getResourceAsStream("sso.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		SERVER_URL_PREFIX = ssoProperties.getProperty("server-url-prefix");
		CLIENT_HOST_URL = ssoProperties.getProperty("client-host-url");
	}
	/**
	 * ���ͻ�����������,����ͳһ��֤����,��Ҫ��redirectUrl�Ĳ���,ͳһ��֤���ĵ�¼��ص��ĵ�ַ
	 * ͨ��Request��ȡ�������ĵ�ַ http://www.crm.com:8088/main
	 * 
	 * @param request
	 * @return
	 */
	public static String getRedirectUrl(HttpServletRequest request){
		//��ȡ����URL
		return CLIENT_HOST_URL+request.getServletPath();
	}
	/**
	 * ����request��ȡ��ת��ͳһ��֤���ĵĵ�ַ http://www.sso.com:8443//checkLogin?redirectUrl=http://www.crm.com:8088/main
	 * ͨ��Response��ת��ָ���ĵ�ַ
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void redirectToSSOURL(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String redirectUrl = getRedirectUrl(request);
		StringBuilder url = new StringBuilder(50)
				.append(SERVER_URL_PREFIX)
				.append("/checkLogin?redirectUrl=")
				.append(redirectUrl);
		response.sendRedirect(url.toString());
	}
	
	
	/**
	 * ��ȡ�ͻ��˵������ǳ���ַ http://www.crm.com:8088/logOut
	 * @return
	 */
	public static String getClientLogOutUrl(){
		return CLIENT_HOST_URL+"/logOut";
	}
	/**
	 * ��ȡ��֤���ĵĵǳ���ַ http://www.sso.com:8443/logOut
	 * @return
	 */
	public static String getServerLogOutUrl(){
		return SERVER_URL_PREFIX+"/logOut";
	}
}

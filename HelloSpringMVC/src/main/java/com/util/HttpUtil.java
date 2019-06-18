package com.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StreamUtils;

public class HttpUtil {
	/**
	 * ģ�������������
	 * @param httpURL ��������ĵ�ַ
	 * @param params  �������
	 * @return
	 * @throws Exception
	 */
	public static String sendHttpRequest(String httpURL,Map<String,String> params) throws Exception{
		//����URL���Ӷ���
		URL url = new URL(httpURL);
		//��������
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//��������ķ�ʽ(��Ҫ�Ǵ�д��)
		conn.setRequestMethod("POST");
		//������Ҫ���
		conn.setDoOutput(true);
		//�ж��Ƿ��в���.
		if(params!=null&&params.size()>0){
			StringBuilder sb = new StringBuilder();
			for(Entry<String,String> entry:params.entrySet()){
				sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
			}
			//sb.substring(1)ȥ����ǰ���&
			conn.getOutputStream().write(sb.substring(1).toString().getBytes("utf-8"));
		}
		//�������󵽷�����
		conn.connect();
		//��ȡԶ����Ӧ������.
		String responseContent = StreamUtils.copyToString(conn.getInputStream(),Charset.forName("utf-8"));
		conn.disconnect();
		return responseContent;
	}
	/**
	 * ģ�������������
	 * @param httpURL ��������ĵ�ַ
	 * @param jesssionId �ỰId
	 * @return
	 * @throws Exception
	 */
	public static void sendHttpRequest(String httpURL,String jesssionId) throws Exception{
		//����URL���Ӷ���
		URL url = new URL(httpURL);
		//��������
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//��������ķ�ʽ(��Ҫ�Ǵ�д��)
		conn.setRequestMethod("POST");
		//������Ҫ���
		conn.setDoOutput(true);
		conn.addRequestProperty("Cookie","JSESSIONID="+jesssionId);
		//�������󵽷�����
		conn.connect();
		conn.getInputStream();
		conn.disconnect();
	}
}

package com.lwx.usm.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	private static Log log = LogFactory.getLog(HttpUtil.class);

	
	public static String httpPost4Body(String url,String msg){
		return httpPost4Body(url,msg,6000);
		
	}
	
	public static String httpPost4Body(String url,String msg,int readTimeout){
		BasicHttpClientConnectionManager connManager;
		connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );
		HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();
		//String url = "http://10.0.57.146/webservice/interface.ashx";
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(3000).build();
		httpPost.setConfig(requestConfig);
		//method.addRequestHeader("Content-Type", "application/json");
		httpPost.addHeader("Content-Type", "application/json");
		
		try {
			log.info("通过HTTP调用接口请求UTL:"+url);
			log.info("通过HTTP调用接口请求参数:"+msg);
			StringEntity entity = new StringEntity(msg, "UTF-8");
			httpPost.setEntity(entity);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			StatusLine statu = httpResponse.getStatusLine();
			int code = statu.getStatusCode();
			HttpEntity httpEntity = httpResponse.getEntity();
			String res =  EntityUtils.toString(httpEntity, "UTF-8");
			if(HttpStatus.SC_OK == code){
			  
			//	log.info("通过HTTP调用接口返回参数:"+res);
				return res;
			} else {
				log.error("the post return value "+statu);
				log.error("the post return value "+res);
				throw new RuntimeException(res);
			}
		} catch (Exception ex){
			throw new RuntimeException(ex);
		}
		
	}

}

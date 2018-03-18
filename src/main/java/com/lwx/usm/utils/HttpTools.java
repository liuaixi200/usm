package com.lwx.usm.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketAddress;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpTools {


    private static String algorithm     = "SunX509";
    private static String keyType   = "PKCS12";

    /**
	 * 发送GET请求并返回响应结果
	 * 
	 * Author: chenggang.du $Date: 2013-09-26 15:25:27 +0800 (Thu, 26 Sep 2013) $
	 */
    @Deprecated
	public static String sendGet(String targetUrl, String param) {
		return sendGet(targetUrl, param, null);
		
	}

    /**  
     * 发送GET请求并返回响应结果
     *
     * @param targetUrl
     * @param param
     * @param proxy --- 如为空则不使用proxy
     * @return 
     */
    public static String sendGet(String targetUrl, String param, Proxy proxy) {

        String result = null;
        if (param != null && param.length() > 0) {
            targetUrl += "?" + param;
        }

        try {
            URL realUrl = new URL(targetUrl);
            HttpURLConnection conn = null;
            if (proxy == null) {
                conn = (HttpURLConnection) realUrl.openConnection();
            } else {
                conn = (HttpURLConnection) realUrl.openConnection(proxy);
            }
            conn.setConnectTimeout(5000);

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            result = sb.toString();
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常 -- " + targetUrl + " -- "+ e);
        }
        return result;
        
    }

    /**
	 * 发送post请求并返回响应结果
	 * 
	 * Author: chenggang.du $Date: 2013-09-26 15:25:27 +0800 (Thu, 26 Sep 2013) $
	 */
    @Deprecated
	public static String sendPost(String url, String param) {
        return sendPost(url, param, null, null);
	}
	
    /**
     * 发送post请求并返回响应结果
     * 
     * Author: chenggang.du $Date: 2013-09-26 15:25:27 +0800 (Thu, 26 Sep 2013) $
     */
    @Deprecated
    public static String sendPost(String url, String param, String contentType) {
        return sendPost(url, param, contentType, null);
    }

    
    /**  
     * 发送post请求并返回响应结果
     *
     * @param url
     * @param param
     * @param contentType
     * @param proxy --- 如为空则不使用proxy
     * @return 
     */
    public static String sendPost(String url, String param, String contentType, Proxy proxy) {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);

            HttpURLConnection conn = null;
            if (proxy == null) {
                conn = (HttpURLConnection) realUrl.openConnection();
            } else {
                conn = (HttpURLConnection) realUrl.openConnection(proxy);
            }

            conn.setConnectTimeout(5000);
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            if (contentType != null) {
                conn.setRequestProperty("Content-type", contentType);
            }

            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            // out.print(param);
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应--- 字符集utf-8
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常 -- " + url + " -- " + param+ e);
            return null;
        } finally {
            CommonTools.closeIgnoringException(out);
            CommonTools.closeIgnoringException(in);
        }
        return result;
    }
    
    /**
     * 所有主机默认通过
     */
    private static HostnameVerifier hnv = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
    
    /**
     * 关键在这信任所有证书
     */
    private static TrustManager[] trustAllCerts = new TrustManager[] {
       new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    return;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    return;
                }
        } 
    };

    /**
     * 
     * 发送https POST请求
     *
     * @param requestUrl
     * @param cPath
     * @param cPassWord
     * @return
     */
    @Deprecated
    public static String sendHttpsPost(
            String requestUrl, String cPath, String cPassWord, String param
            ) {
        return sendHttpsPost(requestUrl, cPath, cPassWord, param, null);
    }

    
    /**  
     * 发送https POST请求
     *
     * @param requestUrl
     * @param cPath
     * @param cPassWord
     * @param param
     * @param proxy --- 如为空则不使用proxy
     * @return 
     */
    public static String sendHttpsPost(String requestUrl,String cPath,String cPassWord,String param, Proxy proxy) {

        // set up a connection
        SSLSocketFactory ssf = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        
        try {
            
            //init
            ssf = initSSLSocketFactory(cPath,cPassWord);

            HttpsURLConnection.setDefaultSSLSocketFactory(ssf);

            HttpsURLConnection.setDefaultHostnameVerifier(hnv);

            URL realUrl = new URL(requestUrl);

            HttpsURLConnection conn = null;
            if (proxy == null) {
                conn = (HttpsURLConnection) realUrl.openConnection();
            } else {
                conn = (HttpsURLConnection) realUrl.openConnection(proxy);
            }
            conn.setConnectTimeout(5000);

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {
           System.out.println("发送POST请求出现异常！" + e);
            return null;
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            CommonTools.closeIgnoringException(out);
            CommonTools.closeIgnoringException(in);
        }
        
    }
    
    public static SSLSocketFactory initSSLSocketFactory(String cPath,String cPassWord) 
            throws Exception{
        
        SSLSocketFactory ssf = null;
        // init context
        SSLContext ctx = SSLContext.getInstance("TLS");
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
        KeyStore ks = KeyStore.getInstance(keyType);
        // load keystore
        ks.load(new FileInputStream(cPath), cPassWord.toCharArray());
        
        kmf.init(ks, cPassWord.toCharArray());

        ctx.init(kmf.getKeyManagers(), trustAllCerts, null);

        ssf = ctx.getSocketFactory();
        
        return ssf;
    }
    
    /**
     * 
     * 发送https GET请求
     *
     * @param requestUrl
     * @param cPath
     * @param cPassWord
     * @return
     */
    @Deprecated
    public static String sendHttpsGet(
            String requestUrl, String cPath, String cPassWord
            ) {
        return sendHttpsGet(requestUrl, cPath, cPassWord, null);
    }
	
    /**  
     * 发送Https Get请求
     *
     * @param requestUrl
     * @param cPath
     * @param cPassWord
     * @param proxy --- 如为空则不使用proxy
     * @return 
     */
    public static String sendHttpsGet(String requestUrl,String cPath,String cPassWord, Proxy proxy) {
        
        SSLSocketFactory ssf = null;
        BufferedReader in = null;
        String result = null;
        
        try {
            //init
            ssf = initSSLSocketFactory(cPath,cPassWord);

            HttpsURLConnection.setDefaultSSLSocketFactory(ssf);

            HttpsURLConnection.setDefaultHostnameVerifier(hnv);

            URL realUrl = new URL(requestUrl);

            HttpsURLConnection conn = null;
            if (proxy == null) {
                conn = (HttpsURLConnection) realUrl.openConnection();
            } else {
                conn = (HttpsURLConnection) realUrl.openConnection(proxy);
            }
            conn.setConnectTimeout(5000);

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            result=buffer.toString();
            return result;
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            CommonTools.closeIgnoringException(in);
        }
        return result;
    }

    /**  
     * 初始化一个Proxy对象供后续访问使用
     *
     * @param proxyUsername
     * @param proxyPasswd
     * @param proxyHost
     * @param proxyPort
     * @param proxyType --- 如为空，默认生成socket类型
     * @return
     * @throws Exception 
     */
    public static Proxy initProxy(
            final String proxyUsername, final String proxyPasswd,
            String proxyHost, String proxyPort,
            Type proxyType
            ) throws Exception {
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(proxyUsername, new String(
                        proxyPasswd).toCharArray());
            }
        });
        SocketAddress addr = new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort));
        if (proxyType == null) {
            return new Proxy(Proxy.Type.SOCKS, addr);
        } else {
            return new Proxy(proxyType, addr);
        }
    }

}

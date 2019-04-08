package com.uway.common.utils;
 
import java.io.IOException;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
 
public class HttpRequestUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);    //日志记录
    SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();

    /**
     * post请求
     * @param url 请求地址
     * @param jsonParam 请求参数
     * @return  boolean 成功还是失败
     */
    public static boolean httpPost(String url,JSONObject jsonParam){
    	CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam){
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpclient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
              return true;
            }else {
                logger.error("post请求提交失败:" + url);
                return false;
            }
        } catch(IOException e){
            logger.error("post请求提交失败:" + url, e);
            return false;
        }
    }
 
 
    /**
     * 发送get请求
     * @param url 路径
     * @return
     */
    public static String httpGet(String url){
    	CloseableHttpClient httpclient = null;
        try {
        	httpclient = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpclient.execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            	   HttpEntity entity = response.getEntity();  
            	   return EntityUtils.toString(entity);
            } else {
                logger.error("get请求提交失败:" + url);
                return null;
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
            return null;
        }finally{
        	try {
				httpclient.close();
			} catch (IOException e) {
				return null;
			}
        }
    }
    
    public static void main(String[] args) {
    	for(int i=0;i<=15;i++){
    		long t1= System.currentTimeMillis();
    		httpGet("http://m.api.dianping.com/novicegift/uquery?IDFA=E3C1AC97-E021-461C-A51C-15D9988D37B3");
    		long t2= System.currentTimeMillis();
    		System.out.println(t2-t1);
    	}    	
	}
    

}
/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.jenkins.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class Description
 * @version Oct 9, 20175:15:34 PM
 * @author Ford.CHEN
 */
public class HttpClientUtils {
    
    
    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    
    /** 
     * 
     * Method Description
     * @version Oct 20, 20175:45:24 PM
     * @author Ford.CHEN
     * @param textMsg
     * @param outgoingUrl
     * @param username
     * @param password
     */
    public static void postWithBasicAuth(String textMsg, String outgoingUrl, String username, String password) {  
        
        HttpHost httpHost = HttpHost.create(outgoingUrl);
        
        // 凭据提供器
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                // 认证范围
                new AuthScope(httpHost),
                // 认证用户名和密码
                new UsernamePasswordCredentials(username, password));
        
        // 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
        
        
        // 创建httppost    
        HttpPost httppost = new HttpPost(outgoingUrl); 
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        
        // 创建参数队列    
        StringEntity se = new StringEntity(textMsg, "utf-8");
        try {  
            httppost.setEntity(se);  
            logger.info("executing post request --> {}", httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    logger.info(EntityUtils.toString(entity, "UTF-8"));
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            logger.error("post with ClientProtocolException ", e); 
        } catch (UnsupportedEncodingException e) {  
            logger.error("post with UnsupportedEncodingException ", e);   
        } catch (IOException e) {  
            logger.error("post with IOException ", e);   
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                logger.error("post with IOException ", e);  
            }  
        }
    }
    
    /**
     * 
     * Method Description
     * @version Oct 24, 20179:02:45 AM
     * @author Ford.CHEN
     * @param url
     * @return
     */
    public static HttpHost create(final String url) {
        Args.containsNoBlanks(url, "HTTP Host");
        String text = url;
        String scheme = null;
        final int schemeIdx = text.indexOf("://");
        if (schemeIdx > 0) {
            scheme = text.substring(0, schemeIdx);
            text = text.substring(schemeIdx + 3);
        }
        int port = -1;
        final int portStartIdx = text.indexOf(":");
        final int portEndIdx = text.indexOf("/");
        if (portStartIdx > 0) {
            try {
                port = Integer.parseInt(text.substring(portStartIdx + 1,portEndIdx));
            } catch (final NumberFormatException ex) {
                throw new IllegalArgumentException("Invalid HTTP host: " + text);
            }
            text = text.substring(0, portStartIdx);
        }
        return new HttpHost(text, port, scheme);
    }
    
 
}

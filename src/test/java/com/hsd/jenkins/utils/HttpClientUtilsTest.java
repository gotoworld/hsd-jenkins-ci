/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.jenkins.utils;

import static org.junit.Assert.assertEquals;

import org.apache.http.HttpHost;
import org.junit.Test;

/**
 * Test for HttpClientUtils
 * @version Oct 9, 20175:15:34 PM
 * @author Ford.CHEN
 */
public class HttpClientUtilsTest {
    
    static final String outgoingUrl = "http://192.168.254.240:88/job/gold-common-jobs/buildWithParameters?application=hsdgold-console-pc&tag=V0.10.23.9&group=gold";
    
    @Test
    public void testCreateHttpHostByURL() {
      
        HttpHost httpHost = HttpClientUtils.create(outgoingUrl);
        
        assertEquals("http://192.168.254.240:88", httpHost.toURI());
    }
    
    
    @Test
    public void testPostWithBasicAuth(){
        
        HttpClientUtils.postWithBasicAuth("", outgoingUrl, "jenkins", "jenkins");
        
    }
    
 
}

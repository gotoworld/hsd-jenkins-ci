package com.hsd.jenkins.utils;

import java.util.concurrent.Callable;

/**
 * 
 * Class Description
 * @version Oct 20, 20175:33:54 PM
 * @author Ford.CHEN
 */
public class TaskOfOutgoingPost implements Callable<String> {
    private String textMsg;
    private String outgoingUrl;
    
    private String username;
    private String password;
    
    public TaskOfOutgoingPost(String textMsg,String outgoingUrl,String username,String password){   
        this.textMsg = textMsg;   
        this.outgoingUrl = outgoingUrl;   
        this.username = username;   
        this.password = password;   
    }
    
    /**
     * 任务的具体过程，一旦任务传给ExecutorService的submit方法， 则该方法自动在一个线程上执行
     */
    @Override
    public String call() throws Exception {
        Long start = System.currentTimeMillis();
        
        //do post with basic Auth
        HttpClientUtils.postWithBasicAuth(textMsg, outgoingUrl, username, password);
        
        return "Task Of Outgoing Post finished, it cost time：" + (System.currentTimeMillis() - start);
    }
}
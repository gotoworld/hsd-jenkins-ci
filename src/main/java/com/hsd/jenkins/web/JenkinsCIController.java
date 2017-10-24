/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.jenkins.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hsd.jenkins.bean.Project;
import com.hsd.jenkins.bean.TagPushEvent;
import com.hsd.jenkins.dto.BaseVO;
import com.hsd.jenkins.dto.ResVo;
import com.hsd.jenkins.utils.TaskOfOutgoingPost;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Class Description
 * 
 * @version Oct 20, 20179:41:49 AM
 * @author Ford.CHEN
 */
@RestController
@RequestMapping("/jenkins")
public class JenkinsCIController {
    
    private final static Logger logger = LoggerFactory.getLogger(JenkinsCIController.class);
    
    
    static Map<String, String> map = new HashMap<String,String>(); 
    static {
        map.put("gold", "chenfangri");
    }
    
    @ApiOperation(value = "Handle the CI trigger event", notes = "CI trigger")
    @ApiImplicitParam(name = "textMsg", value = "CI trigger message", required = true, dataType = "String")
    @PostMapping("/trigger")
    public ResVo<BaseVO> trigger(@RequestBody String textMsg) {
        logger.info("received CI trigger message: {}", textMsg);
        if (!textMsg.contains("tag_push")) {
            logger.error("message doesn't match a tag push event: {}", textMsg);
        }
        
        TagPushEvent event = JSON.parseObject(textMsg, TagPushEvent.class);
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        Project project = event.getProject();
        String group = project.getNamespace();
        
        String username = event.getUserUsername();//English name
        
        
        String ref = event.getRef();
        String tagName = "";
        String[] s = ref.split("\\/");
        if(s.length == 3){
            tagName = s[2];
        }
        
        Map<String, Object> parameters = new HashMap<String,Object>();
        parameters.put("tag", tagName);
        parameters.put("group", group);
        parameters.put("application", project.getName());
        
        String outgoingUrl = "http://192.168.254.240:88/job/gold-common-jobs" + toQueryString(parameters);
        if (StringUtils.equals(username, map.get(group)) && tagName.startsWith("V0")) {
            executorService.submit(new TaskOfOutgoingPost("", outgoingUrl, "jenkins", "jenkins"));
        }
        
        return null;
    }
    
    /**
     * 
     * Method Description
     * @version Oct 23, 20175:26:10 PM
     * @author Ford.CHEN
     * @param parameters
     * @return
     */
    private String toQueryString(Map<String, Object> parameters) {
        StringBuilder queryParams = new StringBuilder();
        
        for (Entry<String, Object> entry : parameters.entrySet()) {
           queryParams.append("&").append(entry.getKey()).append("=").append(encode(entry.getValue()));
        }
        queryParams.deleteCharAt(0);
        return "/buildWithParameters?"+queryParams.toString();
     }
    
    /**
     * 
     * Method Description
     * @version Oct 23, 20175:26:07 PM
     * @author Ford.CHEN
     * @param value
     * @return
     */
    private String encode(Object value) {
        try {
           return URLEncoder.encode(String.valueOf(value), "UTF-8");
        } catch (UnsupportedEncodingException e) {
           throw new IllegalStateException(e);
        }
     }
}

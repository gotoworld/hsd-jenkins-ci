/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.jenkins.web;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
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
    @ApiImplicitParam(name = "message", value = "CI trigger message", required = true, dataType = "String")
    @PostMapping("/trigger")
    public ResVo<BaseVO> trigger(String message) {
        logger.info("received CI trigger message: {}", message);
        if (!message.contains("tag_push")) {
            logger.error("message doesn't match a tag push event: {}", message);
        }
        
        TagPushEvent event = JSON.parseObject(message, TagPushEvent.class);
        
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
        
        if (StringUtils.equals(username, map.get(group)) && tagName.startsWith("V.")) {
            executorService.submit(new TaskOfOutgoingPost("", "", "admin", "admin"));
        }
        
        return null;
    }
}

/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.jenkins.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsd.jenkins.dto.BaseVO;
import com.hsd.jenkins.dto.ResVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Class Description
 * @version Oct 20, 20179:41:49 AM
 * @author Ford.CHEN
 */
@RestController
@RequestMapping("/jenkins")
public class JenkinsCIController {
    
        private final static Logger logger = LoggerFactory.getLogger(JenkinsCIController.class);
        
        
        @ApiOperation(value="Handle the CI trigger event", notes="CI trigger")
        @ApiImplicitParam(name = "message", value = "CI trigger message", required = true, dataType = "String")
        @PostMapping("/trigger")
        public ResVo<BaseVO> trigger(String message){
            logger.info("received CI trigger message: {}",message);
            
            return null;
        }
}

/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.jenkins.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hsd.jenkins.bean.Project;
import com.hsd.jenkins.bean.TagPushEvent;
import com.hsd.jenkins.config.JenkinsProperties;
import com.hsd.jenkins.dto.BaseVO;
import com.hsd.jenkins.dto.ResVo;
import com.hsd.jenkins.utils.TaskOfOutgoingPost;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * Class Description
 * 
 * @version Oct 20, 20179:41:49 AM
 * @author Ford.CHEN
 */
@RestController
@RequestMapping("/jenkins")
@Slf4j
public class JenkinsCIController extends BaseController {
    
    private final static Logger log = LoggerFactory.getLogger(JenkinsCIController.class);
    
    private static final String ONLINE_IMAGES_REPOSITORY_SUFFIX = "release";
    
    @Resource
    JenkinsProperties jenkinsProperties;
    
    @ApiOperation(value = "Handle the CI trigger event", notes = "CI trigger")
    @ApiImplicitParam(name = "textMsg", value = "CI trigger message", required = true, dataType = "String")
    @PostMapping("/trigger")
    public ResVo<BaseVO> trigger(@RequestBody String textMsg) {
        log.info("received CI trigger message: {}", textMsg);
        if (!textMsg.contains("tag_push")) {
            log.error("message doesn't match a tag push event: {}", textMsg);
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
        
        
        List<String> authors = jenkinsProperties.getAuthors();
        
        Map<String, Object> parameters = prepareJenkinsBuildParam(project, group, username, tagName, authors);
        
        String outgoingUrl = jenkinsProperties.getCommonJobUrl() + toQueryString(parameters);
        
       /* Once we judge username and tagname and only build the release tag, now we param this, let the jenkins to make a decision  
        * Refer to: R001 
       *  if ( authors.contains(username) && tagName.startsWith(jenkinsProperties.getReleaseTagPrefix())) {
            executorService.submit(new TaskOfOutgoingPost("", outgoingUrl, jenkinsProperties.getUsername(), jenkinsProperties.getPassword()));
        }*/
        log.info("[{}] push a new project tag [{}:{}] and trigger jenkins build at time {}",username,project.getName(),tagName,DateTime.now());
        executorService.submit(new TaskOfOutgoingPost("", outgoingUrl, jenkinsProperties.getUsername(), jenkinsProperties.getPassword()));
        
        return null;
    }
    
    /**
     * 
     * 
     *payload={  
     *   "actions":[  
     *      {  
     *         "name":"game",
     *         "type":"button",
     *         "value":"chess"
     *      }
     *   ],
     *   "callback_id":"wopr_game",
     *   "team":{  
     *      "id":"T6PBX811B",
     *      "domain":"heshidai"
     *   },
     *   "channel":{  
     *      "id":"C7SSYE8G7",
     *      "name":"teamleader"
     *   },
     *   "user":{  
     *      "id":"U6P5SGGM9",
     *      "name":"ford.chen"
     *   },
     *   "action_ts":"1509433406.911165",
     *   "message_ts":"1509433399.000134",
     *   "attachment_id":"1",
     *   "token":"ZeGsrDaUVqs5nSO83Ly5VJdz",
     *   "is_app_unfurl":false,
     *   "type":"interactive_message",
     *   "original_message":{  
     *      "text":"Would you like to play a game?",
     *      "bot_id":"B7SE53C8L",
     *      "attachments":[  
     *         {  
     *            "callback_id":"wopr_game",
     *            "fallback":"You are unable to choose a game",
     *            "text":"Choose a game to play",
     *            "id":1,
     *            "color":"3AA3E3",
     *            "actions":[  
     *               {  
     *                  "id":"1",
     *                  "name":"game",
     *                  "text":"Chess",
     *                  "type":"button",
     *                  "value":"chess",
     *                  "style":""
     *               },
     *               {  
     *                  "id":"2",
     *                  "name":"game",
     *                  "text":"Falken's Maze",
     *                  "type":"button",
     *                  "value":"maze",
     *                  "style":""
     *               },
     *               {  
     *                  "id":"3",
     *                  "name":"game",
     *                  "text":"Thermonuclear War",
     *                  "type":"button",
     *                  "value":"war",
     *                  "style":"danger",
     *                  "confirm":{  
     *                     "text":"Wouldn't you prefer a good game of chess?",
     *                     "title":"Are you sure?",
     *                     "ok_text":"Yes",
     *                     "dismiss_text":"No"
     *                  }
     *               }
     *            ]
     *         }
     *      ],
     *      "type":"message",
     *      "subtype":"bot_message",
     *      "ts":"1509433399.000134"
     *   },
     *   "response_url":"https:\/\/hooks.slack.com\/actions\/T6PBX811B\/265362110598\/RBuRLqk5NSo3ksjAQc65cJci",
     *   "trigger_id":"264530593588.227405273045.82abfa2066fcaa76dbbe995849d72e65"
     *}
     * 
     * 
     * Method Description
     * @version Oct 31, 20173:12:10 PM
     * @author Ford.CHEN
     * @param payload
     * @return
     */
    @PostMapping("/publish")
    public ResVo<BaseVO> publish(@RequestBody String payload) {
        System.out.println(payload);
        
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
        
        
        return null;
    }


    /**
     * Method Description
     * @version Oct 25, 201710:04:57 AM
     * @author Ford.CHEN
     * @param project
     * @param group
     * @param username
     * @param tagName
     * @param authors
     * @return
     */
    private Map<String, Object> prepareJenkinsBuildParam(Project project, String group, String username, String tagName, List<String> authors) {
        String imagerepo = group;
        boolean isrelease = false;
        if(authors.contains(username) && tagName.startsWith(jenkinsProperties.getReleaseTagPrefix())){// ROO1
            log.info("new release image [{}:{}] is pushed by [{}] at time {}",project.getName(),tagName,username,DateTime.now());
            
            imagerepo = imagerepo + ONLINE_IMAGES_REPOSITORY_SUFFIX;
            isrelease = true;
        }
        
        Map<String, Object> parameters = new HashMap<String,Object>();
        parameters.put("TAG", tagName);
        parameters.put("GROUP", group);
        parameters.put("APPLICATION", project.getName());
        parameters.put("IMAGEREPO", imagerepo); //bath on different repository, publish to different harbor group repository.
        parameters.put("ISRELEASE", isrelease);
        return parameters;
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

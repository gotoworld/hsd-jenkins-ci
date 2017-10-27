package com.hsd.jenkins.bean;

import lombok.Data;

/**
 * Class Description
 * @version Sep 27, 201711:44:20 AM
 * @author Ford.CHEN
 */
@Data
public class Project {

    private String name;
    private String description;
    private String webUrl;
    private String avatarUrl;
    private String gitSshUrl;
    private String gitHttpUrl;
    private String namespace;
    private int visibilityLevel;
    private String pathWithNamespace;
    private String defaultBranch;
    private String homepage;
    private String url;
    private String sshUrl;
    private String httpUrl;
    
}
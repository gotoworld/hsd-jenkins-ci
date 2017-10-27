package com.hsd.jenkins.bean;

import lombok.Data;

/**
 * 
 * Class Description
 * @version Sep 27, 20173:12:21 PM
 * @author Ford.CHEN
 */
@Data
public class Repository {

    private String name;
    private String url;
    private String description;
    private String homepage;
    private String gitHttpUrl;
    private String gitSshUrl;
    private int visibilityLevel;
     
}
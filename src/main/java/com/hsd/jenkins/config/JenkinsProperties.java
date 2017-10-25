package com.hsd.jenkins.config;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;


/**
 * 
 * Class Description
 * @version Oct 24, 201710:55:45 AM
 * @author Ford.CHEN
 */
@Validated
@Configuration
@ConfigurationProperties(prefix = JenkinsProperties.PREFIX)
public class JenkinsProperties {

    public static final String PREFIX = "jenkins";

    @NotNull
    private String jenkinsUrl; // http://192.168.254.240:88

    @NotNull
    private String username; // admin
    @NotNull
    private String password; // admin

    @NotNull
    private String releaseTagPrefix;// V0.
    
    @NotNull
    private String commonJobName; //gold-common-jobs
    
    @NotNull
    private List<String> authors; //simple authorization
    
    /**
     * 
     * return the CommonJob Url
     * @version Oct 24, 201710:35:38 AM
     * @author Ford.CHEN
     * @return
     */
    public String getCommonJobUrl(){
        return jenkinsUrl + "/job/" + commonJobName; //http://192.168.254.240:88/job/gold-common-jobs
    }
    
    
    
    //GETTER SETTER

    /**
     * @return the jenkinsUrl
     */
    public String getJenkinsUrl() {
        return jenkinsUrl;
    }

    /**
     * @param jenkinsUrl the jenkinsURL to set
     */
    public void setJenkinsUrl(String jenkinsUrl) {
        this.jenkinsUrl = jenkinsUrl;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the releaseTagPrefix
     */
    public String getReleaseTagPrefix() {
        return releaseTagPrefix;
    }

    /**
     * @param releaseTagPrefix the releaseTagPrefix to set
     */
    public void setReleaseTagPrefix(String releaseTagPrefix) {
        this.releaseTagPrefix = releaseTagPrefix;
    }

    /**
     * @return the commonJobName
     */
    public String getCommonJobName() {
        return commonJobName;
    }

    /**
     * @param commonJobName the commonJobName to set
     */
    public void setCommonJobName(String commonJobName) {
        this.commonJobName = commonJobName;
    }

    /**
     * @return the prefix
     */
    public static String getPrefix() {
        return PREFIX;
    }


    /**
     * @return the authors
     */
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
    
}

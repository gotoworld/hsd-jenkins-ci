package com.hsd.jenkins.bean;

/**
 * 
 * Class Description
 * @version Sep 27, 20173:12:21 PM
 * @author Ford.CHEN
 */
public class Repository {

    private String name;
    private String url;
    private String description;
    private String homepage;
    private String gitHttpUrl;
    private String gitSshUrl;
    private int visibilityLevel;
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return the homepage
     */
    public String getHomepage() {
        return homepage;
    }
    /**
     * @param homepage the homepage to set
     */
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
    /**
     * @return the gitHttpUrl
     */
    public String getGitHttpUrl() {
        return gitHttpUrl;
    }
    /**
     * @param gitHttpUrl the gitHttpUrl to set
     */
    public void setGitHttpUrl(String gitHttpUrl) {
        this.gitHttpUrl = gitHttpUrl;
    }
    /**
     * @return the gitSshUrl
     */
    public String getGitSshUrl() {
        return gitSshUrl;
    }
    /**
     * @param gitSshUrl the gitSshUrl to set
     */
    public void setGitSshUrl(String gitSshUrl) {
        this.gitSshUrl = gitSshUrl;
    }
    /**
     * @return the visibilityLevel
     */
    public int getVisibilityLevel() {
        return visibilityLevel;
    }
    /**
     * @param visibilityLevel the visibilityLevel to set
     */
    public void setVisibilityLevel(int visibilityLevel) {
        this.visibilityLevel = visibilityLevel;
    }
    

    
}
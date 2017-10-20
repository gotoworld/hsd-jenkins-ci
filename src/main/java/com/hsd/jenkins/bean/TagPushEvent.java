package com.hsd.jenkins.bean;
import java.util.List;

/**
 * 
 *{
 *  "event_name": "tag_push",
 *  "before": "0000000000000000000000000000000000000000",
 *  "after": "82b3d5ae55f7080f1e6022629cdb57bfae7cccc7",
 *  "ref": "refs/tags/v1.0.0",
 *  "checkout_sha": "5937ac0a7beb003549fc5fd26fc247adbce4a52e",
 *  "user_id": 1,
 *  "user_name": "John Smith",
 *  "user_avatar": "https://s.gravatar.com/avatar/d4c74594d841139328695756648b6bd6?s=8://s.gravatar.com/avatar/d4c74594d841139328695756648b6bd6?s=80",
 *  "project_id": 1,
 *  "project":{
 *    "name":"Example",
 *    "description":"",
 *    "web_url":"http://example.com/jsmith/example",
 *    "avatar_url":null,
 *    "git_ssh_url":"git@example.com:jsmith/example.git",
 *    "git_http_url":"http://example.com/jsmith/example.git",
 *    "namespace":"Jsmith",
 *    "visibility_level":0,
 *    "path_with_namespace":"jsmith/example",
 *    "default_branch":"master",
 *    "homepage":"http://example.com/jsmith/example",
 *    "url":"git@example.com:jsmith/example.git",
 *    "ssh_url":"git@example.com:jsmith/example.git",
 *    "http_url":"http://example.com/jsmith/example.git"
 *  },
 *  "repository":{
 *    "name": "Example",
 *    "url": "ssh://git@example.com/jsmith/example.git",
 *    "description": "",
 *    "homepage": "http://example.com/jsmith/example",
 *    "git_http_url":"http://example.com/jsmith/example.git",
 *    "git_ssh_url":"git@example.com:jsmith/example.git",
 *    "visibility_level":0
 *  },
 *  "commits": [],
 *  "total_commits_count": 0
 *}
 *
 * 
 * Class Description
 * @version Sep 27, 201711:56:57 AM
 * @author Ford.CHEN
 */
public class TagPushEvent {
    
    private String eventName;

    private String before;
    private String after;
    private String ref;
    private String checkoutSha;
    private Long userId;
    private String userName;//陈
    private String userAvatar;
    private int projectId;
    
    private Project project;
    private Repository repository;
    
    private List<String> commits;
    
    private int totalCommitsCount;
    
    //new version add
    private String objectKind;//PS001: gitlab offical api document don't have this, but the message have it
    private String message;//same as PS001: 
    private String userUsername; //ford， same as PS001: 
    private String userEmail;//same as PS001:
    
    

    /**
     * @return the eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * @param eventName the eventName to set
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * @return the before
     */
    public String getBefore() {
        return before;
    }

    /**
     * @param before the before to set
     */
    public void setBefore(String before) {
        this.before = before;
    }

    /**
     * @return the after
     */
    public String getAfter() {
        return after;
    }

    /**
     * @param after the after to set
     */
    public void setAfter(String after) {
        this.after = after;
    }

    /**
     * @return the ref
     */
    public String getRef() {
        return ref;
    }

    /**
     * @param ref the ref to set
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * @return the checkoutSha
     */
    public String getCheckoutSha() {
        return checkoutSha;
    }

    /**
     * @param checkoutSha the checkoutSha to set
     */
    public void setCheckoutSha(String checkoutSha) {
        this.checkoutSha = checkoutSha;
    }


    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userAvatar
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * @param userAvatar the userAvatar to set
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    /**
     * @return the projectId
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @return the repository
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * @param repository the repository to set
     */
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    /**
     * @return the commits
     */
    public List<String> getCommits() {
        return commits;
    }

    /**
     * @param commits the commits to set
     */
    public void setCommits(List<String> commits) {
        this.commits = commits;
    }

    /**
     * @return the totalCommitsCount
     */
    public int getTotalCommitsCount() {
        return totalCommitsCount;
    }

    /**
     * @param totalCommitsCount the totalCommitsCount to set
     */
    public void setTotalCommitsCount(int totalCommitsCount) {
        this.totalCommitsCount = totalCommitsCount;
    }

    /**
     * @return the objectKind
     */
    public String getObjectKind() {
        return objectKind;
    }

    /**
     * @param objectKind the objectKind to set
     */
    public void setObjectKind(String objectKind) {
        this.objectKind = objectKind;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the userUsername
     */
    public String getUserUsername() {
        return userUsername;
    }

    /**
     * @param userUsername the userUsername to set
     */
    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    

}
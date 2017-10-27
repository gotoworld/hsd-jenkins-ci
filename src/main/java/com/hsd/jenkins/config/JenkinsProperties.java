package com.hsd.jenkins.config;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Data;


/**
 * 
 * Class Description
 * @version Oct 24, 201710:55:45 AM
 * @author Ford.CHEN
 */
@Validated
@Configuration
@ConfigurationProperties(prefix = JenkinsProperties.PREFIX)
@Data
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
    
}

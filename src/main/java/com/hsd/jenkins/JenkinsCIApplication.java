package com.hsd.jenkins;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
//@EnableConfigServer
@SpringCloudApplication
public class JenkinsCIApplication {

    private final static Logger logger = LoggerFactory.getLogger(JenkinsCIApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JenkinsCIApplication.class, args);
        logger.info("Application is success!");
    }
}

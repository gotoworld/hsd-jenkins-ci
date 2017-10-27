package com.hsd.jenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;


@EnableEurekaClient
//@EnableConfigServer
@SpringCloudApplication
@Slf4j
public class JenkinsCIApplication {

    public static void main(String[] args) {
        SpringApplication.run(JenkinsCIApplication.class, args);
        log.info("Application is success!"); 
    }
}

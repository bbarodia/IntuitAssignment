package com.intuit.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.util.logging.Logger;

@ComponentScan
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class UserProfile {

    final static Logger logger = Logger.getLogger(UserProfile.class.getName());

    public static void main(String[] args) {
        String msg = "User Profile is now listening ..... \n";

        SpringApplication.run(UserProfile.class, args);
        logger.info(msg);
    }


}
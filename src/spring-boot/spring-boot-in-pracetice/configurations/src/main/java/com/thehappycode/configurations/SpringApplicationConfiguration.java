package com.thehappycode.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Properties;

public class SpringApplicationConfiguration {
    public static ConfigurableApplicationContext run(Class<?> primarySource, String[] args){
        Properties properties = new Properties();
        properties.setProperty("spring.config.on-not-found", "ignore");

        SpringApplication application = new SpringApplication(new Class[]{primarySource});
        application.setDefaultProperties(properties);

        System.out.println("-> SpringApplicationConfiguration");
        return application.run(args);


    }
}

package com.thehappycode.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import static com.thehappycode.configurations.SpringApplicationConfiguration.run;

@SpringBootApplication
@EnableConfigurationProperties(CustomProperties.class)

// Thay thế cho EnableConfigurationProperties.
// Sử dụng trong trường hợp có nhiều class sử dụng annotation với @ConfigurationProperties
//@ConfigurationPropertiesScan
public class ConfigurationsApplication {

    public static void main(String[] args) {

//         TODO: Using the Default configuration.
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(ConfigurationsApplication.class, args);

//         TODO: Using SpringApplication’s setDefaultProperties method
//        ConfigurableApplicationContext applicationContext =
//            SpringApplicationConfiguration.run(ManagingConfigurationsApplication.class, args);

//         TODO: dbConfig - Sử dụng @PropertySource
        DbConfiguration dbConfiguration = applicationContext.getBean(DbConfiguration.class);
        System.out.println(dbConfiguration.toString());

//         TODO: Config data file
//        ConfigDataFile.run();

//        TODO: Custom Properties with ConfigurationProperties
        CustomPropertiesConfiguration customPropertiesConfiguration
                = applicationContext.getBean(CustomPropertiesConfiguration.class);
        System.out.println(customPropertiesConfiguration.getCustomProperties().toString());
    }

}

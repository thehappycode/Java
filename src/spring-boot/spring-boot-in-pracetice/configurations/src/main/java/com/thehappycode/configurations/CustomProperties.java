package com.thehappycode.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("custom.properties")
public class CustomProperties {
    private final String name;
    private final String ip;
    private final int port;
    private final CustomSecurity security;

    public CustomProperties(
        String name,
        String ip,
        @DefaultValue("8989") int port, // Annotation @DefaultValue() để set giá trị mặc định
        CustomSecurity security
    ){
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.security = security;
    }

    public String getName(){
        return name;
    }

    public String getIp(){
        return ip;
    }

    public int getPort(){
        return port;
    }

    public CustomSecurity getSecurity(){
        return security;
    }

    @Override
    public String toString(){
        return "CustomProperties {" +
                "name = " + name +
                ", ip = " + ip +
                ", port = " + port +
                ", security = " + security +
                "}";
    }
}

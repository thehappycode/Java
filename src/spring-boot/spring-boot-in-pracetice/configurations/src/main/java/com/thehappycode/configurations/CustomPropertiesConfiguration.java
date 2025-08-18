package com.thehappycode.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Custom Properties giúp ta định nghĩa một strong type bean để quan lý type-safety tốt cho việc validate những cấu hình.
@Service
public class CustomPropertiesConfiguration {
    private final CustomProperties customProperties;

    @Autowired
    public CustomPropertiesConfiguration(CustomProperties customProperties){
        this.customProperties = customProperties;
    }

    public CustomProperties getCustomProperties(){
        return customProperties;
    }
}

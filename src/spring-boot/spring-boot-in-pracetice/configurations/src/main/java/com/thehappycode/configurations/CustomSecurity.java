package com.thehappycode.configurations;

import java.util.List;

public class CustomSecurity {
    private final boolean enabled;
    private final  String token;
    private final List<String> roles;

    public CustomSecurity(
        boolean enabled,
        String token,
        List<String> roles
    ){
        this.enabled = enabled;
        this.token = token;
        this.roles = roles;
    }

    public boolean getEnabled(){
        return enabled;
    }

    public String getToken(){
        return token;
    }

    public  List<String> getRoles(){
        return roles;
    }

    @Override
    public String toString(){
        return "CustomSecurity {" +
                "enabled = " + enabled +
                ", token = " + token +
                ", roles =" + roles +
                "}";
    }
}

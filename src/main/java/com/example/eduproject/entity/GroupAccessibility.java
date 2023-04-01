package com.example.eduproject.entity;

import org.thymeleaf.util.StringUtils;

public enum GroupAccessibility {
    PRIVATE,PUBLIC;
    public static GroupAccessibility accessibilityFromPassword(String password){
        if(StringUtils.isEmpty(password)){
            return PUBLIC;
        }
        else return PRIVATE;
    }
}

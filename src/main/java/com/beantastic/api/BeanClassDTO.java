package com.beantastic.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BeanClassDTO {
    private String name;

    private String description;

    private List<Map<String, Integer>> beanClassStatistics;
    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public List<Map<String, Integer>> getBeanClassStatistics(){
        return beanClassStatistics;
    }
}

package com.beantastic.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDTO {
    private String name;

    private String description;

    private String quality;

    @JsonProperty("itemStatistics")
    private List<Map<String, Integer>> itemStatistics;


    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getQuality(){
        return quality;
    }

    public List<Map<String, Integer>> getItemClassStatistic(){
        return itemStatistics;
    }

}

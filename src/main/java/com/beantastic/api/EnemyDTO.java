package com.beantastic.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnemyDTO {
    private String name;

    private String description;

    private String difficulty;

    @JsonProperty("statistics")
    private List<Map<String, Integer>> statistics;

    private List<String> actions;

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public List<Map<String, Integer>> getStatistics(){
        return statistics;
    }

    public List<String> getActions(){
        return actions;
    }

}

package com.beantastic.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnemyDTO {
    private String name;

    private String description;

    private String difficulty;

    @JsonProperty("difficultyStatistics")
    private List<Map<String, Integer>> difficultyStatistics;

    private List<String> actions;

    private List<Map<String, Integer>> statisticModifiers;

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public List<Map<String, Integer>> getDifficultyStats(){
        return difficultyStatistics;
    }

    public List<String> getActions(){
        return actions;
    }

    public List<Map<String, Integer>> getStatisticModifiers(){
        return statisticModifiers;
    }
}

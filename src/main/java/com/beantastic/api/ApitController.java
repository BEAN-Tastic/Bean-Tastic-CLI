package com.beantastic.api;

import java.util.Map;

import org.springframework.web.client.RestClient;

import com.beantastic.player.PlayerClass;
import com.beantastic.player.PlayerClassManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApitController {

    static RestClient defaultClient = RestClient.create();

    public static void getDataFromServer(){
        getClasses();
        //getItems();
        //getEnemies();
    }

    public static void getClasses(){
        try {
            String result = defaultClient.get()
                    .uri("http://localhost:8080/beanClasses")
                    .retrieve()
                    .body(String.class);
                    
            if (result != null && !result.trim().isEmpty()){
                ObjectMapper mapper = new ObjectMapper();
                BeanClassDTO beanClassDTO = mapper.readValue(result, BeanClassDTO.class);

                PlayerClass playerClass = new PlayerClass();
                playerClass.setClass(beanClassDTO.getName(), beanClassDTO.getDescription());

                for (Map<String, Integer> stat : beanClassDTO.getBeanClassStatistics()) {
                    for (Map.Entry<String, Integer> entry : stat.entrySet()) {
                        switch (entry.getKey()) {
                            case "health":
                                playerClass.setHealth(entry.getValue());
                                break;
                            case "defense":
                                playerClass.setDefense(entry.getValue());
                                break;
                            case "damage":
                                playerClass.setDamage(entry.getValue());
                                break;
                            case "rizz":
                                playerClass.setRizz(entry.getValue());
                                break;
                        }
                    }
                }
            }else{
                System.out.println("server send back empty data!");
            }
            
        } catch (JsonProcessingException e) {
            // Handle exception
            e.printStackTrace();
        }
    }

    public static void getItems(){
        
    }

    public static void getEnemies(){
        
    }
}

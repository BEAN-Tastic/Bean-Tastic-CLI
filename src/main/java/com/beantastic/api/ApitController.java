package com.beantastic.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
            
            if (result != null && !result.trim().isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                List<BeanClassDTO> beanClassDTOList = mapper.readValue(result, new TypeReference<List<BeanClassDTO>>() {});
                
                for (BeanClassDTO beanClassDTO : beanClassDTOList) {
                    System.out.println(beanClassDTO.getBeanClassStatistics());
                }


                for (BeanClassDTO beanClassDTO : beanClassDTOList) {
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

                    PlayerClassManager.addClassToList(playerClass);
                }

            } else {
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

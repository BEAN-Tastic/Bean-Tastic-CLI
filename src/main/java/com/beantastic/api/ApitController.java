package com.beantastic.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.beantastic.enemies.Enemy;
import com.beantastic.enemies.EnemyManager;
import com.beantastic.items.ItemClass;
import com.beantastic.items.ItemManager;
import com.beantastic.logging.Logger;
import com.beantastic.player.PlayerClass;
import com.beantastic.player.PlayerClassManager;
import com.beantastic.stats.StatBlock;
import com.beantastic.stats.StatBlockable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApitController {

    static RestClient defaultClient = RestClient.create();

    public static PlayerClassManager getClasses() {
        PlayerClassManager playerClassManager = new PlayerClassManager();

        try {
            String result = defaultClient.get()
                    .uri("http://production.eba-2dtmknhm.eu-west-1.elasticbeanstalk.com/beanClasses")
                    .retrieve()
                    .body(String.class);

            // String result = "[{\"name\":\"Warrior\",\"description\":\"A strong warrior
            // class\",\"beanClassStatistics\":[{\"health\":100,\"defense\":50,\"damage\":20,\"rizz\":10}]},{\"name\":\"Mage\",\"description\":\"A
            // powerful mage
            // class\",\"beanClassStatistics\":[{\"health\":80,\"defense\":30,\"damage\":50,\"rizz\":20}]}]";

            if (result != null && !result.trim().isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                List<BeanClassDTO> beanClassDTOList = mapper.readValue(result, new TypeReference<List<BeanClassDTO>>() {
                });

                for (BeanClassDTO beanClassDTO : beanClassDTOList) {
                    int health = 0;
                    int defense = 0;
                    int damage = 0;
                    int rizz = 0;

                    for (Map<String, Integer> stat : beanClassDTO.getBeanClassStatistics()) {
                        for (Map.Entry<String, Integer> entry : stat.entrySet()) {
                            switch (entry.getKey()) {
                                case "HEALTH":
                                    health = entry.getValue();
                                    break;
                                case "DEFENSE":
                                    defense = entry.getValue();
                                    break;
                                case "ATTACK":
                                    damage = entry.getValue();
                                    break;
                                case "RIZZ":
                                    rizz = entry.getValue();
                                    break;
                            }
                        }
                    }

                    StatBlock stats = new StatBlock(health, defense, damage, rizz);
                    PlayerClass playerClass = new PlayerClass(beanClassDTO.getName(), beanClassDTO.getDescription(),
                            stats);

                    playerClassManager.addClass(playerClass);
                }

                return playerClassManager;
            } else {
                System.out.println("server send back empty data!");
                return playerClassManager;
            }

        } catch (JsonProcessingException e) {
            // Handle exception
            e.printStackTrace();
            return playerClassManager;
        }
    }

    public static ItemManager getItems(Logger logger, Random random, Scanner scanner) {
        List<ItemClass> items = new ArrayList<ItemClass>();
        ItemManager itemManager = null;

        try {
            String result = defaultClient.get()
                    .uri("http://production.eba-2dtmknhm.eu-west-1.elasticbeanstalk.com/items")
                    .retrieve()
                    .body(String.class);
        
            if (result != null && !result.trim().isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                List<ItemDTO> itemDTOList = mapper.readValue(result, new TypeReference<List<ItemDTO>>() {
                });

                for (ItemDTO ItemDTO : itemDTOList) {
                    
                    int health = 0;
                    int defense = 0;
                    int damage = 0;
                    int rizz = 0;

                    for (Map<String, Integer> stat : ItemDTO.getItemClassStatistic()) {
                        for (Map.Entry<String, Integer> entry : stat.entrySet()) {
                            switch (entry.getKey()) {
                                case "HEALTH":
                                    health = entry.getValue();
                                    break;
                                case "DEFENSE":
                                    defense = entry.getValue();
                                    break;
                                case "ATTACK":
                                    damage = entry.getValue();
                                    break;
                                case "RIZZ":
                                    rizz = entry.getValue();
                                    break;
                            }
                        }
                    }

                    StatBlock stats = new StatBlock(health, defense, damage, rizz);
                    ItemClass item = new ItemClass(ItemDTO.getName(), ItemDTO.getDescription(), stats,
                            ItemDTO.getQuality());
                    items.add(item);

                }

                return itemManager = new ItemManager(logger, random, scanner, items.toArray(new ItemClass[0]));

            } else {
                System.out.println("server send back empty data!");
                return itemManager;
            }

        } catch (JsonProcessingException e) {
            // Handle exception
            e.printStackTrace();
            return itemManager;
        }
    }

    public static EnemyManager getEnemies(Random random){
        List<Enemy> enemies = new ArrayList<Enemy>();
        EnemyManager enemyManager = null;
            try {
                String result = defaultClient.get()
                    .uri("http://production.eba-2dtmknhm.eu-west-1.elasticbeanstalk.com/enemies")
                    .retrieve()
                    .body(String.class);

                

                if (result != null && !result.trim().isEmpty()) {
                    ObjectMapper mapper = new ObjectMapper();
                    List<EnemyDTO> EnemyDTOList = mapper.readValue(result, new TypeReference<List<EnemyDTO>>() {});

                   
                    for (EnemyDTO EnemyDTO : EnemyDTOList) {
                        System.out.println(EnemyDTO.getName());
                        int health = 0;
                        int defense = 0;
                        int damage = 0;
                        int rizz = 0;

                        for (Map<String, Integer> stat : EnemyDTO.getDifficultyStats()) {
                        for (Map.Entry<String, Integer> entry : stat.entrySet()) {
                            switch (entry.getKey()) {
                                case "HEALTH":
                                    health = entry.getValue();
                                    break;
                                case "DEFENSE":
                                    defense = entry.getValue();
                                    break;
                                case "ATTACK":
                                    damage = entry.getValue();
                                    break;
                                case "RIZZ":
                                    rizz = entry.getValue();
                                    break;
                            }
                        }
                    }

                    StatBlock stats = new StatBlock(health, defense, damage, rizz);
                    Enemy enemy = new Enemy(EnemyDTO.getName(), stats, EnemyDTO.getDifficulty(), EnemyDTO.getActions().get(0), EnemyDTO.getDescription());
                    enemies.add(enemy);
                }

                return enemyManager = new EnemyManager(random, enemies.toArray(new Enemy[0])); 

            } else {
                System.out.println("server send back empty data!");
                return enemyManager;
            }

        } catch (JsonProcessingException e) {
            // Handle exception
            e.printStackTrace();
            return enemyManager;
        }
    }
}

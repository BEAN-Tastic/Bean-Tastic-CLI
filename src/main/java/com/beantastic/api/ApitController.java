package com.beantastic.api;

import java.util.List;
import java.util.Map;

import com.beantastic.stats.StatBlockMaker;
import com.beantastic.enemies.Enemy;
import com.beantastic.items.ItemClass;
import com.beantastic.player.PlayerClass;

public class ApitController {

    public static List<PlayerClass> getClasses() {
        return new ApiCaller<>("http://production.eba-2dtmknhm.eu-west-1.elasticbeanstalk.com/beanClasses", BeanClassDTO[].class)
                .getDTO()
                .stream()
                .map(ApitController::getPlayerClass)
                .toList();
    }

    private static PlayerClass getPlayerClass(BeanClassDTO beanClassDTO) {
        StatBlockMaker statBlockMaker = new StatBlockMaker();

        for (Map<String, Integer> stat : beanClassDTO.getBeanClassStatistics()) {
            for (Map.Entry<String, Integer> entry : stat.entrySet()) statBlockMaker.setValue(entry);
        }

        return new PlayerClass(beanClassDTO.getName(), beanClassDTO.getDescription(), statBlockMaker.createStatBlock());
    }

    private static ItemClass getItem (ItemDTO itemDTO) {
        StatBlockMaker statBlockMaker = new StatBlockMaker();

        for (Map<String, Integer> stat : itemDTO.getItemClassStatistic())
            for (Map.Entry<String, Integer> entry : stat.entrySet()) statBlockMaker.setValue(entry);

        return new ItemClass(itemDTO.getName(), itemDTO.getDescription(), statBlockMaker.createStatBlock(),
                itemDTO.getQuality());
    }

    public static Enemy getEnemy(EnemyDTO enemyDTO) {
        StatBlockMaker statBlockMaker = new StatBlockMaker();

        for (Map<String, Integer> stat : enemyDTO.getStatistics())
            for (Map.Entry<String, Integer> entry : stat.entrySet()) statBlockMaker.setValue(entry);

        return new Enemy(enemyDTO.getName(), statBlockMaker.createStatBlock(), enemyDTO.getDifficulty(),
                enemyDTO.getActions().getFirst(), enemyDTO.getDescription());
    }

    public static List<ItemClass> getItems() {
        return new ApiCaller<>("http://production.eba-2dtmknhm.eu-west-1.elasticbeanstalk.com/items", ItemDTO[].class)
                .getDTO()
                .stream()
                .map(ApitController::getItem)
                .toList();
    }

    public static List<Enemy> getEnemies(){
        return new ApiCaller<>("http://production.eba-2dtmknhm.eu-west-1.elasticbeanstalk.com/enemies", EnemyDTO[].class)
                .getDTO()
                .stream()
                .map(ApitController::getEnemy)
                .toList();
    }
}

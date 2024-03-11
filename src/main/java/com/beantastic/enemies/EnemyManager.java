package com.beantastic.enemies;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnemyManager {
    private final List<Enemy> easyEnemies;
    private final List<Enemy> mediumEnemies;
    private final List<Enemy> hardEnemies;
    private final Random random;

    public EnemyManager(Random random, Enemy... enemies){
        this.random = random;
        this.easyEnemies = Arrays.stream(enemies).filter(enemy -> enemy.getDifficulty().equals("Easy")).toList();
        this.mediumEnemies = Arrays.stream(enemies).filter(enemy -> enemy.getDifficulty().equals("Medium")).toList();
        this.hardEnemies = Arrays.stream(enemies).filter(enemy -> enemy.getDifficulty().equals("Hard")).toList();
    }

    public Enemy getEnemy(int hardnessLevel){
        return switch (hardnessLevel) {
            case 1 -> getRandomEasyEnemy();
            case 2 -> getRandomMediumEnemy();
            case 3 -> getRandomHardEnemy();
            default -> null;
        };
    }

    private Enemy getRandomEasyEnemy(){
        int randomIndex = random.nextInt(easyEnemies.size());
        return easyEnemies.get(randomIndex);
    }

    private Enemy getRandomMediumEnemy(){
        int randomIndex = random.nextInt(mediumEnemies.size());
        return mediumEnemies.get(randomIndex);
    }

    private Enemy getRandomHardEnemy(){
        int randomIndex = random.nextInt(hardEnemies.size());
        return hardEnemies.get(randomIndex);
    }
}

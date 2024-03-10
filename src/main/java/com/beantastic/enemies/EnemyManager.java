package com.beantastic.enemies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyManager {
    //store all the enmies in here 
    private static Enemy currentEnemy;
    private static List<Enemy> easyEnemies = new ArrayList<>();
    private static List<Enemy> mediumEnemies = new ArrayList<>();
    private static List<Enemy> hardEnemies = new ArrayList<>();
    private static Random random = new Random();

    public static void setCurrentEnemy(int hardnessLevel){
        switch (hardnessLevel) {
            case 1:
                currentEnemy = getRandomEasyEnemy();
                break;
            case 2:
                currentEnemy = getRandomMediumEnemy();
                break;
            case 3:
                currentEnemy = getRandomHardEnemy();
                break;
        }
    }

    public static Enemy getCurrentEnemy(){
        return currentEnemy;
    }

    public static Enemy getRandomEasyEnemy(){
        int randomIndex = random.nextInt(easyEnemies.size());
        return easyEnemies.get(randomIndex);
    }

    public static Enemy getRandomMediumEnemy(){
        int randomIndex = random.nextInt(mediumEnemies.size());
        return mediumEnemies.get(randomIndex);
    }

    public static Enemy getRandomHardEnemy(){
        int randomIndex = random.nextInt(hardEnemies.size());
        return hardEnemies.get(randomIndex);
    }
    
    public static void setCurrentEnemeyToNull(){
        currentEnemy = null;
    }

    public static void addEasyEnemy(Enemy enemy){
        easyEnemies.add(enemy);
        System.out.println(easyEnemies.get(0).getName());
    }

    public static void addMediumEnemy(Enemy enemy){
        mediumEnemies.add(enemy);
        System.out.println(mediumEnemies.get(0).getName());
    }

    public static void addHardEnemy(Enemy enemy){
        hardEnemies.add(enemy);
        System.out.println(hardEnemies.get(0).getName());
    }

    //creates an enemy object and adds it to the respect dificulty list
    public void createEnemy(String name, int health, int damage, int requiredRizz, String difficulty, String attackDialogue, String description){
        Enemy enemy = new Enemy();

        enemy.setEnemy(name, health, damage, requiredRizz, difficulty, attackDialogue, description);

        if (enemy.getDifficulty().equals("Easy")) {
            addEasyEnemy(enemy);
        } else if(enemy.getDifficulty().equals("Medium")){
            addMediumEnemy(enemy);
        }else if(enemy.getDifficulty().equals("Hard")){
            addHardEnemy(enemy);
        }
    }

}

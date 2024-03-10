package com.beantastic.enemies;

import java.util.Scanner;

import com.beantastic.Main;
import com.beantastic.items.ItemManager;

public class Enemy{
    private String name;
    private int health;
    private int damage;
    private int requiredRizz;
    private int modifier; 
    private String difficulty; 
    private String attackDialogue;
    private String description;


    public void setEnemy(String name, int health, int damage, int requiredRizz, String difficulty, String attackDialogue, String description){
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.requiredRizz = requiredRizz;
        this.difficulty = difficulty;
        this.attackDialogue = attackDialogue;
        this.description = description;
    }

    //NAME
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    //HEALTH 
    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void takeDamage(int damage){
        health -= damage;
    }

    //DAMAGE
    public int getDamange(){
        return damage;
    }

    public void die(){
        Main.typewriter("You successfully slayed " + name);
    }

    //RIZZ
    public int getRizz(){
        return requiredRizz;
    }

    public void setRizz(int rizz){
        requiredRizz = rizz;
    }
 
    //DIFFICULTY
    public String getDifficulty(){
        return difficulty;
    }

    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }

    //ATTACK DIALOGUE
    public String getAttackDialgoue(){
        return attackDialogue;
    }
    
    public void setAttackDialogue(String attackDialogue){
        this.attackDialogue = attackDialogue;
    }

    //DESCRIPTION
    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}

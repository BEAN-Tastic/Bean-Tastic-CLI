package com.beantastic.player;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.Scanner;

import com.beantastic.Main;

public class Player {
    private String name;
    private String playerClass;
    private int health;
    private int defense; 
    private int damage; 
    private int rizz;
    private boolean died;
    private boolean rizzedUp;


    public void setPlayer(String playerClass, int health, int defense, int damage, int rizz){
        this.playerClass = playerClass;
        this.health = health;
        this.defense = defense;
        this.damage = damage;
        this.rizz = rizz;
    }

    //NAME
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    //CLASS
    public String getPlayerClass(){
        return playerClass;
    }

    public void setPlayerClass(String playerClass){
        this.playerClass = playerClass;
    }

    //HEALTH
    public int getHealth(){
        return health;
    }

    public void setHealth(int amount){
        this.health = amount;
    }

    public void modifyHealth(int modifier, boolean operatorCheck){
        if(operatorCheck){
            health += modifier;
        }
        else{
            health -= modifier;
        }
    }

    //DAMAGE
    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }

    public void modifyDamage(int modifier, boolean operatorCheck){
        if(operatorCheck){
            damage += modifier;
        }
        else{
            damage -= modifier;
        }
    }

    //DEFENSE 
    public int getDefense(){
        return defense;
    }

    public void modifyDefense(int modifier, boolean operatorCheck){
        if(operatorCheck){
            defense += modifier;
        }
        else{
            defense -= modifier;
        }
    }

    //RIZZ
    public void setRizz(int rizz){
        this.rizz = rizz;
    }

    public int getRizz(){
        return rizz;
    }

    public void modifyRizz(int modifier, boolean operatorCheck){
        if(operatorCheck){
            rizz += modifier;
        }
        else{
            rizz -= modifier;
        }
    }

    public void takeDamage(int damage){
        health -= damage; 
    }

    public void die(){
        Main.typewriter("...You DIED!");
        died = true;
        Main.gameOver(false);
    }

    public boolean isDead(){
        return died;
    }

    //RIZZED UP\\
    public void setIsRizzedUp(boolean rizzedUp){
        this.rizzedUp = rizzedUp;
    }
    public boolean isRizzedUp(){
        return rizzedUp;
    }
}

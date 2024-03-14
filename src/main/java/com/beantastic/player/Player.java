package com.beantastic.player;

import com.beantastic.items.ItemClass;
import com.beantastic.stats.StatBlockable;

import java.util.ArrayList;
import java.util.List;

public class Player implements StatBlockable {

    private int healthLost;
    private final String name;
    private final PlayerClass playerClass;
    private final List<ItemClass> items = new ArrayList<>();
    private boolean rizzedUp;

    public Player (String name, PlayerClass playerClass){
        this.name = name;
        this.playerClass = playerClass;
        this.healthLost = 0;
        this.rizzedUp = false;
    }

    //NAME
    public String getName(){
        return name;
    }

    //CLASS
    public PlayerClass getPlayerClass(){
        return playerClass;
    }

    //HEALTH
    public int health(){
        return maxHealth() - this.healthLost;
    }

    private int maxHealth() {
        return playerClass.health() +
                this.items.stream()
                        .mapToInt(ItemClass::health)
                        .sum();
    }

    public int damage(){
        return this.playerClass.damage() +
                this.items.stream()
                        .mapToInt(ItemClass::damage)
                        .sum();
    }

    //DEFENSE
    public int defense(){
        return this.playerClass.defense() +
                this.items.stream()
                        .mapToInt(ItemClass::defense)
                        .sum();
    }

    public int rizz(){
        return this.playerClass.rizz() +
                this.items.stream()
                        .mapToInt(ItemClass::rizz)
                        .sum();
    }

    public void takeDamage(int damage){
        this.healthLost = Math.min(Math.max((healthLost + damage), 0), maxHealth());
    }

    public void die(){
        this.healthLost = maxHealth();
    }

    public boolean isDead(){
        return this.health() <= 0;
    }

    //RIZZED UP\\
    public void setIsRizzedUp(boolean rizzedUp){
        this.rizzedUp = rizzedUp;
    }

    public boolean isRizzedUp(){
        return rizzedUp;
    }

    public void addItem(ItemClass item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        return """
                Name: %s
                Class: %s
                Health: %s
                Damage: %s
                Defense: %s
                Rizz: %s""".formatted(getName(), playerClass.getName(), health(), damage(), defense(), rizz());
    }
}

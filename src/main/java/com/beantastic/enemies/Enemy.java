package com.beantastic.enemies;

import com.beantastic.stats.StatBlock;
import com.beantastic.stats.StatBlockable;

public class Enemy implements StatBlockable {
    private final String name;

    private final StatBlock statBlock;
    private int healthLost;
    private final String difficulty;
    private final String attackDialogue;
    private final String description;


    public Enemy (String name, StatBlock statBlock, String difficulty, String attackDialogue, String description) {
        this.statBlock = statBlock;
        this.name = name;
        this.healthLost = 0;
        this.difficulty = difficulty;
        this.attackDialogue = attackDialogue;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public int health(){
        return this.statBlock.health() - healthLost;
    }

    public int defense() {
        return this.statBlock.health();
    }

    public void takeDamage(int damage){
        healthLost += damage;
    }

    public boolean isDead() {
        return healthLost >= this.statBlock.health();
    }

    public int damage(){
        return this.statBlock.damage();
    }

    public int rizz(){
        return this.statBlock.rizz();
    }

    public String getDifficulty(){
        return difficulty;
    }

    public String getAttackDialogue(){
        return attackDialogue;
    }

    public String getDescription(){
        return description;
    }
}

package com.beantastic.stats;

import java.util.Map;

public class StatBlockMaker {

    private int health = 0;

    private int defense = 0;
    private int rizz = 0;
    private int damage = 0;

    private void setDamage(int damage) {
        this.damage = damage;
    }

    private void setRizz(int rizz) {
        this.rizz = rizz;
    }

    private void setDefense(int defense) {
        this.defense = defense;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    public void setValue(Map.Entry<String, Integer> dataEntry) {
        switch (dataEntry.getKey()) {
            case "HEALTH" -> setHealth(dataEntry.getValue());
            case "DEFENSE" -> setDefense(dataEntry.getValue());
            case "ATTACK" -> setDamage(dataEntry.getValue());
            case "RIZZ" -> setRizz(dataEntry.getValue());
        }
    }

    public StatBlock createStatBlock() {
        return new StatBlock(health, defense, damage, rizz);
    }
}

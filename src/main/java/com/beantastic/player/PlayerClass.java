package com.beantastic.player;

public class PlayerClass {
    private String className;
    private String classDiscription;
    private int health;
    private int defense; 
    private int damage; 
    private int rizz;

//SETTERS\\
    public void setClass(String className, String classDiscription, int health, int defense, int damage, int rizz){
        this.className = className;
        this.classDiscription = classDiscription;
        this.health = health;
        this.defense = defense;
        this.damage = damage;
        this.rizz = rizz;
    }

    public void setClass(String className, String classDiscription){
        this.className = className;
        this.classDiscription = classDiscription;
    }

    public void setName(String className){
        this.className = className;
    }

    public void setDiscription(String classDiscription){
        this.classDiscription = classDiscription;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setDefense(int defense){
        this.defense = defense;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void setRizz(int rizz){
        this.rizz = rizz;
    }


//GETTERS\\
    public String getname(){
        return className;
    }

    public String getDiscription(){
        return classDiscription;
    }

    public int getHealth(){
        return health;
    }

    public int getDefense(){
        return defense;
    }

    public int getDamage(){
        return damage;
    }

    public int getRizz(){
        return rizz;
    }

}

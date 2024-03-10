package com.beantastic.items;

import com.beantastic.Main;
import com.beantastic.player.Player;

public class ItemClass {
    private String name;
    private String description;
    private String quality;
    private String affectedFeild;
    private String rarity;
    private int value;

    //add logic for items that affect multiple feilds 
    public void setItem(String name, String description, String quality, String affectedFeild, int value, String rarity){
        this.name = name;
        this.description = description;
        this.quality = quality;
        this.affectedFeild = affectedFeild;
        this.value = value;
        this.rarity = rarity;
    }

    //NAME
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    //DESCRIPTION
    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    //QUALITY
    public void setQuality(String quality){
        this.quality = quality;
    }

    public String getQuality(){
        return quality;
    }

    //AFFECTED FEILD 
    public void setAffectedFeild(String affectedFeild){
        this.affectedFeild = affectedFeild;
    }

    public String getAffectedFeild(){
        return affectedFeild;
    }

    //VALUE
    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public boolean checkItemType(){
        if(quality.equals("bad")) {
            return false;
        }else{
            return true;
        }
    }

    public void pickUpItem(Player player){
        switch (affectedFeild.toLowerCase()) {
            case "health":
                player.modifyHealth(value, checkItemType());
                Main.typewriter("Health: " + player.getHealth());
                break;
            case "damage":
                player.modifyDamage(value, checkItemType());
                Main.typewriter("Damage: " + player.getDamage());
                break;
            case "defense":
                player.modifyDefense(value, checkItemType());
                Main.typewriter("Defense: " + player.getDefense());
                break;
            case "rizz":
                player.modifyRizz(value, checkItemType());
                Main.typewriter("Rizz: " + player.getRizz());
                break;
        }
    }

    public String getRarity(){
        return rarity;
    }
}

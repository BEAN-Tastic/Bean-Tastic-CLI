package com.beantastic.items;

import com.beantastic.stats.StatBlock;
import com.beantastic.stats.StatBlockable;

public class ItemClass  implements StatBlockable {
    private final String name;
    private final String description;
    private final StatBlock statBlock;
    private final String rarity;

    //add logic for items that affect multiple fields
    public ItemClass(String name, String description, StatBlock statBlock, String rarity){
        this.name = name;
        this.description = description;
        this.statBlock = statBlock;
        this.rarity = rarity;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public boolean checkItemGood(){
        return statBlock.rizz() + statBlock.defense() + statBlock.health() + statBlock.rizz() >= 0;
    }

    public String pickUpDescription(){
        String details = "";
        if (this.health() != 0) details += "Health: " + (this.health() > 0 ? "+" : "") + this.health() + "\n";
        if (this.damage() != 0)  details += "Damage: " + (this.damage() > 0 ? "+" : "") + this.damage() + "\n";
        if (this.defense() != 0)  details += "Defense: " + (this.defense() > 0 ? "+" : "") + this.defense() + "\n";
        if (this.rizz() != 0)  details += "Rizz: " + (this.rizz() > 0 ? "+" : "") + this.rizz() + "\n";
        return  details;
    }



    public String getRarity(){
        return rarity;
    }

    @Override
    public int health() {
        return statBlock.health();
    }

    @Override
    public int defense() {
        return statBlock.defense();
    }

    @Override
    public int damage() {
        return statBlock.damage();
    }

    @Override
    public int rizz() {
        return statBlock.rizz();
    }
}

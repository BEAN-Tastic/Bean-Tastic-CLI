package com.beantastic.player;

import com.beantastic.stats.StatBlock;
import com.beantastic.stats.StatBlockable;

public class PlayerClass implements StatBlockable {
    private final String className;
    private final String classDescription;
    private final StatBlock statBlock;

    public PlayerClass(String className, String classDescription, StatBlock statBlock){
        this.className = className;
        this.classDescription = classDescription;
        this.statBlock = statBlock;
    }

//GETTERS\\
    public String getName(){
        return className;
    }

    public String getDescription(){
        return classDescription;
    }

    public int health(){
        return statBlock.health();
    }

    public int defense(){
        return statBlock.defense();
    }

    public int damage(){
        return statBlock.damage();
    }

    public int rizz(){
        return statBlock.rizz();
    }

}

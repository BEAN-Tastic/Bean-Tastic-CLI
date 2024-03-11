package com.beantastic.player;

import java.util.List;

public class PlayerClassManager {

    private final List<PlayerClass> classList;
    public PlayerClassManager (PlayerClass... playerClasses ){
        this.classList = List.of(playerClasses);
    }


    public List<PlayerClass> getClassList(){
        return classList;
    }
}

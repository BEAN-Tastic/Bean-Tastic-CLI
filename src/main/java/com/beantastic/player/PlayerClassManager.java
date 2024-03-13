package com.beantastic.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerClassManager {

    private final List<PlayerClass> classList;
    
    public PlayerClassManager (PlayerClass... playerClasses ){
        this.classList = new ArrayList<>(Arrays.asList(playerClasses));
    }

    public void addClass(PlayerClass playerClass) {
        classList.add(playerClass);
    }
    
    public List<PlayerClass> getClassList(){
        return classList;
    }
}

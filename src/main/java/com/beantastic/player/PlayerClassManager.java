package com.beantastic.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerClassManager {
    private static List<PlayerClass> classList = new ArrayList<>();


    public static List<PlayerClass> getClassList(){
        return classList;
    }

    public static void addClassToList(PlayerClass playerClass){
        classList.add(playerClass);
    }
}

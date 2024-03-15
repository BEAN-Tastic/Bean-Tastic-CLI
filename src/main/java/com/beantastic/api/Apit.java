package com.beantastic.api;

import com.beantastic.enemies.Enemy;
import com.beantastic.items.ItemClass;
import com.beantastic.player.PlayerClass;

import java.util.List;

public interface Apit {
    List<PlayerClass> getClasses();
    List<ItemClass> getItems();
    List<Enemy> getEnemies();
    }

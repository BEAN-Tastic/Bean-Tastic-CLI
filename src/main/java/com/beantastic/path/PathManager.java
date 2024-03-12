package com.beantastic.path;

import java.util.List;
import java.util.Scanner;

import com.beantastic.event.Obstacle;
import com.beantastic.logging.Logger;
import com.beantastic.enemies.EnemyManager;
import com.beantastic.event.CombatSystem;
import com.beantastic.event.ObstacleSystem;
import com.beantastic.items.ItemClass;
import com.beantastic.items.ItemManager;
import com.beantastic.player.Player;

public class PathManager {
    private final Player player;
    private final EnemyManager enemyManager;

    private final ItemManager itemManager;
    private int levelDifficulty;
    private final ObstacleSystem obstacleSystem;

    private final Logger logger;
    private final Scanner scanner;
    private final int numOfStagesInPath;


    public PathManager(Player player, EnemyManager enemyManager, ItemManager itemManager, ObstacleSystem obstacleSystem, Logger logger, Scanner scanner, int numOfStagesInPath) {
        this.player = player;
        this.enemyManager = enemyManager;
        this.itemManager = itemManager;
        this.logger = logger;
        this.levelDifficulty = 1;
        this.scanner = scanner;
        this.obstacleSystem = obstacleSystem;
        this.numOfStagesInPath = numOfStagesInPath;
    }

    public boolean generatePath(Obstacle startingObstacle, Obstacle finalObstacle){
        startingObstacle.run(player);
        List<Runnable> stages = List.of(
                () -> {
                    ItemClass item = new CombatSystem(logger, scanner, player, enemyManager.getEnemy(levelDifficulty), itemManager).doCombatEvent();
                    if(item != null) item = itemManager.pickUpItemOption(item);
                    if(item != null) player.addItem(item);
                    levelDifficulty++;
                    },
                () -> obstacleSystem.runObstacle(player)
        );

        for (int stage = 1; stage <= numOfStagesInPath; stage++) {
            stages.get((stage -1) % stages.size()).run();
            if (player.isDead() || player.isRizzedUp()) {
                return false;
            }
        }

        finalObstacle.run(player);
        return true;
    }
}

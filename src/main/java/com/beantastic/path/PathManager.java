package com.beantastic.path;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
    private final Random random;

    private final Logger logger;
    private final Scanner scanner;
    private final int numOfStagesInPath;


    public PathManager(Player player, EnemyManager enemyManager, ItemManager itemManager, Random random, Logger logger, Scanner scanner, int numOfStagesInPath) {
        this.player = player;
        this.enemyManager = enemyManager;
        this.itemManager = itemManager;
        this.logger = logger;
        this.levelDifficulty = 1;
        this.scanner = scanner;
        this.random = random;
        this.numOfStagesInPath = numOfStagesInPath;
    }

    //START PATH\\
    private void startPath(){
        logger.writeln("""
                You wake up finding yourself...
                On the kitchen floor... No bean has ever ventured this low before, well not any self-respecting bean that is... The kitchen counter looms above you, your home.

                Down on the floor, it's a different story. The floor is a realm of forgotten leftovers, crumbs, and the occasional sticky spill. It's where the unwanted and unappreciated foods reside, far from the glory of the counter above. As a bean, you've always been taught to stay on the counter, to avoid the disgrace of the floor at all costs.

                Yet, here you are, having fallen off the counter in a moment of carelessness or perhaps curiosity. The floor stretches out before you, mysterious and unfamiliar. The journey back to the counter seems daunting, but staying on the floor is not an option. What will you do?""");

        logger.writeln("""

                1. Find a way back home\s

                2. Panic!""");

        pickPath();
    }

    private void pickPath(){
        String pathOption = scanner.nextLine().toLowerCase();

        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("find a way back home")){
            logger.writeln("""

                    You move forward, looking for a way out
                    1. Keep walking forward

                    2. Look around""");
            keepWalking();
        } else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("panic") || pathOption.equals("panic!")){
            panic();
        }else {
            logger.writeln("\nPlease input a valid option");
            pickPath();
        }
    }

    private void panic(){
        logger.writeln("""

                You start to panic...

                1. Curl into a ball and hope for the best.

                2. Fall to the floor and sob.

                3. Panic more!
                """);

        panicOptions();

    }

    private void panicOptions(){
        String pathOption = scanner.nextLine().toLowerCase();

        switch (pathOption) {
            case "1", "one", "curl into a ball" -> {
            }
            case "2", "two", "sob" -> {

                logger.writeln("""
                        You make so much noise crying you attract something...
                        You hear it getting closer...
                        And closer...
                        """);
                new CombatSystem(logger, scanner, player, enemyManager.getEnemy(1), itemManager).doCombatEvent();
            }
            case "3", "three", "panic" -> {

                logger.writeln("You panic so much you explode!");
                player.die();
            }
            default -> {

                logger.writeln("\nPlease input a valid option");
                panicOptions();
            }
        }
    }

    private void keepWalking(){
        String pathOption = scanner.nextLine().toLowerCase();

        switch (pathOption) {
            case "1", "one", "keep walking forward" -> {
                //TODO: add some dialogue here for 1st path
            }
            case "2", "two", "look around" -> {
                //TODO: add some dialogue here for 2nd path
            }
            default -> {
                logger.writeln("Please input a valid option");
                keepWalking();
            }
        }

    }

    public boolean generatePath(){
        startPath();

        List<Runnable> stages = List.of(
                () -> {
                    ItemClass item = new CombatSystem(logger, scanner, player, enemyManager.getEnemy(levelDifficulty), itemManager).doCombatEvent();
                    if(item != null) item = itemManager.pickUpItemOption(item);
                    if(item != null) player.addItem(item);
                    levelDifficulty++;
                    },
                () -> ObstacleSystem.obstacle(random, scanner)
        );

        for (int stage = 1; stage <= numOfStagesInPath; stage++) {
            stages.get((stage -1) % stages.size()).run();
            if (player.isDead() || player.isRizzedUp()) {
                return false;
            }
        }

        endPath();
        return true;
    }


    //END PATH\\
    private void endPath(){
        logger.writeln("After your long treacherous journey, you finally see it... The light at the end of the tunnel" +
            "The way up and home, away from this dark place");
    }
}

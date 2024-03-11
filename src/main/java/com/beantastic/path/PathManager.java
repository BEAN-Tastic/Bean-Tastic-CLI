package com.beantastic.path;

import java.util.Random;
import java.util.Scanner;

import com.beantastic.Main;
import com.beantastic.event.CombatSystem;
import com.beantastic.event.ObstacleSystem;
import com.beantastic.player.PlayerManager;

public class PathManager {
    private static int levelDifficulty = 1;
    private static final int numOfStagesInPath = 3;

    private static final Random random = new Random();

    public static void resetLevelDifficulty(){
        levelDifficulty = 1;
    }

    //START PATH\\
    public static void startPath(Scanner scanner){
        Main.typewriter("""
                You wake up finding yourself...
                On the kitchen floor... No bean has ever ventured this low before, well not any self-respecting bean that is... The kitchen counter looms above you, your home.

                Down on the floor, it's a different story. The floor is a realm of forgotten leftovers, crumbs, and the occasional sticky spill. It's where the unwanted and unappreciated foods reside, far from the glory of the counter above. As a bean, you've always been taught to stay on the counter, to avoid the disgrace of the floor at all costs.

                Yet, here you are, having fallen off the counter in a moment of carelessness or perhaps curiosity. The floor stretches out before you, mysterious and unfamiliar. The journey back to the counter seems daunting, but staying on the floor is not an option. What will you do?""");

        Main.typewriter("""

                1. Find a way back home\s

                2. Panic!""");

        pickPath(scanner);
    }

    public static void pickPath(Scanner scanner){
        String pathOption = scanner.nextLine().toLowerCase();

        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("find a way back home")){

            Main.typewriter("""

                    You move forward, looking for a way out
                    1. Keep walking forward

                    2. Look around""");

            keepWalking(scanner);

        } else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("panic") || pathOption.equals("panic!")){
            panic(scanner);
        }else{
            Main.typewriter("\nPlease input a valid option");
            pickPath(scanner);
        }
    }

    public static void panic(Scanner scanner){
        Main.typewriter("""

                You start to panic...

                1. Curl into a ball and hope for the best.

                2. Fall to the floor and sob.

                3. Panic more!
                """);

        panicOptions(scanner);

    }

    public static void panicOptions(Scanner scanner){
        String pathOption = scanner.nextLine().toLowerCase();

        switch (pathOption) {
            case "1", "one", "curl into a ball" -> {
            }
            case "2", "two", "sob" -> {

                Main.typewriter("""
                        You make so much noise crying you attract something...
                        You hear it getting closer...\s
                        And closer...
                        """);
                CombatSystem.startCombatEvent(scanner, 1);
            }
            case "3", "three", "panic" -> {

                Main.typewriter("You panic so much you explode!");
                PlayerManager.getPlayer().die();
            }
            default -> {

                Main.typewriter("\nPlease input a valid option");
                panicOptions(scanner);
            }
        }
    }

    public static void keepWalking(Scanner scanner){
        String pathOption = scanner.nextLine().toLowerCase();

        switch (pathOption) {
            case "1", "one", "keep walking forward" -> {
                //TODO: add some dialogue here for 1st path
            }
            case "2", "two", "look around" -> {
                //TODO: add some dialogue here for 2nd path
            }
            default -> {
                Main.typewriter("Please input a valid option");
                keepWalking(scanner);
            }
        }

    }

    public static void generatePath(Scanner scanner){
        startPath(scanner);

        for (int stage = 1; stage <= numOfStagesInPath; stage++) {
            if (PlayerManager.getPlayer().isDead() || PlayerManager.getPlayer().isRizzedUp()) {
                return;
            }

            CombatSystem.startCombatEvent(scanner, levelDifficulty);
            levelDifficulty++;
            ObstacleSystem.obstacle(random, scanner);
        }

        endPath();
    }


    //END PATH\\
    public static void endPath(){
        Main.typewriter("After your long treacherous journey, you finally see it... The light at the end of the tunnel" +
            "The way up and home, away from this dark place");
        //game over
        Main.gameOver(true);
    }
}

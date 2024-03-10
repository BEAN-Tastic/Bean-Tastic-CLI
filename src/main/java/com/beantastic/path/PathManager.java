package com.beantastic.path;

import static java.lang.StringTemplate.STR;

import java.security.PublicKey;
import java.util.Random;
import java.util.Scanner;

import com.beantastic.Main;
import com.beantastic.event.CombatSystem;
import com.beantastic.event.ObstacleSystem;
import com.beantastic.player.PlayerManager;

public class PathManager {
    private static int levelDifficulty = 1;
    private static int numOfStagesInPath = 3;

    private static Random random = new Random();

    public static void resetLevelDifficulty(){
        levelDifficulty = 1;
    }

    //START PATH\\
    public static void startPath(Scanner scanner){
        Main.typewriter("You wake up finding yourself...\nOn the kitchen floor... No bean has ever ventured this low before, well not any self-respecting bean that is... The kitchen counter looms above you, your home.\n\n"
            + "Down on the floor, it's a different story. The floor is a realm of forgotten leftovers, crumbs, and the occasional sticky spill. It's where the unwanted and unappreciated foods reside, far from the glory of the counter above. As a bean, you've always been taught to stay on the counter, to avoid the disgrace of the floor at all costs."
            + "\n\nYet, here you are, having fallen off the counter in a moment of carelessness or perhaps curiosity. The floor stretches out before you, mysterious and unfamiliar. The journey back to the counter seems daunting, but staying on the floor is not an option. What will you do?");

        Main.typewriter("\n1. Find a way back home \n" + 
            "\n2. Panic!");

        pickPath(scanner);
    }

    public static void pickPath(Scanner scanner){
        String pathOption = scanner.nextLine().toLowerCase();

        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("Find a way back home")){

            Main.typewriter("\nYou move forward, looking for a way out" + 
                "\n1. Keep walking forward\n" + 
                "\n2. Look around");

            keepWalking(scanner);

        } else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("panic") || pathOption.equals("panic!")){
            panic(scanner);
        }else{
            Main.typewriter("\nPlease input a valid option");
            pickPath(scanner);
        }
    }

    public static void panic(Scanner scanner){
        Main.typewriter("\nYou start to panic...\n" + 
            "\n1. Curl into a ball and hope for the best.\n" + 
            "\n2. Fall to the floor and sob.\n" + 
            "\n3. Panic more!\n");

        panicOptions(scanner);
        
    }

    public static void panicOptions(Scanner scanner){
        String pathOption = scanner.nextLine().toLowerCase();

        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("curl into a ball")){

        } else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("sob")){

            Main.typewriter("You make so much noise crying you attract something...\n" + 
                "You hear it getting closer... \nAnd closer..." + "\n");
            CombatSystem.startCombatEvent(scanner, 1);

        } else if(pathOption.equals("3") || pathOption.equals("three") || pathOption.equals("panic")){

            Main.typewriter("You panic so much you explode!");
            PlayerManager.getPlayer().die();

        }else{

            Main.typewriter("\nPlease input a valid option");
            panicOptions(scanner);
        }
    }

    public static void keepWalking(Scanner scanner){
        String pathOption = scanner.nextLine().toLowerCase();

        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("keep walking forward")){
            //add some dialogue here
        } else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("look around")){
            //add some dialogue here
        } else{
            Main.typewriter("Please input a valid option");
            keepWalking(scanner);
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
        Main.typewriter("After your long tretious journey, you finally see it... The light at the end of the tunnel" + 
            "The way up and home, away from this dark place");
        //game over
        Main.gameOver(true);
    }
}

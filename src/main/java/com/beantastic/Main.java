package com.beantastic;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.beantastic.enemies.Enemy;
import com.beantastic.enemies.EnemyManager;
import com.beantastic.event.ObstacleSystem;
import com.beantastic.items.ItemClass;
import com.beantastic.items.ItemManager;
import com.beantastic.path.PathManager;
import com.beantastic.player.Player;
import com.beantastic.player.PlayerClass;
import com.beantastic.player.PlayerClassManager;
import com.beantastic.player.PlayerManager;
import com.beantastic.stats.StatBlock;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static Scanner scanner;

    public static void main(String[] args) {
        Random random = new Random();
        scanner = new Scanner(System.in);

        //TEMP DATA FOR NOW
        addEnemies();
        PlayerClassManager playerClassManager = addClass();
        ItemManager itemManager = addItems(random);
        PlayerManager playerManager = new PlayerManager(scanner, playerClassManager.getClassList());

        // START GAME
        startGame(playerClassManager, itemManager, playerManager);
    }

    //TEMP DATA INSERTION\\
    public static PlayerClassManager addClass(){
        return new PlayerClassManager(
                new PlayerClass("Green Bean Warrior",
                        "A sturdy bean skilled in close combat, wielding the might of beans with brute force.",
                        new StatBlock(5, 3, 2, 2)),
                new PlayerClass("Magic Coffee Bean",
                        "A magical bean adept at harnessing the mystical energies of beans to cast powerful spells.",
                        new StatBlock(3, 2, 3, 3)),
                new PlayerClass("Sneaky Black Bean",
                        "A nimble bean with a knack for stealth and deception, striking enemies from the shadows.",
                        new StatBlock(5, 3, 2, 2)),
                new PlayerClass("Divine Lima Bean",
                        "A benevolent bean with divine powers, healing allies and smiting foes with righteous fury.",
                        new StatBlock(5, 3, 2, 2)),
                new PlayerClass("Arrowhead Pinto Bean",
                        "A skilled bean archer, raining down arrows upon enemies with deadly precision.",
                        new StatBlock(5, 3, 2, 2)));
    }

    public static ItemManager addItems(Random random){
        return new ItemManager(
                random,
                scanner,
                new ItemClass("SHOE", "HOT PINK HIGH HEEL", new StatBlock(0, 0, 0, 1), "Common"),
                new ItemClass("tea cup", "steaming black tea", new StatBlock(0, 0, -1, 0), "Rare"));
    }

    public static void addEnemies(){
        Enemy easEnemy = new Enemy();
        easEnemy.setEnemy("Sorcerer Soup", 3, 1, 2, "Easy",
            "Fiery Ladle Strike- The Sorcerer Soup hurls a fiery ladle at you, inflicting heavy damage to your health.",
            "A mystical soup with potent magical properties.");
        EnemyManager.addEasyEnemy(easEnemy);


        Enemy midEnemy = new Enemy();
        midEnemy.setEnemy("Cursed Carrot", 5, 2, 3, "Medium",
            "Toxic Root Slam- The Cursed Carrot slams its toxic roots into the ground, weakening your defenses.",
            "A carrot cursed by dark magic, capable of unleashing havoc.");
        EnemyManager.addMediumEnemy(midEnemy);

        Enemy hardEnemy = new Enemy();
        hardEnemy.setEnemy("Haunted Hamburger", 7, 3, 4, "Hard",
            "Seductive Sizzle- The Haunted Hamburger emits a seductive sizzle, enchanting you and lowering your charisma.",
            "A haunted hamburger possessed by vengeful spirits.");
        EnemyManager.addHardEnemy(hardEnemy);

    }

    public static void startGame(PlayerClassManager playerClassManager, ItemManager itemManager, PlayerManager playerManager){
        System.out.println("""
                  ____                         _            _   _                    _                 _                  \s
                 |  _ \\                       | |          | | (_)          /\\      | |               | |                 \s
                 | |_) | ___  __ _ _ __ ______| |_ __ _ ___| |_ _  ___     /  \\   __| |_   _____ _ __ | |_ _   _ _ __ ___ \s
                 |  _ < / _ \\/ _` | '_ \\______| __/ _` / __| __| |/ __|   / /\\ \\ / _` \\ \\ / / _ \\ '_ \\| __| | | | '__/ _ \\\s
                 | |_) |  __/ (_| | | | |     | || (_| \\__ \\ |_| | (__   / ____ \\ (_| |\\ V /  __/ | | | |_| |_| | | |  __/\s
                 |____/ \\___|\\__,_|_| |_|      \\__\\__,_|___/\\__|_|\\___| /_/    \\_\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|\
                """);

        System.out.println("\n\n");
        if (!getStartInput(scanner)) {
            typewriter("What a loser! \n");
            return;
        }
        Player player = playerManager.createPlayer();
        PathManager.generatePath(scanner);
    }

    public static boolean getStartInput(Scanner scanner) {
        typewriter("Do you want to play the game? \n");
        typewriter("1. Yes \n");
        typewriter("2. No \n");
        String playerInputString = scanner.nextLine().toLowerCase();
        if (playerInputString.equals("1") || playerInputString.equals("one") || playerInputString.equals("yes") ) {
            return true;
        } else if (playerInputString.equals("2") || playerInputString.equals("two") || playerInputString.equals("no")) {
            return false;
        } else {
            typewriter("Please input a valid answer \n");
            return getStartInput(scanner);
        }
    }

    public static void typewriter(String text) {
        IntStream.range(0, text.length())
                 .mapToObj(text::charAt)
                 .forEachOrdered(c -> {
                     System.out.print(c);
                     try {
                         Thread.sleep(ThreadLocalRandom.current().nextInt(50, 150 +1)); // Adjust the delay as needed
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 });
        System.out.println(); // Move to the next line after printing the text
    }

    public static void gameOver(boolean won){
        if(won){
            typewriter("Your are the mightiest Bean there ever was!");
            System.out.println("""
                      ____    _    __  __ _____    _____     _______ ____   \s
                     / ___|  / \\  |  \\/  | ____|  / _ \\ \\   / / ____|  _ \\  \s
                    | |  _  / _ \\ | |\\/| |  _|   | | | \\ \\ / /|  _| | |_) | \s
                    | |_| |/ ___ \\| |  | | |___  | |_| |\\ V / | |___|  _ < _\s
                     \\____/_/   \\_\\_|  |_|_____|  \\___/  \\_/  |_____|_| \\_(_)""");

            typewriter("""
                    Play again?

                    1. Yes!

                    2. No!""");
            String restartOption = scanner.nextLine().toLowerCase();

            if(restartOption.equals("1") || restartOption.equals("one") || restartOption.equals("yes")){
                restart();
            }

            //game finished

        }else{
            typewriter("Better luck next time!");
            System.out.println("""
                      ____    _    __  __ _____    _____     _______ ____   \s
                     / ___|  / \\  |  \\/  | ____|  / _ \\ \\   / / ____|  _ \\  \s
                    | |  _  / _ \\ | |\\/| |  _|   | | | \\ \\ / /|  _| | |_) | \s
                    | |_| |/ ___ \\| |  | | |___  | |_| |\\ V / | |___|  _ < _\s
                     \\____/_/   \\_\\_|  |_|_____|  \\___/  \\_/  |_____|_| \\_(_)""");

            typewriter("""
                    Restart game?

                    1. Yes!

                    2. No!""");
            String restartOption = scanner.nextLine().toLowerCase();

            if(restartOption.equals("1") || restartOption.equals("one") || restartOption.equals("yes")){
                restart();
            }

            //game finished

        }
    }

    public static void restart(){
        Random random = new Random();
        //TEMP DATA FOR NOW
        addEnemies();
        PlayerClassManager playerClassManager = addClass();
        ItemManager itemManager = addItems(random);
        PlayerManager playerManager = new PlayerManager(scanner, playerClassManager.getClassList());

        // START GAME
        startGame(playerClassManager, itemManager, playerManager);
        //reset all stats
        ObstacleSystem.resetBeanToObstacleList();
        PathManager.resetLevelDifficulty();
        startGame(playerClassManager, itemManager, playerManager);
    }
}

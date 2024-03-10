package com.beantastic;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.beantastic.api.ApitController;
import com.beantastic.enemies.Enemy;
import com.beantastic.enemies.EnemyManager;
import com.beantastic.event.CombatSystem;
import com.beantastic.event.ObstacleSystem;
import com.beantastic.items.ItemClass;
import com.beantastic.items.ItemManager;
import com.beantastic.path.PathManager;
import com.beantastic.player.PlayerClass;
import com.beantastic.player.PlayerClassManager;
import com.beantastic.player.PlayerManager;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        //TEMP DATA FOR NOW
        addEnemies();
        addClass();
        addItems();
        
        startGame();

        

    }

    //TEMP DATA INSERTION\\
    public static void addClass(){
        PlayerClass class1 = new PlayerClass();
        class1.setClass("Green Bean Warrior", 
            "A sturdy bean skilled in close combat, wielding the might of beans with brute force.", 
            5, 3, 2, 2);
        PlayerClassManager.addClassToList(class1);

        PlayerClass class2 = new PlayerClass();
        class2.setClass("Magic Coffee Bean", 
            "A magical bean adept at harnessing the mystical energies of beans to cast powerful spells.", 
            3, 2, 3, 3);
        PlayerClassManager.addClassToList(class2);

        PlayerClass class3 = new PlayerClass();
        class3.setClass("Sneaky Black Bean", 
            "A nimble bean with a knack for stealth and deception, striking enemies from the shadows.", 
            5, 3, 2, 2);
        PlayerClassManager.addClassToList(class3);

        PlayerClass class4 = new PlayerClass();
        class4.setClass("Divine Lima Bean", 
            "A benevolent bean with divine powers, healing allies and smiting foes with righteous fury.", 
            5, 3, 2, 2);
        PlayerClassManager.addClassToList(class4);

        PlayerClass class5 = new PlayerClass();
        class5.setClass("Arrowhead Pinto Bean", 
            "A skilled bean archer, raining down arrows upon enemies with deadly precision.", 
            5, 3, 2, 2);
        PlayerClassManager.addClassToList(class5);
    }

    public static void addItems(){
        ItemClass item1 = new ItemClass();
        item1.setItem("SHOE", "HOT PINK HIGH HEEL", "good", "rizz", 1, "Common");
        ItemManager.addItemToList(item1);;

        ItemClass item2 = new ItemClass();
        item2.setItem("tea cup", "steeming black tea", "bad", "damage", 1, "Rare");
        ItemManager.addItemToList(item2);
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

    public static void startGame(){
        System.out.println("  ____                         _            _   _                    _                 _                   \n" +
           " |  _ \\                       | |          | | (_)          /\\      | |               | |                  \n" +
           " | |_) | ___  __ _ _ __ ______| |_ __ _ ___| |_ _  ___     /  \\   __| |_   _____ _ __ | |_ _   _ _ __ ___  \n" +
           " |  _ < / _ \\/ _` | '_ \\______| __/ _` / __| __| |/ __|   / /\\ \\ / _` \\ \\ / / _ \\ '_ \\| __| | | | '__/ _ \\ \n" +
           " | |_) |  __/ (_| | | | |     | || (_| \\__ \\ |_| | (__   / ____ \\ (_| |\\ V /  __/ | | | |_| |_| | | |  __/ \n" +
           " |____/ \\___|\\__,_|_| |_|      \\__\\__,_|___/\\__|_|\\___| /_/    \\_\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|");

        System.out.println("\n\n");
        typewriter("Do you want to play the game? \n");
        typewriter("1. Yes \n");
        typewriter("2. No \n");

        String playerInpuString = scanner.nextLine().toLowerCase();
        getStartInput(playerInpuString);
       
    }

    public static void getStartInput(String playerInputString) {
        if (playerInputString.equals("1") || playerInputString.equals("one") || playerInputString.equals("yes") ) {
            PlayerManager.createPlayer(scanner, PlayerClassManager.getClassList());
            PathManager.generatePath(scanner);

        } else if (playerInputString.equals("2") || playerInputString.equals("two") || playerInputString.equals("no")) {
            typewriter("What a loser! \n");
            scanner.close();
        } else {
            typewriter("Please input a valid answer \n");
            String newInputString = scanner.nextLine().toLowerCase();
            getStartInput(newInputString);
        }
    }

    public static void typewriter(String text) {
        IntStream.range(0, text.length())
                 .mapToObj(i -> text.charAt(i))
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
            typewriter("Your are the mightest Bean there ever was!");
            System.out.println("  ____    _    __  __ _____    _____     _______ ____    \n"
                         + " / ___|  / \\  |  \\/  | ____|  / _ \\ \\   / / ____|  _ \\   \n"
                         + "| |  _  / _ \\ | |\\/| |  _|   | | | \\ \\ / /|  _| | |_) |  \n"
                         + "| |_| |/ ___ \\| |  | | |___  | |_| |\\ V / | |___|  _ < _ \n"
                         + " \\____/_/   \\_\\_|  |_|_____|  \\___/  \\_/  |_____|_| \\_(_)");
            
            typewriter("Play again?" + "\n\n1. Yes!" + "\n\n2. No!");
            String restartOption = scanner.nextLine().toLowerCase();
             
            if(restartOption.equals("1") || restartOption.equals("one") || restartOption.equals("yes")){
                restart();
            }else if(restartOption.equals("2") || restartOption.equals("two") || restartOption.equals("no")){
                //game finished 
            }
        }else{
            typewriter("Better luck next time!");
            System.out.println("  ____    _    __  __ _____    _____     _______ ____    \n"
                         + " / ___|  / \\  |  \\/  | ____|  / _ \\ \\   / / ____|  _ \\   \n"
                         + "| |  _  / _ \\ | |\\/| |  _|   | | | \\ \\ / /|  _| | |_) |  \n"
                         + "| |_| |/ ___ \\| |  | | |___  | |_| |\\ V / | |___|  _ < _ \n"
                         + " \\____/_/   \\_\\_|  |_|_____|  \\___/  \\_/  |_____|_| \\_(_)");

            typewriter("Restart game?" + "\n\n1. Yes!" + "\n\n2. No!");
            String restartOption = scanner.nextLine().toLowerCase();

            if(restartOption.equals("1") || restartOption.equals("one") || restartOption.equals("yes")){
                restart();
            }else if(restartOption.equals("2") || restartOption.equals("two") || restartOption.equals("no")){
                //game finished 
            }
            
            
        }
    }

    public static void restart(){
        //reset all stats
        ObstacleSystem.resetBennToObstaclelist();
        PathManager.resetLevelDifficulty();
        PlayerManager.resetPlayer();
        startGame();
    }
}

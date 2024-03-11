package com.beantastic;

import java.util.Random;
import java.util.Scanner;

import com.beantastic.enemies.Enemy;
import com.beantastic.enemies.EnemyManager;
import com.beantastic.items.ItemClass;
import com.beantastic.items.ItemManager;
import com.beantastic.logging.Logger;
import com.beantastic.path.PathManager;
import com.beantastic.player.Player;
import com.beantastic.player.PlayerClass;
import com.beantastic.player.PlayerClassManager;
import com.beantastic.player.PlayerManager;
import com.beantastic.stats.StatBlock;

public class Main {

    private final Random random;

    private final Logger logger;
    private final Scanner scanner;
    private final EnemyManager enemyManager;
    private final ItemManager itemManager;
    private final PlayerManager playerManager;

    public Main(Random random, Logger logger, Scanner scanner, EnemyManager enemyManager, ItemManager itemManager, PlayerManager playerManager) {
        this.random = random;
        this.logger = logger;
        this.scanner = scanner;
        this.enemyManager = enemyManager;
        this.itemManager = itemManager;
        this.playerManager = playerManager;
    }


    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        Logger logger = new Logger(System.out);

        //TEMP DATA FOR NOW
        EnemyManager enemyManager = addEnemies(random);
        PlayerClassManager playerClassManager = addClass();
        ItemManager itemManager = addItems(logger, random, scanner);
        PlayerManager playerManager = new PlayerManager(logger, scanner, playerClassManager.getClassList());

        Main main = new Main(random, logger, scanner, enemyManager, itemManager, playerManager);
        // START GAME
        boolean play;
        do {
            play = main.startGame();
        } while (play);
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

    public static ItemManager addItems(Logger logger, Random random, Scanner scanner){
        return new ItemManager(
                logger,
                random,
                scanner,
                new ItemClass("SHOE", "HOT PINK HIGH HEEL", new StatBlock(0, 0, 0, 1), "Common"),
                new ItemClass("tea cup", "steaming black tea", new StatBlock(0, 0, -1, 0), "Rare"));
    }

    public static EnemyManager addEnemies(Random random){
        return new EnemyManager(random,
                new Enemy("Sorcerer Soup", new StatBlock(3, 0, 1, 2), "Easy",
                        "Fiery Ladle Strike- The Sorcerer Soup hurls a fiery ladle at you, inflicting heavy damage to your health.",
                        "A mystical soup with potent magical properties."),
                new Enemy("Cursed Carrot", new StatBlock( 5, 0, 2, 3), "Medium",
                        "Toxic Root Slam- The Cursed Carrot slams its toxic roots into the ground, weakening your defenses.",
                        "A carrot cursed by dark magic, capable of unleashing havoc."),
                new Enemy("Haunted Hamburger", new StatBlock(7, 0, 3, 4), "Hard",
                        "Seductive Sizzle- The Haunted Hamburger emits a seductive sizzle, enchanting you and lowering your charisma.",
                        "A haunted hamburger possessed by vengeful spirits."));
    }

    public boolean startGame(){
        logger.println("""
                  ____                         _            _   _                    _                 _                  \s
                 |  _ \\                       | |          | | (_)          /\\      | |               | |                 \s
                 | |_) | ___  __ _ _ __ ______| |_ __ _ ___| |_ _  ___     /  \\   __| |_   _____ _ __ | |_ _   _ _ __ ___ \s
                 |  _ < / _ \\/ _` | '_ \\______| __/ _` / __| __| |/ __|   / /\\ \\ / _` \\ \\ / / _ \\ '_ \\| __| | | | '__/ _ \\\s
                 | |_) |  __/ (_| | | | |     | || (_| \\__ \\ |_| | (__   / ____ \\ (_| |\\ V /  __/ | | | |_| |_| | | |  __/\s
                 |____/ \\___|\\__,_|_| |_|      \\__\\__,_|___/\\__|_|\\___| /_/    \\_\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|\
                
                """);
        if (!getStartInput()) {
            logger.writeln("What a loser!");
            return false;
        }
        Player player = playerManager.createPlayer();
        PathManager pathManager = new PathManager(player, enemyManager, itemManager, random, logger, scanner, 3);
        return gameOver(pathManager.generatePath());
    }

    private boolean getStartInput() {
        logger.writeln("""
                Do you want to play the game?
                
                1. Yes
                
                2. No
                """);
        String playerInputString = scanner.nextLine().toLowerCase();
        if (playerInputString.equals("1") || playerInputString.equals("one") || playerInputString.equals("yes") ) {
            return true;
        } else if (playerInputString.equals("2") || playerInputString.equals("two") || playerInputString.equals("no")) {
            return false;
        } else {
            logger.writeln("Please input a valid answer");
            return getStartInput();
        }
    }

    private boolean gameOver(boolean won){
        if (won) {
            logger.writeln("Your are the mightiest Bean there ever was!");
        } else {
            logger.writeln("Better luck next time!");
        }

        logger.println("""
                      ____    _    __  __ _____    _____     _______ ____   \s
                     / ___|  / \\  |  \\/  | ____|  / _ \\ \\   / / ____|  _ \\  \s
                    | |  _  / _ \\ | |\\/| |  _|   | | | \\ \\ / /|  _| | |_) | \s
                    | |_| |/ ___ \\| |  | | |___  | |_| |\\ V / | |___|  _ < _\s
                     \\____/_/   \\_\\_|  |_|_____|  \\___/  \\_/  |_____|_| \\_(_)""");
        logger.println("""
                    Play again?

                    1. Yes!

                    2. No!
                    """);
        String restartOption = scanner.nextLine().toLowerCase();

        return restartOption.equals("1") || restartOption.equals("one") || restartOption.equals("yes");
    }
}

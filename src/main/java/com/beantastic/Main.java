package com.beantastic;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.beantastic.api.Apit;
import com.beantastic.api.ApitController;
import com.beantastic.api.ApitOffline;
import com.beantastic.enemies.Enemy;
import com.beantastic.enemies.EnemyManager;
import com.beantastic.event.*;
import com.beantastic.items.ItemClass;
import com.beantastic.items.ItemManager;
import com.beantastic.logging.ChoiceOption;
import com.beantastic.logging.Logger;
import com.beantastic.logging.UserChoice;
import com.beantastic.oauth.GitHubOAuth;
import com.beantastic.path.PathManager;
import com.beantastic.player.Player;
import com.beantastic.player.PlayerClass;
import com.beantastic.player.PlayerManager;

public class Main {

        private final Random random;
        private final Logger logger;
        private final Scanner scanner;
        private final EnemyManager enemyManager;
        private final ItemManager itemManager;
        private final PlayerManager playerManager;

        public Main(Random random, Logger logger, Scanner scanner, EnemyManager enemyManager, ItemManager itemManager,
                        PlayerManager playerManager) {
                this.random = random;
                this.logger = logger;
                this.scanner = scanner;
                this.enemyManager = enemyManager;
                this.itemManager = itemManager;
                this.playerManager = playerManager;
        }

        public static void main(String[] args) throws IOException, InterruptedException {
                Random random = new Random();
                Scanner scanner = new Scanner(System.in);
                Logger logger = new Logger(System.out);

                printBean(logger);

                UserChoice<Boolean> runOffline = new UserChoice<>(scanner, logger, "Do you want To run online or offline", List.of(
                        new ChoiceOption<>("Online", () -> false),
                        new ChoiceOption<>("Offline", () -> true)
                ));
                Boolean offline = runOffline.getChoice().outcome().get();
                Apit apitController = offline
                        ? new ApitOffline()
                        : new ApitController(GitHubOAuth.getAccessToken());
                if (!offline) LoadingAnimation(logger);
                List<PlayerClass> playerClasses = apitController.getClasses();
                PlayerManager playerManager = new PlayerManager(logger, scanner, playerClasses);

                List<Enemy> enemies = apitController.getEnemies();
                EnemyManager enemyManager = new EnemyManager(random, enemies.toArray(Enemy[]::new));
                List<ItemClass> items = apitController.getItems();
                ItemManager itemManager = new ItemManager(logger, random, scanner,
                                items.toArray(ItemClass[]::new));
                Main main = new Main(random, logger, scanner, enemyManager, itemManager, playerManager);

                // START GAME
                boolean play;
                do play = main.startGame();
                while (play);
        }

        public boolean startGame() {
                if (!getStartInput()) {
                        logger.writeln("What a loser!");
                        return false;
                }
                Player player = playerManager.createPlayer();
                ObstacleSystem obstacleSystem = new ObstacleSystem(random, new OldBean(scanner, logger),
                                new FloorCrack(scanner, logger, random));
                PathManager pathManager = new PathManager(player, enemyManager, itemManager, obstacleSystem, logger,
                                scanner, 6);
                Obstacle start = new JourneyStart(logger, scanner,
                                new CombatSystem(logger, scanner, player, enemyManager.getEnemy(1), itemManager));
                Obstacle end = new JourneyEnd(logger);
                return gameOver(pathManager.generatePath(start, end));
        }

        private boolean getStartInput() {
                UserChoice<Boolean> choices = new UserChoice<>(scanner, logger, "Do you want to play the game?",
                                List.of(
                                                new ChoiceOption<>("Yes", () -> true),
                                                new ChoiceOption<>("No", () -> false)));
                return choices.getChoice().outcome().get();
        }

        public boolean gameOver(boolean won) {
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
                         \\____/_/   \\_\\_|  |_|_____|  \\___/  \\_/  |_____|_| \\_(_)
                        """);

                UserChoice<Boolean> playAgain = new UserChoice<>(scanner, logger, "Play again?",
                                List.of(
                                                new ChoiceOption<>("Yes!", () -> true),
                                                new ChoiceOption<>("No!", () -> false)));
                return playAgain.getChoice().outcome().get();
        }

        public static void LoadingAnimation(Logger logger) {

            logger.print("Loading");
            for (int i = 0; i < 10; i++) {
                logger.print(".");
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.println();
            logger.println();
            logger.println();
        }

        public static void printBean(Logger logger){
            logger.println("""
                ____                         _            _   _                    _                 _                  \s
                |  _ \\                       | |          | | (_)          /\\      | |               | |                 \s
                | |_) | ___  __ _ _ __ ______| |_ __ _ ___| |_ _  ___     /  \\   __| |_   _____ _ __ | |_ _   _ _ __ ___ \s
                |  _ < / _ \\/ _` | '_ \\______| __/ _` / __| __| |/ __|   / /\\ \\ / _` \\ \\ / / _ \\ '_ \\| __| | | | '__/ _ \\\s
                | |_) |  __/ (_| | | | |     | || (_| \\__ \\ |_| | (__   / ____ \\ (_| |\\ V /  __/ | | | |_| |_| | | |  __/\s
                |____/ \\___|\\__,_|_| |_|      \\__\\__,_|___/\\__|_|\\___| /_/    \\_\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|\
                """);
            logger.println( """
                                                          @@@ \s
                                                         @@@@ \s
                              @@@@@@@@@@@@@@             @@@@ \s
                           @@@@@@@@@@@@@@@@@@@@          @@@@ \s
                         @@@@@@@          @@@@@@@       @@@@@ \s
                       @@@@@@                @@@@@      @@@@@ \s
                      @@@@@                   @@@@@     @@@@@ \s
                     @@@@@    @@          @@    @@@@   @@@@@@ \s
                    @@@@@    @@@@        @@@@   @@@@   @@@@@@ \s
                   @@@@@      @@          @@    @@@@@  @@@@@@ \s
                   @@@@                         @@@@@  @@@@@@@ \s
                  @@@@            @@@@@@@       @@@@@ @@@@@@@@@
                  @@@@                          @@@@@ @@@@@@@@@
                  @@@@                          @@@@    @@@@  \s
                 @@@@@  @@@@                   @@@@@@@@@@@@@  \s
                 @@@@   @@@@                   @@@@@@@@@@@@@  \s
                @@@@@  @@@@                    @@@@           \s
                @@@@   @@@@                    @@@@           \s
                @@@@   @@@@@                   @@@@           \s
                @@@@                          @@@@@           \s
                @@@@@                        @@@@@            \s
                 @@@@@                     @@@@@@             \s
                  @@@@@@@             @@@@@@@@@               \s
                    @@@@@@@@@@@@@@@@@@@@@@@@@                 \s
                       @@@@@@@@@@@@@@@@@@                     \s
                        @@@@      @@@@                        \s
                        @@@@      @@@@                        \s
                        @@@@@@    @@@@@@                      \s
                        @@@@@@     @@@@@                      \s
                """);
        }
}

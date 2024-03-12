package com.beantastic.event;

import com.beantastic.logging.Logger;
import com.beantastic.player.Player;

import java.util.Scanner;

public class JourneyStart implements Obstacle{

    private final Logger logger;

    private final Scanner scanner;

    private final CombatSystem combatSystem;

    public JourneyStart(Logger logger, Scanner scanner, CombatSystem combatSystem) {
        this.logger = logger;
        this.scanner = scanner;
        this.combatSystem = combatSystem;
    }

    @Override
    public void run(Player player) {
        logger.writeln("""
                You wake up finding yourself...
                On the kitchen floor... No bean has ever ventured this low before, well not any self-respecting bean that is...
                The kitchen counter looms above you, your home.

                Down on the floor, it's a different story.
                The floor is a realm of forgotten leftovers, crumbs, and the occasional sticky spill.
                It's where the unwanted and unappreciated foods reside, far from the glory of the counter above.
                As a bean, you've always been taught to stay on the counter, to avoid the disgrace of the floor at all costs.

                Yet, here you are, having fallen off the counter in a moment of carelessness or perhaps curiosity.
                The floor stretches out before you, mysterious and unfamiliar.
                The journey back to the counter seems daunting, but staying on the floor is not an option.
                What will you do?
                """);

        logger.writeln("""
                1. Find a way back home

                2. Panic!""");

        pickPath(player);
    }

    private void pickPath(Player player){
        String pathOption = scanner.nextLine().toLowerCase();

        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("find a way back home")){
            logger.writeln("""
                    You move forward, looking for a way out
                    
                    1. Keep walking forward

                    2. Look around""");
            keepWalking(player);
        } else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("panic") || pathOption.equals("panic!")){
            panic(player);
        }else {
            logger.writeln("Please input a valid option");
            pickPath(player);
        }
    }

    private void panic(Player player){
        logger.writeln("""
                You start to panic...

                1. Curl into a ball and hope for the best.

                2. Fall to the floor and sob.

                3. Panic more!
                """);

        panicOptions(player);

    }

    private void panicOptions(Player player){
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
                combatSystem.doCombatEvent();
            }
            case "3", "three", "panic" -> {
                logger.writeln("You panic so much you explode!");
                player.die();
            }
            default -> {
                logger.writeln("Please input a valid option");
                panicOptions(player);
            }
        }
    }

    private void keepWalking(Player player){
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
                keepWalking(player);
            }
        }

    }
}

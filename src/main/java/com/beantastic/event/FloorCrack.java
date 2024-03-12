package com.beantastic.event;

import com.beantastic.logging.ChoiceOption;
import com.beantastic.logging.Logger;
import com.beantastic.logging.UserChoice;
import com.beantastic.player.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FloorCrack implements Obstacle {

    private final Scanner scanner;

    private final Logger logger;

    private final Random random;

    public FloorCrack(Scanner scanner, Logger logger, Random random) {
        this.scanner = scanner;
        this.logger = logger;
        this.random = random;
    }


    //CRACK IN THE FLOOR OBSTACLE\\
    @Override
    public void run(Player player){
        UserChoice<Void> crackChoice = new UserChoice<>(scanner, logger, "You come across a huge crack in the floor",
                List.of(
                        new ChoiceOption<>("Jump over the crack!", () -> {
                            jump(player);
                            return null;
                        }),
                        new ChoiceOption<>("No, I am afraid of heights", () -> {
                            walkAround(player);
                            return null;
                        })
                ));
        crackChoice.getChoice().outcome().get();
    }

    private void jump(Player player){
        int randNum = random.nextInt(101);

        logger.writeln("""
                You take a running start
                your little legs move as fast as they can carry you
                you reach the crack and take a leap...
                ...
                ..
                .
                """);

        if(randNum <= 20){
            logger.writeln("You plummet to your death");
            player.die();
        }else{
            logger.writeln("You just make it over the cavern");
        }
        logger.printBreak();
    }

    private void walkAround(Player player){
        UserChoice<Void> walkChoice = new UserChoice<>(scanner, logger, """
                You take the long way round and walk around the crack...
                ....
                ...
                ..
                .""",
                List.of(
                        new ChoiceOption<>("Carry on walking", () -> {
                            logger.writeln("You continue to walk around the crack... Who knew a crack could be so large");
                            if (random.nextInt(100+1) < 50) {
                                walkAround(player);
                            }else {
                                logger.writeln("You finally manage to get around the crack");
                            }
                            return null;
                        }),
                        new ChoiceOption<>("Give up", () -> {
                            logger.writeln("""
                                You fall to the floor slowly getting overwhelmed by how lost you are
                                You dont know how you ever are going to make it back home
                                You accept your fate and lie on the floor waiting for the end to come""");
                            player.die();
                            return null;
                        })
                ));
        walkChoice.getChoice().outcome().get();
    }
}

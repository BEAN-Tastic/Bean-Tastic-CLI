package com.beantastic.event;

import com.beantastic.logging.Logger;
import com.beantastic.player.Player;

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
        logger.writeln("""
                You come across a huge crack in the floor

                1. Jump over the crack!

                2. No, I am afraid of heights
                """);

        pickCrackOptions(player);
    }

    private void pickCrackOptions(Player player){
        String pathOption = scanner.nextLine().toLowerCase();

        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("jump")){
            jump(player);
        } else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("no")){
            walkAround(player);
        } else{
            logger.writeln("Please input a valid option");
            pickCrackOptions(player);
        }

    }

    private void jump(Player player){
        int randNum = random.nextInt(101);

        if(randNum <= 20){
            logger.writeln("""
                    You take a running start
                    your little legs move as fast as they can carry you
                    you reach the crack and take a leap...
                    ...
                    ..
                    .



                    You plummet to your death""");
            player.die();
        }else{
            logger.writeln("""
                    You just make it over the cavern
                    """);
        }
    }

    private void walkAround(Player player){
        logger.writeln("""
                You decided to take the long way round and walk around the crack...
                ....
                ...
                ..
                .""");
        logger.writeln("""


                1. Carry on walking

                2. Give up""");

        walkAroundOptions(player);
    }

    private void walkAroundOptions(Player player){
        String pathOption = scanner.nextLine().toLowerCase();

        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("walk")){
            logger.writeln("You continue to walk around the crack... Who knew a crack could be so large");
        } else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("give up")){
            logger.writeln("""
                    You fall to the floor slowly getting overwhelmed by how lost you are
                    You dont know how you ever are going to make it back home
                    You accept your fate and lie on the floor waiting for the end to come""");
            player.die();
        }else{
            logger.writeln("Please input a valid option");
            walkAroundOptions(player);
        }
    }

}

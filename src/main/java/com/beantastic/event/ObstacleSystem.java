package com.beantastic.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.beantastic.Main;
import com.beantastic.player.PlayerManager;

public class ObstacleSystem {

    private static final List<Integer> beenToObstacles = new ArrayList<>();

    public static void resetBeanToObstacleList(){
        beenToObstacles.clear();
    }

    public static int pickObstacleAndCheck(Random random){
        int randNum = random.nextInt(3);

        if(beenToObstacles.contains(randNum)){
            pickObstacleAndCheck(random);
        } else if( beenToObstacles.isEmpty()){
            return randNum;
        }

        return randNum;
    }

    public static void obstacle(Random random, Scanner scanner){
        int randNum = pickObstacleAndCheck(random);

        switch (randNum) {
            case 0:
            crackInTheFloor(scanner, random);
            beenToObstacles.add(randNum);
                break;
            case 1:
            oldMysteriousBean(scanner);
            beenToObstacles.add(randNum);
                break;
            case 2:

                break;
        }

    }

    //CRACK IN THE FLOOR OBSTACLE\\
    public static void crackInTheFloor(Scanner scanner, Random random){
        Main.typewriter("""
                You come across a huge crack in the floor\s

                1. Jump over the crack!\s

                2. No, I am afraid of heights
                """);

        pickCrackOptions(scanner, random);
    }

    public static void pickCrackOptions(Scanner scanner, Random random){
        String pathOption = scanner.nextLine().toLowerCase();

        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("jump")){
            jump(random);
        } else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("no")){
            walkAround(scanner);
        } else{
            Main.typewriter("Please input a valid option");
            pickCrackOptions(scanner, random);
        }

    }

    public static void jump(Random random){
        int randNum = random.nextInt(101);

        if(randNum <= 20){
            Main.typewriter("""
                    You take a running start
                    your little legs move as fast as they can carry you
                    you reach the crack and take a leap...
                    ...
                    ..
                    .



                    You plummet to your death""");
            PlayerManager.getPlayer().die();
        }else{
            Main.typewriter("You just make it over the cavern\n\n");
        }
    }

    public static void walkAround(Scanner scanner){
        Main.typewriter("""
                You decided to take the long way round and walk around the crack...
                ....
                ...
                ..
                .""");
        Main.typewriter("""


                1. Carry on walking

                2. Give up""");

        walkAroundOptions(scanner);
    }

    public static void walkAroundOptions(Scanner scanner){
        String pathOption = scanner.nextLine().toLowerCase();

        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("walk")){
            Main.typewriter("You continue to walk around the crack... Who knew a crack could be so large");
        } else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("give up")){
            Main.typewriter("""
                    You fall to the floor slowly getting overwhelmed by how lost you are
                    You dont know how you ever are going to make it back home
                    You accept your fate and lie on the floor waiting for the end to come""");
                //GAME OVER
        }else{
            Main.typewriter("Please input a valid option");
            walkAroundOptions(scanner);
        }
    }

    //MYSTERY OLD BEAN OBSTACLE\\
    public static void oldMysteriousBean(Scanner scanner){
        Main.typewriter("""
                A muffled cackle drifts to your ears

                 1. Walk towards the cackle

                 2. Hell nah!""");
        mysteryBeanOptions(scanner);
    }

    public static void mysteryBeanOptions(Scanner scanner){
        String pathOption = scanner.nextLine().toLowerCase();

        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("walk")){
            mysteryBean(scanner);
        } else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("hell nah")){
            Main.typewriter("You swiftly walk in the opposite direction of the cackle... You don't fuck with that creepy shit");
        }else{
            Main.typewriter("Please input a valid option");
            mysteryBeanOptions(scanner);
        }
    }

    public static void mysteryBean(Scanner scanner){
        Main.typewriter("""
                As you get closer you catch a glimpse of something or someone...
                You move closer to get a better look and see a green bean... An old green bean...

                Old Green Bean: Don't be scared come closer, so I can get a better look at you...
                I too fell to this place... I have been stuck here for many years...
                I have learnt things... I have a gift for you

                1. Take the gift

                2. Hell no, that's some creepy shit\s

                """);
        takeItemOption(scanner);

    }

    public static void takeItemOption(Scanner scanner){
        String pathOption = scanner.nextLine().toLowerCase();
        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("take the gift")){

            Main.typewriter("The ikd lady places a protection ward on you" + "\nThe protection ward increase you defense by one");
            Main.typewriter("Your defense is now: " + PlayerManager.getPlayer().defense());
            Main.typewriter("You go to thank the old green bean but she has disappeared into thin air...");

        }else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("hell no")){

            Main.typewriter("Old Green Bean: How dare you reject my gift!");
            Main.typewriter("The old green bean places a curse on you!" +
                "\nShe takes away 1 defense" + "Your defense is now: " + PlayerManager.getPlayer().defense());
        }else{
            Main.typewriter("Please input a valid option");
            takeItemOption(scanner);
        }
    }

    //

}

package com.beantastic.event;

import com.beantastic.items.ItemClass;
import com.beantastic.logging.Logger;
import com.beantastic.player.Player;
import com.beantastic.stats.StatBlock;

import java.util.Scanner;

public class OldBean implements Obstacle {

    private final Scanner scanner;

    private final Logger logger;

    public OldBean(Scanner scanner, Logger logger) {
        this.scanner = scanner;
        this.logger = logger;
    }


    //MYSTERY OLD BEAN OBSTACLE\\
    @Override
    public void run(Player player){
        logger.writeln("""
                A muffled cackle drifts to your ears

                 1. Walk towards the cackle

                 2. Hell nah!""");
        mysteryBeanOptions(player);
    }

    public void mysteryBeanOptions(Player player){
        String pathOption = scanner.nextLine().toLowerCase();

        if(pathOption.equals("1") || pathOption.equals("one") || pathOption.equals("walk")){
            mysteryBean(player);
        } else if(pathOption.equals("2") || pathOption.equals("two") || pathOption.equals("hell nah")){
            logger.writeln("You swiftly walk in the opposite direction of the cackle... You don't fuck with that creepy shit");
        }else{
            logger.writeln("Please input a valid option");
            mysteryBeanOptions(player);
        }
    }

    public void mysteryBean(Player player){
        logger.writeln("""
                As you get closer you catch a glimpse of something or someone...
                You move closer to get a better look and see a green bean... An old green bean...

                Old Green Bean: Don't be scared come closer, so I can get a better look at you...
                I too fell to this place... I have been stuck here for many years...
                I have learnt things... I have a gift for you

                1. Take the gift

                2. Hell no, that's some creepy shit\s

                """);
        takeItemOption(player);

    }

    private void takeItemOption(Player player){
        String pathOption = scanner.nextLine().toLowerCase();
        switch (pathOption) {
            case "1", "one", "take the gift" -> {
                player.addItem(new ItemClass("Witch's ward", "A minor boon that increases your defense", new StatBlock(0, 1, 0, 0), "Common"));
                logger.writeln("""
                        The ikd lady places a protection ward on you
                        The protection ward increase you defense by one
                        Your defense is now:\s""" + player.defense());
                logger.writeln("You go to thank the old green bean but she has disappeared into thin air...");
            }
            case "2", "two", "hell no" -> {
                player.addItem(new ItemClass("Witch's curse", "A curse that decreases your defense", new StatBlock(0, -1, 0, 0), "Common"));
                logger.writeln("Old Green Bean: How dare you reject my gift!");
                logger.writeln("""
                        The old green bean places a curse on you!
                        You feel your defenses drain
                        Your defense is now:\s""" + player.defense());
            }
            default -> {
                logger.writeln("Please input a valid option");
                takeItemOption(player);
            }
        }
    }
}

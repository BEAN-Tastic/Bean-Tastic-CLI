package com.beantastic.event;

import com.beantastic.items.ItemClass;
import com.beantastic.logging.ChoiceOption;
import com.beantastic.logging.Logger;
import com.beantastic.logging.UserChoice;
import com.beantastic.player.Player;
import com.beantastic.stats.StatBlock;

import java.util.List;
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
        UserChoice<Void> oldBeanChoice = new UserChoice<>(scanner, logger, "A muffled cackle drifts to your ears",
                List.of(
                        new ChoiceOption<>("Walk towards the cackle", () -> {
                            mysteryBean(player);
                            return null;
                        }),
                        new ChoiceOption<>("Hell nah", () -> {
                            logger.writeln("You swiftly walk in the opposite direction of the cackle... You don't fuck with that creepy shit");
                            return null;
                        })
                ));
        oldBeanChoice.getChoice().outcome().get();
    }

    public void mysteryBean(Player player){
        UserChoice<Void> mysteryGiftChoice = new UserChoice<>(scanner, logger, """
                As you get closer you catch a glimpse of something or someone...
                You move closer to get a better look and see a green bean... An old green bean...

                Old Green Bean: Don't be scared come closer, so I can get a better look at you...
                I too fell to this place... I have been stuck here for many years...
                I have learnt things... I have a gift for you
                """,
                List.of(
                        new ChoiceOption<>("Take the gift", () -> {
                            ItemClass gift = new ItemClass("Witch's ward", "A minor boon that increases your defense", new StatBlock(0, 1, 0, 0), "Common");
                            logger.writeln("The old lady places a protection ward on you");
                            player.addItem(gift);
                            logger.writeln(gift.pickUpDescription());
                            logger.writeln("You go to thank the old green bean but she has disappeared into thin air...");
                            logger.printBreak();
                            return null;
                        }),
                        new ChoiceOption<>("Hell nah, that's some creepy shit", () -> {
                            ItemClass curse = new ItemClass("Witch's curse", "A curse that decreases your defense", new StatBlock(0, -1, 0, 0), "Common");
                            logger.writeln("""
                                Old Green Bean: How dare you reject my gift!
                                
                                The old green bean places a curse on you!""");
                            player.addItem(curse);
                            logger.writeln(curse.pickUpDescription());
                            logger.printBreak();
                            return null;
                        }))
                );
        mysteryGiftChoice.getChoice().outcome().get();
    }
}

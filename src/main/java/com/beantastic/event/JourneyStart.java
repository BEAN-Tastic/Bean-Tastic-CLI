package com.beantastic.event;

import com.beantastic.logging.ChoiceOption;
import com.beantastic.logging.Logger;
import com.beantastic.logging.UserChoice;
import com.beantastic.player.Player;

import java.util.List;
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
        UserChoice<Void> choices = new UserChoice<>(scanner, logger,"""
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
                """,
                List.of(
                        new ChoiceOption<>("Find a way back home", () -> {
                            keepWalking();
                            return null;
                        }),
                        new ChoiceOption<>("Panic!", () -> {
                            panic(player);
                            return null;
                        })));
        choices.getChoice().outcome().get();
    }

    private void panic(Player player){
        UserChoice<Void> choices = new UserChoice<>(scanner, logger, "You start to panic...",
                List.of(
                        new ChoiceOption<>("Curl into a ball and hope for the best.", () -> null),
                        new ChoiceOption<>("Fall to the floor and sob.", () -> {
                            logger.writeln("""
                                You make so much noise crying you attract something...
                                You hear it getting closer...
                                And closer...
                                """);
                            combatSystem.doCombatEvent();
                            return null;
                        }),
                        new ChoiceOption<>("Panic more!", () -> {
                            logger.writeln("You panic so much you explode!");
                            player.die();
                            return null;
                        })
                ));
        ChoiceOption<Void> choice = choices.getChoice();
        choice.outcome().get();
    }

    private void keepWalking(){
        UserChoice<Void> choices = new UserChoice<>(scanner, logger, "You move forward, looking for a way out",
                List.of(
                        new ChoiceOption<>("Keep walking forward.", () -> null),
                        new ChoiceOption<>("Look around.", () -> null)
                ));
        choices.getChoice().outcome().get();
    }
}

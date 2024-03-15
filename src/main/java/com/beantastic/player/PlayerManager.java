package com.beantastic.player;

import java.util.List;
import java.util.Scanner;

import com.beantastic.logging.ChoiceOption;
import com.beantastic.logging.Logger;
import com.beantastic.logging.UserChoice;

public class PlayerManager {
    private final Logger logger;
    private final List<PlayerClass> classList;
    private final Scanner scanner;

    private boolean canChooseCoffee;

    public static Player getPlayer() { // TODO remove dummy once static migration is done
        return null;
    }

    public PlayerManager (Logger logger, Scanner scanner, List<PlayerClass> classList) {
        this.logger = logger;
        this.scanner = scanner;
        this.classList = classList;
        this.canChooseCoffee = false;
    }

    public Player createPlayer(){
        logger.writeln("Create your bean:");
        PlayerMaker playerMaker = new PlayerMaker();
        Player player = playerMaker.setName(checkName(null))
                .setPlayerClass(pickClass())
                .createPlayer();
        displayCharacter(player);
        logger.printBreak();
        return player;
    }

    private String checkName(String name){
        if (name == null || name.isEmpty()){
            logger.write("Please enter a name: ");
            String playerName = scanner.nextLine();
            return checkName(playerName);
        }
        if(name.length() > 10){
            logger.writeln("Name is too long!");
            return checkName(null);
        } else {
            return  name;
        }
    }

    private PlayerClass pickClass() {
        UserChoice<PlayerClass> playerClassUserChoice = new UserChoice<>(scanner, logger, "Choose your class:",
                classList.stream().map(playerClass -> new ChoiceOption<>("""
                        %1$s
                        Description: %2$s"""
                        .formatted(playerClass.getName(), playerClass.getDescription()), () -> playerClass)).toList());
        PlayerClass playerClass = playerClassUserChoice.getChoice().outcome().get();
        if(playerClass == classList.get(1) && !this.canChooseCoffee){
            this.canChooseCoffee = true;
            String message = "418 I'm a teapot - refused to brew coffee";
            int length = message.length();
            String border = "+" + "-".repeat(length + 2) + "+";

            logger.writeln(border);
            logger.writeln("| " + message + " |");
            logger.writeln(border);
            return pickClass();
        }
        return playerClass;
    }

    private void displayCharacter(Player player){
        logger.writeln(player.toString());
    }


}

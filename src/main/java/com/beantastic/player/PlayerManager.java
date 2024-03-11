package com.beantastic.player;

import java.util.List;
import java.util.Scanner;

import com.beantastic.logging.Logger;

public class PlayerManager {
    private final Logger logger;
    private final List<PlayerClass> classList;
    private final Scanner scanner;

    public static Player getPlayer() { // TODO remove dummy once static migration is done
        return null;
    }

    public PlayerManager (Logger logger, Scanner scanner, List<PlayerClass> classList) {
        this.logger = logger;
        this.scanner = scanner;
        this.classList = classList;
    }

    public Player createPlayer(){
        logger.writeln("\nCreate your bean: \n\n");
        PlayerMaker playerMaker = new PlayerMaker();
        Player player = playerMaker.setName(checkName(null))
                .setPlayerClass(pickClass())
                .createPlayer();
        displayCharacter(player);
        return player;
    }

    private String checkName(String name){
        if (name == null || name.isEmpty()){
            logger.writeln("Please enter a name \n");
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

    private void displayEachClassOption() {
        final int[] index = {1}; // Lambda external reference jank
        logger.writeln("Pick your class \n");
        classList.stream()
                 .map((PlayerClass playerClass) -> """
                         %s.
                         Name: %s
                         Description: %s
                         -------------------------------------------""".formatted(index[0]++,playerClass.getName(), playerClass.getDescription()) )
                 .forEach(logger::println);
    }

    private PlayerClass pickClass(){
        displayEachClassOption();
        logger.writeln("\n\nPick a class");
        String playerInpuString = scanner.nextLine().toLowerCase();
        if(playerInpuString.equals("1") || playerInpuString.equals("one")
            || playerInpuString.equals(classList.get(0).getName().toLowerCase())){
            return classList.getFirst();
        }else if(playerInpuString.equals("2") || playerInpuString.equals("two")
            || playerInpuString.equals(classList.get(1).getName().toLowerCase())){
            return classList.get(1);
        } else if (playerInpuString.equals("3") || playerInpuString.equals("three")
            || playerInpuString.equals(classList.get(2).getName().toLowerCase())){
            return classList.get(2);
        } else if (playerInpuString.equals("4") || playerInpuString.equals("four")
            || playerInpuString.equals(classList.get(3).getName().toLowerCase())){
            return classList.get(2);
        }else if (playerInpuString.equals("5") || playerInpuString.equals("five")
            || playerInpuString.equals(classList.get(4).getName().toLowerCase())){
            return classList.get(2);
        }else{
                logger.writeln("That's not a valid class");
                return pickClass();
        }
    }

    private void displayCharacter(Player player){
        logger.writeln(player.toString());
    }


}

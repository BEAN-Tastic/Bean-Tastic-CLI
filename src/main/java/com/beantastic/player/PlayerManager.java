package com.beantastic.player;

import java.util.List;
import java.util.Scanner;

import com.beantastic.Main;

public class PlayerManager {
    private final List<PlayerClass> classList;
    private final Scanner scanner;

    public static Player getPlayer() { // TODO remove dummy once static migration is done
        return null;
    }

    public PlayerManager (Scanner scanner, List<PlayerClass> classList) {
        this.scanner = scanner;
        this.classList = classList;
    }

    public Player createPlayer(){
        Main.typewriter("\nCreate your bean: \n\n");
        PlayerMaker playerMaker = new PlayerMaker();
        Player player = playerMaker.setName(checkName(null))
                .setPlayerClass(pickClass())
                .createPlayer();
        displayCharacter(player);
        return player;
    }

    private String checkName(String name){
        if (name == null || name.isEmpty()){
            Main.typewriter("Please enter a name \n");
            String playerName = scanner.nextLine();
            return checkName(playerName);
        }
        if(name.length() > 10){
            Main.typewriter("Name is too long!");
            return checkName(null);
        } else {
            return  name;
        }
    }

    private void displayEachClassOption() {
        Main.typewriter("Pick your class \n");
        classList.stream()
                 .map((PlayerClass playerClass) -> "Name: " + playerClass.getName() + "\nDescription: " + playerClass.getDescription() + "\n-------------------------------------------" )
                 .forEach(System.out::println);
    }

    private PlayerClass pickClass(){
        displayEachClassOption();
        Main.typewriter("\n\nPick a class");
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
                Main.typewriter("That's not a valid class");
                return pickClass();
        }
    }

    private static void displayCharacter(Player player){
        Main.typewriter(player.toString());
    }


}

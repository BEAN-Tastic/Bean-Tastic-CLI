package com.beantastic.player;

import java.util.List;
import java.util.Scanner;

import com.beantastic.Main;

public class PlayerManager {
    private static Player player;

    public static Player getPlayer(){
        return player;
    }

    public static void resetPlayer(){
        player = null; 
    }

    public static void createPlayer(Scanner scanner, List<PlayerClass> classList){
        Main.typewriter("\nCreate your bean: \n\n");
        player = new Player();
        Main.typewriter("Please enter a name \n");
        String playerName = scanner.nextLine();
        checkName(playerName, scanner);
        
        displayEachClassOption(PlayerClassManager.getClassList());
        Main.typewriter("\n\nPick a class");
        pickClass(scanner, classList);

        displayCharacter();
        
    }

    public static void checkName(String name, Scanner scanner){
        if(name.length() > 10){
            Main.typewriter("Name is too long!");
            String playerName = scanner.nextLine();
            checkName(playerName, scanner);
        }else{
            player.setName(name);
        }
    }

    public static void displayEachClassOption(List<PlayerClass> classList) {
        Main.typewriter("Pick your class \n");
        classList.stream()
                 .map((PlayerClass playerClass) -> "Name: " + playerClass.getname() + "\nDescription: " + playerClass.getDiscription() + "\n-------------------------------------------" )
                 .forEach(System.out::println);
    }
    
    public static void pickClass(Scanner scanner, List<PlayerClass> classList){
        String playerInpuString = scanner.nextLine().toLowerCase();
        if(playerInpuString.equals("1") || playerInpuString.equals("one") 
            || playerInpuString.equals(PlayerClassManager.getClassList().get(0).getname().toLowerCase())){

            PlayerManager.getPlayer().setPlayer(classList.get(0).getname(), 
                                                classList.get(0).getHealth(), 
                                                classList.get(0).getDefense(), 
                                                classList.get(0).getDamage(), 
                                                classList.get(0).getRizz());

        }else if(playerInpuString.equals("2") || playerInpuString.equals("two") 
            || playerInpuString.equals(PlayerClassManager.getClassList().get(1).getname().toLowerCase())){

            PlayerManager.getPlayer().setPlayer(classList.get(1).getname(), 
                                                classList.get(1).getHealth(), 
                                                classList.get(1).getDefense(), 
                                                classList.get(1).getDamage(), 
                                                classList.get(1).getRizz());

        } else if (playerInpuString.equals("3") || playerInpuString.equals("three") 
            || playerInpuString.equals(PlayerClassManager.getClassList().get(2).getname().toLowerCase())){
            
            PlayerManager.getPlayer().setPlayer(classList.get(2).getname(), 
                                                classList.get(2).getHealth(), 
                                                classList.get(2).getDefense(), 
                                                classList.get(2).getDamage(), 
                                                classList.get(2).getRizz());

        } else if (playerInpuString.equals("4") || playerInpuString.equals("four") 
            || playerInpuString.equals(PlayerClassManager.getClassList().get(3).getname().toLowerCase())){
            
            PlayerManager.getPlayer().setPlayer(classList.get(2).getname(), 
                                                classList.get(2).getHealth(), 
                                                classList.get(2).getDefense(), 
                                                classList.get(2).getDamage(), 
                                                classList.get(2).getRizz());

        }else if (playerInpuString.equals("5") || playerInpuString.equals("five") 
            || playerInpuString.equals(PlayerClassManager.getClassList().get(4).getname().toLowerCase())){
            
            PlayerManager.getPlayer().setPlayer(classList.get(2).getname(), 
                                                classList.get(2).getHealth(), 
                                                classList.get(2).getDefense(), 
                                                classList.get(2).getDamage(), 
                                                classList.get(2).getRizz());

        }else{
                Main.typewriter("Please pick a valid class");
                pickClass(scanner, classList);
        }
    }

    public static void displayCharacter(){
        Main.typewriter("\nName: " + player.getName() + "\n\n" + 
            "Class: " + player.getPlayerClass() + "\n\n" + 
            "Health: " + player.getHealth() + "\n\n" + 
            "Defense: " + player.getDefense() + "\n\n" + 
            "Rizz: " + player.getRizz() + "\n---------------------------------------------------\n\n");
    }


}

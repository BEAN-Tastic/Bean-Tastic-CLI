package com.beantastic.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.beantastic.Main;
import com.beantastic.player.PlayerManager;

public class ItemManager {
    private static List<ItemClass> commonItems = new ArrayList<>();
    private static List<ItemClass> uncommonItems = new ArrayList<>();
    private static List<ItemClass> rareItems = new ArrayList<>();
    private static List<ItemClass> epicItems = new ArrayList<>();

    static Random random = new Random();

    public static void addItemToList(ItemClass item){
        if(item.getRarity().equals("Common")){
            commonItems.add(item);
        }else if(item.getRarity().equals("Uncommon")){
            uncommonItems.add(item);
        }else if(item.getRarity().equals("Rare")){
            rareItems.add(item);
        }else if(item.getRarity().equals("Epic")){
            epicItems.add(item);
        }
        
    }

    public static void calculateDropChance(int dropChance, Scanner scanner){
        int randomNumber = random.nextInt(101); 
        if(randomNumber <= dropChance) {
            pickItem(scanner);
            
        }
    }

    public static void pickItem(Scanner scanner){
        int randomNum = random.nextInt(101);
            if(randomNum <= 50){
                dropItem(scanner, commonItems);
            }else if (randomNum <= 75 && randomNum >= 51){
                dropItem(scanner, uncommonItems);
            }else if(randomNum <= 90 && randomNum >= 76){
                dropItem(scanner, rareItems);
            } else if(randomNum <= 100 && randomNum >= 91){
                dropItem(scanner, epicItems);
            }
    }

    public static ItemClass pickRandomItem(List<ItemClass> items){
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty");
        }
        
        int randomIndex = random.nextInt(items.size());
        return items.get(randomIndex);
    }

    public static void dropItem(Scanner scanner, List<ItemClass> items){
        ItemClass droppedItem = pickRandomItem(items);
        Main.typewriter("Item: " + droppedItem.getName() + "\n"
            + "Description: " + droppedItem.getDescription() + "\n");
        pickUpItemOption(scanner, droppedItem);
        
    }

    public static void pickUpItemOption(Scanner scanner, ItemClass item){
        Main.typewriter("Pick up item? \n" + 
            "1. Yes \n" + 
            "2. No \n");
        String playerInpuString = scanner.nextLine().toLowerCase();
        droppedItemActionOptions(playerInpuString, scanner, item);
    }

    public static void droppedItemActionOptions(String option, Scanner scanner, ItemClass item){
        if(option.equals("1") || option.equals("one") || option.equals("yes")){
            item.pickUpItem(PlayerManager.getPlayer());
        }else if(option.equals("2") || option.equals("two") || option.equals("no")){
            Main.typewriter("Your loss \n");
        }else {
            Main.typewriter("Please input a valied option \n");
            String playerInpuString = scanner.nextLine().toLowerCase();
            droppedItemActionOptions(playerInpuString, scanner, item);
        }
    }
}

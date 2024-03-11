package com.beantastic.items;

import java.util.*;

import com.beantastic.logging.Logger;

public class ItemManager {
    private final List<ItemClass> commonItems;
    private final List<ItemClass> uncommonItems;
    private final List<ItemClass> rareItems;
    private final List<ItemClass> epicItems;

    private final Logger logger;

    private final Random random;

    private final Scanner scanner;

    public ItemManager (Logger logger, Random random, Scanner scanner, ItemClass... itemClasses) {
        this.logger = logger;
        this.random = random;
        this.scanner = scanner;
        this.commonItems = Arrays.stream(itemClasses).filter(itemClass -> itemClass.getRarity().equals("Common")).toList();
        this.uncommonItems = Arrays.stream(itemClasses).filter(itemClass -> itemClass.getRarity().equals("Uncommon")).toList();
        this.rareItems = Arrays.stream(itemClasses).filter(itemClass -> itemClass.getRarity().equals("Rare")).toList();
        this.epicItems = Arrays.stream(itemClasses).filter(itemClass -> itemClass.getRarity().equals("Epic")).toList();
    }

    public boolean calculateDropChance(int dropChance){
        int randomNumber = random.nextInt(101);
        return randomNumber <= dropChance;
    }

    public ItemClass pickItem(){
        int randomNum = random.nextInt(101);
            if(randomNum <= 50){
                return dropItem(commonItems);
            }else if (randomNum <= 75){
                return dropItem(uncommonItems);
            }else if(randomNum <= 90){
                return dropItem(rareItems);
            } else {
                return dropItem(epicItems);
            }
    }

    private ItemClass pickRandomItem(List<ItemClass> items){
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty");
        }

        int randomIndex = random.nextInt(items.size());
        return items.get(randomIndex);
    }

    private ItemClass dropItem(List<ItemClass> items){
        ItemClass droppedItem = pickRandomItem(items);
        logger.writeln("Item: " + droppedItem.getName() + "\n"
            + "Description: " + droppedItem.getDescription() + "\n");
        return droppedItem;
    }

    public ItemClass pickUpItemOption(ItemClass item){
        logger.writeln("""
                Pick up item?\s
                1. Yes\s
                2. No\s
                """);
        String playerInpuString = scanner.nextLine().toLowerCase();
        return droppedItemActionOptions(playerInpuString, item);
    }

    private ItemClass droppedItemActionOptions(String option, ItemClass item){
        if(option.equals("1") || option.equals("one") || option.equals("yes")){
            return item;
        }else if(option.equals("2") || option.equals("two") || option.equals("no")){
            return null;
        }else {
            logger.writeln("Please input a valid option \n");
            return pickUpItemOption(item);
        }
    }
}

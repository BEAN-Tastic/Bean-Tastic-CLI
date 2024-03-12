package com.beantastic.items;

import java.util.*;

import com.beantastic.logging.ChoiceOption;
import com.beantastic.logging.Logger;
import com.beantastic.logging.UserChoice;

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
        return pickUpItemOption(droppedItem);
    }

    private ItemClass pickUpItemOption(ItemClass item){
        UserChoice<ItemClass> itemChoice = new UserChoice<>(scanner, logger, """
                You find the following
                
                Item:\t%1$s
                Description:\t%2$s
                
                Pick up item?
                """.formatted(item.getName(), item.getDescription()),
                List.of(
                        new ChoiceOption<>("Yes", () -> item),
                        new ChoiceOption<>("No", () -> null)
                ));
        return itemChoice.getChoice().outcome().get();
    }
}

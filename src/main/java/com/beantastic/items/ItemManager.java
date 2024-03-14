package com.beantastic.items;

import java.util.*;
import java.util.stream.Collectors;

import com.beantastic.logging.ChoiceOption;
import com.beantastic.logging.Logger;
import com.beantastic.logging.UserChoice;

public class ItemManager {

    private final Map<String, List<ItemClass>> items;

    private final Logger logger;

    private final Random random;

    private final Scanner scanner;

    public ItemManager (Logger logger, Random random, Scanner scanner, ItemClass... itemClasses) {
        this.logger = logger;
        this.random = random;
        this.scanner = scanner;
        this.items = Arrays.stream(itemClasses)
                .collect(Collectors.groupingBy(ItemClass::getRarity));
    }

    public boolean calculateDropChance(int dropChance){
        int randomNumber = random.nextInt(101);
        return randomNumber <= dropChance;
    }

    public ItemClass pickItem(){
        int randomNum = random.nextInt(items.size());
        String rarity = items.keySet().stream().toList().get(randomNum);
        return dropItem(items.get(rarity));
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

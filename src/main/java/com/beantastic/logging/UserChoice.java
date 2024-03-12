package com.beantastic.logging;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserChoice<R> {

    private final Scanner scanner;
    private final Logger logger;

    private final String description;
    private final List<ChoiceOption<R>> choiceOptions;

    public UserChoice(Scanner scanner, Logger logger, String description, List<ChoiceOption<R>> choiceOptions) {
        this.scanner = scanner;
        this.logger = logger;
        this.description = description;
        this.choiceOptions = choiceOptions;
    }

    public ChoiceOption<R> getChoice() {
        logger.writeln(description);
        logger.printBreak();
        final int[] index = {1}; // Lambda external reference jank
        choiceOptions.stream()
                .map((ChoiceOption<R> choiceOption) -> "%1$s.\t%2$s"
                        .formatted(index[0]++, choiceOption.description())
                        .replace("\n", "\n\t"))
                .forEach(option -> {
                    logger.println(option);
                    logger.printBreak();
                });
        logger.println();
        String choice = scanner.nextLine().toLowerCase();
        logger.println();
        logger.printBreak();
        ChoiceOption<R> choiceOption = matchedOption(choice);
        if (choiceOption == null) {
            logger.writeln("Please enter a valid input");
            return getChoice();
        }
        return choiceOption;
    }

    private ChoiceOption<R> matchedOption(String choice) {
        final int[] index = {1}; // Lambda external reference jank
        return choiceOptions.stream().filter(choiceOption -> {
            try {
                return choiceOption.description().equalsIgnoreCase(choice) || stringToInteger(choice) == index[0]++;
            } catch (IllegalArgumentException e){
                return false;
            }
        }).findFirst().orElse(null);
    }

    private Integer stringToInteger(String choice) {
        Map<String, Integer> mappedNumbers = Map.of(
                "one", 1,
                "two", 2,
                "three", 3,
                "four", 4,
                "five", 5,
                "six", 6,
                "seven", 7,
                "eight", 8,
                "nine", 9,
                "ten", 10
        );
        if (mappedNumbers.containsKey(choice)) return mappedNumbers.get(choice);
        try {
            return Integer.parseInt(choice);
        } catch (NumberFormatException ignored){}
        throw new IllegalArgumentException("choice is not numeric");
    }
}

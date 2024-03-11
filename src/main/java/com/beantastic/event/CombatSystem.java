package com.beantastic.event;

import java.util.Random;
import java.util.Scanner;

import com.beantastic.logging.Logger;
import com.beantastic.Dialogue;
import com.beantastic.enemies.Enemy;
import com.beantastic.items.ItemClass;
import com.beantastic.items.ItemManager;
import com.beantastic.player.Player;

public class CombatSystem {

    private final Logger logger;

    private final Scanner scanner;

    private final Player player;

    private final Enemy enemy;

    private final ItemManager itemManager;

    private final Dialogue dialogue;
    public CombatSystem(Logger logger, Scanner scanner, Player player, Enemy enemy, ItemManager itemManager) {
        this.logger = logger;
        this.scanner = scanner;
        this.player = player;
        this.enemy = enemy;
        this.itemManager = itemManager;
        dialogue = new Dialogue(new Random(), player, enemy);
    }

    public ItemClass doCombatEvent(){
        logger.writeln("""
                A %1$s appears!
                %2$s
                """.formatted(enemy.getName(), enemy.getDescription()));
        return combatOptions();
    }

    private ItemClass combatOptions(){
        logger.writeln(Dialogue.combatInputDialogue()); //gives players their options: attack, defend or rizz
        String playerInpuString = scanner.nextLine().toLowerCase();
        return checkCombatInput(playerInpuString); //checks to see what the player has selected
    }

    private ItemClass checkCombatInput(String playerInputString) {
        return switch (playerInputString) {
            case "1", "one", "attack" -> attack();
            case "2", "two", "defend" -> defend();
            case "3", "three", "rizz" -> rizz();
            default -> {
                logger.writeln("Please input a valid option");
                yield combatOptions();
            }
        };
    }

    private ItemClass attack(){
        logger.writeln(dialogue.getAttackDialogue());
        enemy.takeDamage(player.damage());
        logger.writeln("You deal " + player.damage());

        if(enemy.isDead()){
            logger.writeln(dialogue.getEnemyDeathDialogue());
            return itemManager.calculateDropChance(100) ? itemManager.pickItem() : null;
        }

        //enemy attack
        logger.writeln(enemy.getName());
        logger.writeln("Health: " + enemy.health());
        logger.writeln("------------");
        player.takeDamage(enemy.damage());

        logger.writeln(enemy.getAttackDialogue());
        logger.writeln("Dealing: " + enemy.damage() + " damage");

        if(!player.isDead()) {
            logger.writeln("Your health: " + player.health());
            logger.writeln("------------");
            return combatOptions();
        }
        return null;

    }

    private ItemClass rizz(){

        logger.writeln(dialogue.getRizzDialogue());

        if(player.rizz() > enemy.rizz()){
            // you can rizz up the enemy
            logger.writeln("""
                Your rizz is so high, no one can resist you not even %1$s
                Some might even call you the Rizzard
                
                1. Rizz even more!
                2. Leave %1$s be""".formatted(enemy.getName()));
            return rizzOptions();
        }else{
            //you cant rizz up the enemy, and they do 1/2 damage
            logger.writeln("Your rizz was not high enough, " + enemy.getName() + " retaliates in disgust and deals " + enemy.damage()/ 2);
            player.takeDamage(enemy.damage()/ 2);
            return combatOptions();
        }
    }

    private ItemClass rizzOptions(){
        String option = scanner.nextLine().toLowerCase();

        if(option.equals("1") || option.equals("one") || option.equals("rizz again")){
            logger.writeln(dialogue.getRizzDialogue());
            logger.writeln("""
                There is something in the air... %1$s starts to look irresistible
                You might be the rizzard but they have captured your heart
                You never expected this turn of events, but you decide to embrace it.
                
                Days turn into weeks, and weeks into months. %1$s, and you have formed an unlikely bond.
                You spend your days exploring the kitchen floor, your abilities complementing each other perfectly.
                
                One day, as you sit under a potato peel scrap, %1$s turns to you and says,
                'My dear %2$s, I never thought I could love a bean. But you've stolen my heart.'
                You smile, knowing that your rizz worked better than you ever imagined.
                
                And so, you and %1$s live happily ever after, a bean and an enemy, bound by rizz.""".formatted(enemy.getName(), player.getName()));
            player.setIsRizzedUp(true);
            return null;
        } else if(option.equals("2") || option.equals("two") || option.equals("leave")){
            logger.writeln("""
                You feel a wave of kindness wash over you, and you allow the ugly beast to retreat %s scuttles away but leaves behind a gift
                """.formatted(enemy.getName()));
            return itemManager.pickItem();
        }else{
            logger.writeln("Please enter a valid input");
            return rizzOptions();
        }
    }

    private ItemClass defend(){

        if(player.defense() > enemy.damage()){
            //enemy does no damage you successfully blocked the attack
            logger.writeln("You successfully blocked " + enemy.getName() + "'s attack!");
            return combatOptions();
        }
        player.takeDamage(enemy.damage() - player.defense());
        if(!player.isDead()){
            logger.writeln("""
            You negated %1$s damage
            Your health: %2$s\s""".formatted((enemy.damage() - player.defense()), player.health()));
            return combatOptions();
        }
        return null;
    }
}

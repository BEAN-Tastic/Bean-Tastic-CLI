package com.beantastic.event;

import java.util.Random;
import java.util.Scanner;

import com.beantastic.Main;
import com.beantastic.Dialogue;
import com.beantastic.enemies.Enemy;
import com.beantastic.items.ItemClass;
import com.beantastic.items.ItemManager;
import com.beantastic.player.Player;

public class CombatSystem {

    private final Scanner scanner;

    private final Player player;

    private final Enemy enemy;

    private final ItemManager itemManager;

    private final Dialogue dialogue;
    public CombatSystem(Scanner scanner, Player player, Enemy enemy, ItemManager itemManager) {
        this.scanner = scanner;
        this.player = player;
        this.enemy = enemy;
        this.itemManager = itemManager;
        dialogue = new Dialogue(new Random(), player, enemy);
    }

    public ItemClass doCombatEvent(){
        Main.typewriter("A " + enemy.getName() + " appears! \n" + enemy.getDescription());
        return combatOptions();
    }

    private ItemClass combatOptions(){
        Main.typewriter(Dialogue.combatInputDialogue()); //gives players their options: attack, defend or rizz
        String playerInpuString = scanner.nextLine().toLowerCase();
        return checkCombatInput(playerInpuString); //checks to see what the player has selected
    }

    private ItemClass checkCombatInput(String playerInputString) {
        return switch (playerInputString) {
            case "1", "one", "attack" -> attack();
            case "2", "two", "defend" -> defend();
            case "3", "three", "rizz" -> rizz();
            default -> {
                Main.typewriter("Please input a valid option \n");
                yield combatOptions();
            }
        };
    }

    private ItemClass attack(){
        Main.typewriter(dialogue.getAttackDialogue());
        enemy.takeDamage(player.damage());
        Main.typewriter("\n\nYou deal " + player.damage());

        if(enemy.isDead()){
            Main.typewriter(dialogue.getEnemyDeathDialogue());
            return itemManager.calculateDropChance(100) ? itemManager.pickItem() : null;
        }

        //enemy attack
        Main.typewriter(enemy.getName() + "health: " + enemy.health() +"\n------------\n\n");
        player.takeDamage(enemy.damage());

        Main.typewriter(enemy.getAttackDialogue() + "\nDealing: " + enemy.damage() + " damage");

        if(!player.isDead()) {
            Main.typewriter("\nYour health: " + player.health() + "\n------------\n\n");
            return combatOptions();
        }
        return null;

    }

    private ItemClass rizz(){

        Main.typewriter(dialogue.getRizzDialogue());

        if(player.rizz() > enemy.rizz()){
            // you can rizz up the enemy
            Main.typewriter("Your rizz is so high, no one can resist you not even " + enemy.getName() + "\n"
                + "Some might even call you the Rizzard \n"
                + "\n1. Rizz again!"
                + "\n2. Leave " + enemy.getName() + " be");
            return rizzOptions();
        }else{
            //you cant rizz up the enemy, and they do 1/2 damage
            Main.typewriter("Your rizz was not enough, " + enemy.getName() + " retaliates in disgust and deals " + enemy.damage()/ 2);
            player.takeDamage(enemy.damage()/ 2);
            return combatOptions();
        }
    }

    private ItemClass rizzOptions(){
        String option = scanner.nextLine().toLowerCase();

        if(option.equals("1") || option.equals("one") || option.equals("rizz again")){
            Main.typewriter(dialogue.getRizzDialogue());
            Main.typewriter("\n\nThere is something in the air... " + enemy.getName() + " starts to look irresistible" +
                "\nYou might be the rizzard but they have captured your heart" +
                "\nYou never expected this turn of events, but you decide to embrace it." +
                "\n\nDays turn into weeks, and weeks into months. " + enemy.getName() + " and you, have formed an unlikely bond." +
                "\nYou spend your days exploring the kitchen floor, your abilities complementing each other perfectly." +
                "\n\nOne day, as you sit under a potato peel scrap, " + enemy.getName() + " turns to you and says," +
                "\n'My dear " + player.getName() + ", I never thought I could love a bean. But you've stolen my heart.'" +
                "\nYou smile, knowing that your rizz worked better than you ever imagined." +
                "\n\nAnd so, you and " + enemy.getName() + " live happily ever after, a bean and an enemy, bound by rizz.");
            player.setIsRizzedUp(true);
            return null;
        } else if(option.equals("2") || option.equals("two") || option.equals("leave")){
            Main.typewriter("\n\nYou feel a wave of kindness wash over you, and you allow the ugly beast to retreat" +
                enemy.getAttackDialogue() + " scuttles away but leaves behind a gift\n");
            return itemManager.pickItem();
        }else{
            Main.typewriter("Please enter a valid input");
            return rizzOptions();
        }
    }

    private ItemClass defend(){

        if(player.defense() > enemy.damage()){
            //enemy does no damage you successfully blocked the attack
            Main.typewriter("You successfully blocked " + enemy.getName() + "'s attack!");
            return combatOptions();
        }
        player.takeDamage(enemy.damage() - player.defense());
        if(!player.isDead()){
            //you negated x amount of damage
            Main.typewriter("You negated " + (enemy.damage() - player.defense()) + "damage \n"
            + "Your health: " + player.health());
            return combatOptions();
        }
        return null;
    }
}

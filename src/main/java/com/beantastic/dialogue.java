package com.beantastic;

import static java.lang.StringTemplate.STR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.beantastic.enemies.Enemy;
import com.beantastic.enemies.EnemyManager;
import com.beantastic.player.PlayerClassManager;
import com.beantastic.player.PlayerManager;

public class dialogue {
    static Random random = new Random();

    //COMBAT DIALOGUE 
    public static String combatInputDilogue(){
        return "What will you do? \n\n"+
        "1. Attack! \n\n"+ 
        "2. Defend! \n\n"+ 
        "3. Rizz!";

    }
    
    public static String getAttackDialogue() {
        int randomNumber = random.nextInt(3);
        String playerClassName = PlayerManager.getPlayer().getPlayerClass();

        switch (playerClassName) {
            case "Green Bean Warrior":
                return greenBeanWarriorAttackDialogue(randomNumber);
                
            case "Magic Coffee Bean":
                return magicCoffeeBeanAttackDialogue(randomNumber);
                
            case "Sneaky Black Bean":
                return sneakyBlackBeanAttackDialogue(randomNumber);
                
            case "Divine Lima Bean":
                return divineLimaBeanAttackDialogue(randomNumber);
                
            case "Arrowhead Pinto Bean":
                return arrowheadPintoBeanAttackDialogue(randomNumber);
                
            default:
                return "You attack!";
                
        }

    }

    public static String greenBeanWarriorAttackDialogue(int randomNum){
        String dialogue;
        switch (randomNum) {
            case 0:
                dialogue = "You swiftly swing your sword, cleaving through your enemy with brute force!";
                return dialogue;
            case 1:
                dialogue = "Your muscles bulge as you deliver a devastating blow, sending your foe staggering backward!";
                return dialogue;   
            case 2:
                dialogue = "You swing your weapon!";
                return dialogue;   
            default:
                dialogue = "You unleash a powerful strike!";
                return dialogue;
        }
    }

    public static String magicCoffeeBeanAttackDialogue(int randomNum){
        String dialogue;
        switch (randomNum) {
            case 0:
                dialogue = "You channel the mystical energies of beans, unleashing a devastating spell!";
                return dialogue;
            case 1:
                dialogue = "Arcane energies surge around you as you cast a spell";
                return dialogue;   
            case 2:
                dialogue = "A beam of pure magical energy shoots from your hand";
                return dialogue;   
            default:
                dialogue = "You unleash a powerful blast";
                return dialogue;
        }
    }

    public static String sneakyBlackBeanAttackDialogue(int randomNum){
        String dialogue;
        switch (randomNum) {
            case 0:
                dialogue = "You vanish into the shadows, reappearing behind your enemy and striking with deadly precision!";
                return dialogue;
            case 1:
                dialogue = "With a flick of your wrist, you throw a poisoned dagger";
                return dialogue;   
            case 2:
                dialogue = "You deliver a deadly backstab!";
                return dialogue;   
            default:
                dialogue = "You deliver a deadly jab!";
                return dialogue;
        }
    }

    public static String divineLimaBeanAttackDialogue(int randomNum){
        String dialogue;
        switch (randomNum) {
            case 0:
                dialogue = "You raise your holy symbol of the bean high, unleashing a wave of divine bean energy";
                return dialogue;
            case 1:
                dialogue = "You call upon the power of the beans, summoning a bean bolt";
                return dialogue;   
            case 2:
                dialogue = "You shoot a holy bean bolt";
                return dialogue;   
            default:
                dialogue = "You unless the power of the bean sending out a wave of bean energy";
                return dialogue;
        }
    }
    
    public static String arrowheadPintoBeanAttackDialogue(int randomNum){
        String dialogue;
        switch (randomNum) {
            case 0:
                dialogue = "You draw your bow with precision, releasing an arrow";
                return dialogue;
            case 1:
                dialogue = "You fire a volley of arrows";
                return dialogue;   
            case 2:
                dialogue = "You shoot an arrow from your bow";
                return dialogue;   
            default:
                dialogue = "You shoot an arrow from your bow";
                return dialogue;
        }
    }


    public static List<String> getEnemyDeathDialogue(){
        int randomNumber = random.nextInt(3);
        switch (randomNumber) {
            case 0:
                return List.of("You fiercely slay the " + EnemyManager.getCurrentEnemy().getName());
            case 1:
                return List.of("You brutally kill the " + EnemyManager.getCurrentEnemy().getName());
            case 2:
                return List.of("You ferociously kill the " + EnemyManager.getCurrentEnemy().getName());
            default:
                return List.of("You viciously kill the " + EnemyManager.getCurrentEnemy().getName());
        }
    }

    public static String getRizzDialgoue(){
        int randomNumber = random.nextInt(5);

        switch (randomNumber) {
            case 0:
                return "Are you a bean? Because you've bean on my mind all day.\n";
            case 1:
                return "Are you a coffee bean? Because you're brewing something strong in me.\n";
            case 2:
                return "Are you a refried bean? Because you're heating up my world\n";  
            case 3:
                return "I peed my pants, can I get in yours?\n";
            case 4:
                return "You look like trash! Let me take you out\n";
            default:
                return "Did you just fart? Because you blew me away!\n";
        }
    }
}

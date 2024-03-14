package com.beantastic;
import java.util.Random;

import com.beantastic.enemies.Enemy;
import com.beantastic.player.Player;

public class Dialogue {
    private final Random random;

    private final Player player;
    private final Enemy enemy;

    public Dialogue(Random random, Player player, Enemy enemy) {
        this.random = random;
        this.player = player;
        this.enemy = enemy;
    }

    public int getRandomNum(int max){
        int randomNumber = random.nextInt(max);
        return randomNumber;
    }

    public String getAttackDialogue() {
        int randomNumber = random.nextInt(3);
        String playerClassName = player.getPlayerClass().getName();
        return switch (playerClassName) {
            case "Green Bean Warrior" -> greenBeanWarriorAttackDialogue(randomNumber);
            case "Magic Coffee Bean" -> magicCoffeeBeanAttackDialogue(randomNumber);
            case "Sneaky Black Bean" -> sneakyBlackBeanAttackDialogue(randomNumber);
            case "Divine Lima Bean" -> divineLimaBeanAttackDialogue(randomNumber);
            case "Arrowhead Pinto Bean" -> arrowheadPintoBeanAttackDialogue(randomNumber);
            default -> "You attack!";
        };

    }

    public String greenBeanWarriorAttackDialogue(int randomNum){
        return switch (randomNum) {
            case 0 -> "You swiftly swing your sword, cleaving through your enemy with brute force!";
            case 1 -> "Your muscles bulge as you deliver a devastating blow, sending your foe staggering backward!";
            case 2 -> "You swing your weapon!";
            default -> "You unleash a powerful strike!";
        };
    }

    public String magicCoffeeBeanAttackDialogue(int randomNum){
        return switch (randomNum) {
            case 0 -> "You channel the mystical energies of beans, unleashing a devastating spell!";
            case 1 -> "Arcane energies surge around you as you cast a spell";
            case 2 -> "A beam of pure magical energy shoots from your hand";
            default -> "You unleash a powerful blast";
        };
    }

    public String sneakyBlackBeanAttackDialogue(int randomNum){
        return switch (randomNum) {
            case 0 -> "You vanish into the shadows, reappearing behind your enemy and striking with deadly precision!";
            case 1 -> "With a flick of your wrist, you throw a poisoned dagger";
            case 2 -> "You deliver a deadly back-stab!";
            default -> "You deliver a deadly jab!";
        };
    }

    public String divineLimaBeanAttackDialogue(int randomNum){
        return switch (randomNum) {
            case 0 -> "You raise your holy symbol of the bean high, unleashing a wave of divine bean energy";
            case 1 -> "You call upon the power of the beans, summoning a bean bolt";
            case 2 -> "You shoot a holy bean bolt";
            default -> "You unless the power of the bean sending out a wave of bean energy";
        };
    }

    public String arrowheadPintoBeanAttackDialogue(int randomNum){
        return switch (randomNum) {
            case 0 -> "You draw your bow with precision, releasing an arrow";
            case 1 -> "You fire a volley of arrows";
            default -> "You shoot an arrow from your bow";
        };
    }


    public String getEnemyDeathDialogue(){
        int randomNumber = random.nextInt(3);
        return switch (randomNumber) {
            case 0 -> "You fiercely slay the " + enemy.getName();
            case 1 -> "You brutally kill the " + enemy.getName();
            case 2 -> "You ferociously kill the " + enemy.getName();
            default -> "You viciously kill the " + enemy.getName();
        };
    }

    public String getRizzDialogue(){
        int randomNumber = random.nextInt(5);

        return switch (randomNumber) {
            case 0 -> "Are you a bean? Because you've bean on my mind all day.";
            case 1 -> "Are you a coffee bean? Because you're brewing something strong in me.";
            case 2 -> "Are you a refried bean? Because you're heating up my world";
            case 3 -> "I peed my pants, can I get in yours?";
            case 4 -> "You look like trash! Let me take you out";
            case 5 -> "Do you work at Subway? Cause you just gave me a foot-long.";
            case 6 -> "You slut drop holding eye contact";
            default -> "Did you just fart? Because you blew me away!";
        };
    }

    public String enemyIntroduction(int randomNum){
        return switch(randomNum) {
            case 0 -> "*rumble*... *rumble*... \n ";
            case 1 -> "As you tread carefully, a distant rumble breaks the silence, drawing nearer.";
            default -> "You hear something behind you....";
        };
    }
}

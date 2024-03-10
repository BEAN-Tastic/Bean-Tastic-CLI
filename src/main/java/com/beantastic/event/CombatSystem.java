package com.beantastic.event;

import java.util.Scanner;

import com.beantastic.Main;
import com.beantastic.dialogue;
import com.beantastic.enemies.Enemy;
import com.beantastic.enemies.EnemyManager;
import com.beantastic.items.ItemManager;
import com.beantastic.player.PlayerManager;

public class CombatSystem {

    public static void startCombatEvent(Scanner scanner, int hardnessLevel){
        EnemyManager.setCurrentEnemy(hardnessLevel);
        Main.typewriter("A " + EnemyManager.getCurrentEnemy().getName() + " appears! \n" + EnemyManager.getCurrentEnemy().getDescription()); 
        combatOptions(scanner);
    }

    public static void combatOptions(Scanner scanner){
        Main.typewriter(dialogue.combatInputDilogue()); //gives players their options- attack, defend or rizz
        String playerInpuString = scanner.nextLine().toLowerCase();
        checkCombatInput(playerInpuString, scanner); //checks to see what the player has selected 
    }

    public static void checkCombatInput(String playerInputString, Scanner scanner) {
        if (playerInputString.equals("1") || playerInputString.equals("one") || playerInputString.equals("attack") ) {
            attack(scanner);
        } else if (playerInputString.equals("2") || playerInputString.equals("two") || playerInputString.equals("defend")) {
            defend(scanner);
        } else if (playerInputString.equals("3") || playerInputString.equals("three") || playerInputString.equals("rizz")){
            rizz(scanner);
        }else {
            Main.typewriter("Please input a valid option \n");
            String newInputString = scanner.nextLine().toLowerCase();
            checkCombatInput(newInputString, scanner);
        }
    }

    public static void attack(Scanner scanner){
        Enemy enemy =  EnemyManager.getCurrentEnemy();
        Main.typewriter(dialogue.getAttackDialogue());
        enemy.takeDamage(PlayerManager.getPlayer().getDamage());
        Main.typewriter("\n\nYou deal " + PlayerManager.getPlayer().getDamage());
       
        if(enemy.getHealth() <= 0){
            //enemy die 
            enemy.die();
            ItemManager.calculateDropChance(25, scanner);
            EnemyManager.setCurrentEnemeyToNull();
        }else{
            //enemy attack 
            Main.typewriter(EnemyManager.getCurrentEnemy().getName() + "health: " + EnemyManager.getCurrentEnemy().getHealth() +"\n------------\n\n");
            PlayerManager.getPlayer().takeDamage(EnemyManager.getCurrentEnemy().getDamange());

            Main.typewriter(enemy.getAttackDialgoue() + "\nDealing: " + enemy.getDamange() + " damage");

            if(PlayerManager.getPlayer().getHealth() <= 0){
                PlayerManager.getPlayer().die();
            }else{
                Main.typewriter("\nYour health: " + PlayerManager.getPlayer().getHealth() + "\n------------\n\n");
                combatOptions(scanner);
            }
        }

        

        
    }

    public static void rizz(Scanner scanner){
        Enemy enemy =  EnemyManager.getCurrentEnemy();

        Main.typewriter(dialogue.getRizzDialgoue());

        if(PlayerManager.getPlayer().getRizz() > enemy.getRizz()){
            // you can rizz up the enemy 
            Main.typewriter("Your rizz is so high, no one can resist you not even " + enemy.getName() + "\n"
                + "Some might even call you the Rizzard \n"
                + "\n1. Rizz again!" 
                + "\n2. Leave " + EnemyManager.getCurrentEnemy().getName() + " be");
            
            //item logic here
        }else{
            //you cant rizz up the enemy and they do 1/2 dmaage 
            Main.typewriter("Your rizz was not enough" + enemy.getName() + " attacks and deals " + enemy.getDamange()/ 2);
            PlayerManager.getPlayer().takeDamage(enemy.getDamange()/ 2);
            if(PlayerManager.getPlayer().getHealth() <= 0){
                PlayerManager.getPlayer().die();
            }else{
                //you negated x amount of damage
                Main.typewriter("You live to rizz another day!");
                combatOptions(scanner);
            }
        }

        
    }

    public void rizzOptions(Scanner scanner){
        String option = scanner.nextLine().toLowerCase();

        if(option.equals("1") || option.equals("one") || option.equals("rizz again")){
            Main.typewriter(dialogue.getRizzDialgoue());
            Main.typewriter("\n\nThere is something in the air... " + EnemyManager.getCurrentEnemy().getName() + " starts to look irresistable" +
                "\nYou might be the rizzard but they have captured your heart" +
                "\nYou never expected this turn of events, but you decide to embrace it." +
                "\n\nDays turn into weeks, and weeks into months. " + EnemyManager.getCurrentEnemy().getName() + " and you, have formed an unlikely bond." +
                "\nYou spend your days exploring the kitchen floor, your abilities complementing each other perfectly." +
                "\n\nOne day, as you sit under a potato peel scrap, " + EnemyManager.getCurrentEnemy().getName() + " turns to you and says," +
                "\n'My dear " + PlayerManager.getPlayer().getName() + ", I never thought I could love a bean. But you've stolen my heart.'" +
                "\nYou smile, knowing that your rizz worked better than you ever imagined." +
                "\n\nAnd so, you and " + EnemyManager.getCurrentEnemy().getName() + " live happily ever after, a bean and an enemy, bound by rizz.");
            PlayerManager.getPlayer().setIsRizzedUp(true);
            Main.gameOver(true);

        } else if(option.equals("2") || option.equals("two") || option.equals("leave")){
            Main.typewriter("\n\nYou feel a wave of kindness wash over you, and you allow the ugly beast to retreat" + 
                EnemyManager.getCurrentEnemy().getAttackDialgoue() + " scuttles away but leaves behing a gift\n");
            ItemManager.pickItem(scanner);
        }else{
            Main.typewriter("Please enter a valid input");
            rizzOptions(scanner);
        }
    }

    public static void defend(Scanner scanner){
        Enemy enemy =  EnemyManager.getCurrentEnemy();

        if(PlayerManager.getPlayer().getDefense() > enemy.getDamange()){
            //enemy does no damage you succefully blocked the attack
            Main.typewriter("You succesfully blocked " + enemy.getName() + "'s attack!");
            combatOptions(scanner);

        }else{
            PlayerManager.getPlayer().takeDamage(enemy.getDamange() - PlayerManager.getPlayer().getDefense());

            if(PlayerManager.getPlayer().getHealth() <= 0){
                PlayerManager.getPlayer().die();
            }else{
                //you negated x amount of damage
                Main.typewriter("You negated " + (enemy.getDamange() - PlayerManager.getPlayer().getDefense()) + "damage \n" 
                + "Your health: " + PlayerManager.getPlayer().getHealth());
                combatOptions(scanner);
            }
            
        }
    }

}

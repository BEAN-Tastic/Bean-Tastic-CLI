package com.beantastic.event;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.beantastic.logging.ChoiceOption;
import com.beantastic.logging.Logger;
import com.beantastic.Dialogue;
import com.beantastic.enemies.Enemy;
import com.beantastic.items.ItemClass;
import com.beantastic.items.ItemManager;
import com.beantastic.logging.UserChoice;
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
                %2$s""".formatted(enemy.getName(), enemy.getDescription()));
        logger.printBreak();
        return combatOptions();
    }

    private ItemClass combatOptions(){
        UserChoice<ItemClass> combatOptions = new UserChoice<>(scanner, logger, """
                %s
                
                What will you do?""".formatted(player),
                List.of(
                        new ChoiceOption<>("Attack", this::attack),
                        new ChoiceOption<>("Defend", this::defend),
                        new ChoiceOption<>("Rizz", this::rizz)
                ));
        return combatOptions.getChoice().outcome().get();
    }

    private ItemClass attack(){
        logger.writeln(dialogue.getAttackDialogue());
        enemy.takeDamage(player.damage());
        logger.writeln("You deal " + player.damage() + " damage");

        if(enemy.isDead()){
            logger.writeln(dialogue.getEnemyDeathDialogue());
            logger.printBreak();
            return itemManager.calculateDropChance(100) ? itemManager.pickItem() : null;
        }

        //enemy attack
        logger.printBreak();
        logger.writeln(enemy.getName());
        logger.writeln(enemy.getAttackDialogue());
        logger.writeln("You take " + enemy.damage() + " damage");
        player.takeDamage(enemy.damage());

        if(player.isDead()) {
            return null;
        }
        logger.printBreak();
        return combatOptions();

    }

    private ItemClass rizz(){
        logger.writeln("%s:\t%s".formatted(player.getName(), dialogue.getRizzDialogue()));
        if(player.rizz() > enemy.rizz()){
            return rizzOptions();
        }else{
            //you cant rizz up the enemy, and they do 1/2 damage
            logger.writeln("Your rizz was not high enough, " + enemy.getName() + " retaliates in disgust and deals " + enemy.damage()/ 2);
            player.takeDamage(enemy.damage()/ 2);
            logger.printBreak();
            if(player.isDead()) {
                return null;
            }
            return combatOptions();
        }
    }

    private ItemClass rizzOptions(){
        UserChoice<ItemClass> rizzOptions = new UserChoice<>(scanner, logger,
                """
                Your rizz is so high, no one can resist you not even %1$s
                Some might even call you the Rizzard
                """.formatted(enemy.getName()),
                List.of(
                        new ChoiceOption<>("Rizz even more!", this::rizzMore),
                        new ChoiceOption<>("Leave %1$s be".formatted(enemy.getName()), this::rizzAndRun)
                )
        );
        return rizzOptions.getChoice().outcome().get();
    }

    private ItemClass rizzAndRun () {
        logger.writeln("""
            You feel a wave of kindness wash over you, and you allow the ugly beast to retreat.
            %s scuttles away but leaves behind a gift
            """
                .formatted(enemy.getName()));
        return itemManager.pickItem();

    }
    private ItemClass rizzMore () {
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
            
            And so, you and %1$s live happily ever after, a bean and an enemy, bound by rizz."""
                .formatted(enemy.getName(), player.getName()));
        player.setIsRizzedUp(true);
        return null;
    }

    private ItemClass defend(){
        if(player.defense() > enemy.damage()){
            //enemy does no damage you successfully blocked the attack
            logger.writeln("You successfully block " + enemy.getName() + "'s attack!");
            logger.printBreak();
            return combatOptions();
        }
        player.takeDamage(enemy.damage() - player.defense());
        if(!player.isDead()){
            logger.writeln("You negated %s damage".formatted((enemy.damage() - player.defense())));
            logger.printBreak();
            return combatOptions();
        }
        return null;
    }
}

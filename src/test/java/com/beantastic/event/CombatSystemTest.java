package com.beantastic.event;

import com.beantastic.enemies.Enemy;
import com.beantastic.items.ItemClass;
import com.beantastic.items.ItemManager;
import com.beantastic.logging.Logger;
import com.beantastic.player.Player;
import com.beantastic.player.PlayerClass;
import com.beantastic.stats.StatBlock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

public class CombatSystemTest {

    private final static StatBlock OVER_POWERED = new StatBlock(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    private final static StatBlock WEAK = new StatBlock(1, 0, 0, 0);
    private final static Logger logger = new DummyLogger();
    private final static ItemManager itemManager = new DummyItemManager();

    private static class DummyLogger extends Logger {

        public DummyLogger() {
            super(null);
        }

        @Override
        public void printBreak() {
        }

        @Override
        public void println() {
        }

        @Override
        public void println(Object object) {
        }

        @Override
        public void print(Object object) {
        }

        @Override
        public void write(String text) {}

        @Override
        public void writeln(String text) {}
    }



    private static class DummyItemManager extends ItemManager {

        public static ItemClass dummyItem = new ItemClass("test item", "test item description", new StatBlock(0,0,0,0),"test");

        public DummyItemManager() {
            super(null, null, null);
        }

        @Override
        public boolean calculateDropChance(int dropChance) {
            return true;
        }

        @Override
        public ItemClass pickItem() {
            return dummyItem;
        }
    }

    @Test
    public void testCombatAttackWin() {
        Scanner input = new Scanner("1\n"); // Attack
        Player player = new Player("test", new PlayerClass("test class", "a test class", OVER_POWERED));
        Enemy enemy = new Enemy("test enemy", WEAK,"test difficulty","test enemy attack","test enemy description");
        CombatSystem combatSystem = new CombatSystem(logger, input, player, enemy, itemManager);
        ItemClass item = combatSystem.doCombatEvent();
        Assertions.assertEquals(DummyItemManager.dummyItem, item, "winner gets an item");
        Assertions.assertTrue(enemy.isDead(), "Weak enemy should lose");
        Assertions.assertFalse(player.isDead(), "Over powered player should win");
    }

    @Test
    public void testCombatAttackLose() {
        Scanner input = new Scanner("1\n"); // Attack
        Player player = new Player("test", new PlayerClass("test class", "a test class", WEAK));
        Enemy enemy = new Enemy("test enemy", OVER_POWERED,"test difficulty","test enemy attack","test enemy decription");
        CombatSystem combatSystem = new CombatSystem(logger, input, player, enemy, itemManager);
        ItemClass item = combatSystem.doCombatEvent();
        Assertions.assertNull(item, "No Item for lost combat");
        Assertions.assertFalse(enemy.isDead(), "Over powered enemy should win");
        Assertions.assertTrue(player.isDead(), "Weak player should lose");
    }

    @Test
    public void testCombatDefendLose() {
        Scanner input = new Scanner("2\n"); // Defend
        Player player = new Player("test", new PlayerClass("test class", "a test class", WEAK));
        Enemy enemy = new Enemy("test enemy", OVER_POWERED,"test difficulty","test enemy attack","test enemy decription");
        CombatSystem combatSystem = new CombatSystem(logger, input, player, enemy, itemManager);
        ItemClass item = combatSystem.doCombatEvent();
        Assertions.assertNull(item, "No Item for lost combat");
        Assertions.assertFalse(enemy.isDead(), "Over powered enemy should win");
        Assertions.assertTrue(player.isDead(), "Weak player should lose");
    }

    @Test
    public void testCombatDefendWin() {
        Scanner input = new Scanner("2\n1\n"); // Defend, Attack
        Player player = new Player("test", new PlayerClass("test class", "a test class", OVER_POWERED));
        Enemy enemy = new Enemy("test enemy", WEAK,"test difficulty","test enemy attack","test enemy description");
        CombatSystem combatSystem = new CombatSystem(logger, input, player, enemy, itemManager);
        ItemClass item = combatSystem.doCombatEvent();
        Assertions.assertEquals(DummyItemManager.dummyItem, item, "winner gets an item");
        Assertions.assertTrue(enemy.isDead(), "Weak enemy should lose");
        Assertions.assertFalse(player.isDead(), "Over powered player should win");
    }

    @Test
    public void testCombatDoubleRizzWin() {
        Scanner input = new Scanner("3\n1\n"); // Rizz, Rizz further
        Player player = new Player("test", new PlayerClass("test class", "a test class", OVER_POWERED));
        Enemy enemy = new Enemy("test enemy", WEAK,"test difficulty","test enemy attack","test enemy description");
        CombatSystem combatSystem = new CombatSystem(logger, input, player, enemy, itemManager);
        ItemClass item = combatSystem.doCombatEvent();
        Assertions.assertNull(item, "No item needed as game ends");
        Assertions.assertFalse(enemy.isDead(), "Enemy does not die in a rizz ending");
        Assertions.assertFalse(player.isDead(), "Player should be alive after rizzing successfully");
        Assertions.assertTrue(player.isRizzedUp(), "Enemy should rizz up the the player if they stay");
    }

    @Test
    public void testCombatRizzAndRunWin() {
        Scanner input = new Scanner("3\n2\n"); // Rizz, Leave them be
        Player player = new Player("test", new PlayerClass("test class", "a test class", OVER_POWERED));
        Enemy enemy = new Enemy("test enemy", WEAK,"test difficulty","test enemy attack","test enemy description");
        CombatSystem combatSystem = new CombatSystem(logger, input, player, enemy, itemManager);
        ItemClass item = combatSystem.doCombatEvent();
        Assertions.assertEquals(DummyItemManager.dummyItem, item, "Player should get a gift");
        Assertions.assertFalse(enemy.isDead(), "Enemy does not die in a rizz ending");
        Assertions.assertFalse(player.isDead(), "Player should be alive after rizzing successfully");
        Assertions.assertFalse(player.isRizzedUp(), "Player should not get rizzed up if they leave");
    }

    @Test
    public void testCombatRizzLose() {
        Scanner input = new Scanner("3\n"); // Rizz, Leave them be
        Player player = new Player("test", new PlayerClass("test class", "a test class", WEAK));
        Enemy enemy = new Enemy("test enemy", OVER_POWERED,"test difficulty","test enemy attack","test enemy description");
        CombatSystem combatSystem = new CombatSystem(logger, input, player, enemy, itemManager);
        ItemClass item = combatSystem.doCombatEvent();
        Assertions.assertNull(item, "Player should get nothing when they lose");
        Assertions.assertFalse(enemy.isDead(), "Enemy should win");
        Assertions.assertTrue(player.isDead(), "Player should lose");
    }
}

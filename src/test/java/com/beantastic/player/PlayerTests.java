package com.beantastic.player;

import com.beantastic.items.ItemClass;
import com.beantastic.stats.StatBlock;
import org.junit.jupiter.api.*;

public class PlayerTests {

    private Player player;

    @BeforeEach
    public void createPlayer (){
        this.player = new Player("test",
                new PlayerClass("test", "a test class",
                        new StatBlock(1,1,1,1)));
    }

    @Test
    public void testDieKillsPlayer () {
        Assertions.assertFalse(player.isDead(),"New Player should be alive");
        player.die();
        Assertions.assertTrue(player.isDead(), "Player should be dead after dying");
    }

    @Test
    public void testDoubleDeath () {
        player.die();
        player.die();
        Assertions.assertTrue(player.isDead(), "Dying twice does not make you alive");
    }

    @Test
    public void testCannotHealMoreThanMax() {
        int startHealth = player.health();
        player.takeDamage(-100);
        Assertions.assertEquals(startHealth, player.health());
    }

    @Test
    public void testCanHealDamage() {
        int startHealth = player.health();
        int damage = 100;
        player.takeDamage(damage);
        Assertions.assertTrue(startHealth > player.health());
        player.takeDamage(-damage);
        Assertions.assertEquals(startHealth, player.health());
    }

    @Test
    public void testCannotHaveNegativeHealth() {
        player.die();
        Assertions.assertEquals(0, player.health());
        player.takeDamage(100);
        Assertions.assertEquals(0, player.health());
    }

    @Test
    public void testItemsAffectStats () {
        int initialDamage = player.damage();
        int initialDefense = player.defense();
        int initialRizz = player.rizz();

        player.addItem(new ItemClass("test item", " a test item", new StatBlock(0, -1, 1, 0), "test"));
        Assertions.assertTrue(initialDamage < player.damage());
        Assertions.assertTrue(initialDefense > player.defense());
        Assertions.assertEquals(initialRizz, player.rizz());

    }
}

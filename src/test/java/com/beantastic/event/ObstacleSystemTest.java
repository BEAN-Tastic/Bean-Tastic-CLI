package com.beantastic.event;

import com.beantastic.player.Player;
import com.beantastic.player.PlayerClass;
import com.beantastic.stats.StatBlock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ObstacleSystemTest {

    private static class DummyRandom extends Random {
        @Override
        public int nextInt(int bound) {
            return 0;
        }
    }

    @Test
    public void testRunObstacleCanEffectPlayer () {
        int initialStat = 1;
        ObstacleSystem obstacleSystem = new ObstacleSystem(new DummyRandom(), Player::die);
        Player testPlayer = new Player("test", new PlayerClass("test", "test class", new StatBlock(initialStat, initialStat, initialStat, initialStat)));

        Assertions.assertFalse(testPlayer.isDead());
        obstacleSystem.runObstacle(testPlayer);
        Assertions.assertTrue(testPlayer.isDead());
    }
}

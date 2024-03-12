package com.beantastic.event;

import java.util.List;
import java.util.Random;
import com.beantastic.player.Player;

public class ObstacleSystem {

    private final Random random;


    private final List<Obstacle> obstacles;

    public ObstacleSystem(Random random, Obstacle... obstacles) {
        this.random = random;
        this.obstacles = List.of(obstacles);
    }

    private Obstacle getRandomObstacle(){
        int randIndex = random.nextInt(obstacles.size());
        return obstacles.get(randIndex);
    }

    public void runObstacle(Player player){
        Obstacle obstacle = getRandomObstacle();
        obstacle.run(player);
    }
}

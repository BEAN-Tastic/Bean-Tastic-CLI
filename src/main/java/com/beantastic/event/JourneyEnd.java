package com.beantastic.event;

import com.beantastic.logging.Logger;
import com.beantastic.player.Player;

public class JourneyEnd implements Obstacle{
    private final Logger logger;

    public JourneyEnd(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void run(Player player) {
        logger.writeln("After your long treacherous journey, you finally see it... The light at the end of the tunnel" +
                "The way up and home, away from this dark place");
    }
}

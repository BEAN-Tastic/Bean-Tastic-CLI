package com.beantastic.player;

public class PlayerMaker {
    private String name;
    private PlayerClass playerClass;

    public PlayerMaker setName(String name) {
        this.name = name;
        return this;
    }

    public PlayerMaker setPlayerClass(PlayerClass playerClass) {
        this.playerClass = playerClass;
        return this;
    }

    public Player createPlayer () {
        return new Player(this.name, this.playerClass);
    }
}

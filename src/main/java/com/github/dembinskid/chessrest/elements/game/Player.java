package com.github.dembinskid.chessrest.elements.game;

import lombok.Data;

import java.util.*;

@Data
public class Player {
    private UUID playerId;
    private String name;
    private String emailAddress;
    private List<Game> playedGames; //must be list of game identificators to not to have all data about games
    private final Date playerCreated;

    public Player(String name, String emailAddress) {
        this.playerId = UUID.randomUUID();
        this.name = name;
        this.emailAddress = emailAddress;
        this.playedGames = new ArrayList<>();
        this.playerCreated = Calendar.getInstance().getTime();
    }
}

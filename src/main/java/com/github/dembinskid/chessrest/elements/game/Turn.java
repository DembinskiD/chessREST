package com.github.dembinskid.chessrest.elements.game;

import java.util.*;

public class Turn {
    UUID uuid;
    UUID gameId;
    Player player;
    List<Movement> listOfPossibleMoves;
    Movement moveTaken;
    Date startTime;
    Date endTime;

    public Turn(Player player, UUID gameId, List<Movement> listOfPossibleMoves) {
        this.uuid = UUID.randomUUID();
        this.gameId = gameId;
        this.player = player;
        this.listOfPossibleMoves = listOfPossibleMoves;
        startTime = Calendar.getInstance().getTime();
    }

    public void takeMove(Movement moveTaken) {
        this.moveTaken = moveTaken;
        this.endTime = Calendar.getInstance().getTime();
    }
}

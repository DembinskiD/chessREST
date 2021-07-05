package com.github.dembinskid.chessrest.elements.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Game {
    UUID id;
    String gameDescription;
    Player firstPlayer;
    Player secondPlayer;
    List<Turn> turnList; //player, list of possible moves, move taken,
    List<Movement> movementList; //list of moves done already
    List<Movement> possibleMoves; //list of possible moves in currentTurn
    Turn currentTurn;
    Date startTime;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.id = UUID.randomUUID();
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.turnList = new ArrayList<>();


    }


}

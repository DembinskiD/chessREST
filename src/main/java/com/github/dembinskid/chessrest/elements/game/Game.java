package com.github.dembinskid.chessrest.elements.game;

import com.github.dembinskid.chessrest.elements.Color;
import com.github.dembinskid.chessrest.elements.gameboard.Board;

import java.util.*;

public class Game {
    UUID id;
    String gameDescription;
    PlayerInGame firstPlayer;
    PlayerInGame secondPlayer;
    Board board;
    List<Turn> turnList; //player, list of possible moves, move taken,
    Turn currentTurn;
    Date startTime;
    Date endTime;

    public Game(PlayerInGame firstPlayer, PlayerInGame secondPlayer) {
        this.id = UUID.randomUUID();
        this.startTime = Calendar.getInstance().getTime();
        this.firstPlayer = firstPlayer;
        this.firstPlayer.setColor(Color.WHITE);
        this.firstPlayer.setGameId(this.id);
        this.secondPlayer = secondPlayer;
        this.secondPlayer.setColor(Color.BLACK);
        this.secondPlayer.setGameId(this.id);
        this.board = new Board();
        this.turnList = new ArrayList<>();
        this.currentTurn = new Turn(firstPlayer, this.id, this.board);


    }

    List<Movement> getPossibleMoves() {
        return new ArrayList<>();
    }


}

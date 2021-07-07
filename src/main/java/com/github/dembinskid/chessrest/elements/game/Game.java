package com.github.dembinskid.chessrest.elements.game;

import com.github.dembinskid.chessrest.elements.Color;
import com.github.dembinskid.chessrest.elements.gameboard.Board;
import lombok.Data;

import java.util.*;

@Data
public class Game {
    private UUID id;
    private String gameDescription;
    private PlayerInGame firstPlayer;
    private PlayerInGame secondPlayer;
    private Board board;
    private List<Turn> turnList;
    private Turn currentTurn;
    private Date startTime;
    private Date endTime;

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
        this.currentTurn = new Turn(1, firstPlayer, this.id, this.board);
    }

    public Game(PlayerInGame firstPlayer, PlayerInGame secondPlayer, String gameDescription) {
        this(firstPlayer, secondPlayer);
        this.gameDescription = gameDescription;
    }

    public void endTurn(Movement moveTaken) {
        int turnNumber = this.currentTurn.getTurnNumber();
        Color newTurnColor = turnNumber%2 == 1 ? Color.BLACK : Color.WHITE;
        turnNumber++;
        this.turnList.add(this.currentTurn.takeMove(moveTaken));
        this.currentTurn = new Turn(turnNumber, newTurnColor.equals(Color.WHITE) ? firstPlayer : secondPlayer, this.id, this.board);
    }

    public List<Movement> getPossibleMoves() {
        return this.currentTurn.getListOfPossibleMoves();
    }

    public void finishGame() {
        this.endTime = Calendar.getInstance().getTime();
    }


}

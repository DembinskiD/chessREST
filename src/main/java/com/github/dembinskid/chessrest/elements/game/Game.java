package com.github.dembinskid.chessrest.elements.game;

import java.util.List;

public class Game {
    Player firstPlayer;
    Player secondPlayer;
    List<Turn> turnList;
    List<Movement> movementList; //list of moves done already
    List<Movement> possibleMoves; //list of possible moves in currentTurn
    Turn currentTurn;
    long startTime; //posix time




}

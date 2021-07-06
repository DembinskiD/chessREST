package com.github.dembinskid.chessrest.elements.game;

import com.github.dembinskid.chessrest.elements.gameboard.Board;
import com.github.dembinskid.chessrest.elements.gameboard.Field;
import lombok.Data;

import java.util.*;

@Data
public class Turn {
    UUID uuid;
    UUID gameId;
    PlayerInGame player;
    List<Movement> listOfPossibleMoves;
    Movement moveTaken;
    Date startTime;
    Date endTime;

    public Turn(PlayerInGame player, UUID gameId, Board board) {
        this.uuid = UUID.randomUUID();
        this.gameId = gameId;
        this.player = player;
        this.listOfPossibleMoves = getPossibleMoves(player, board);
        startTime = Calendar.getInstance().getTime();
    }

    private List<Movement> getPossibleMoves(PlayerInGame player, Board board) {
        return board.getGameBoard()
                .stream()
                .filter(Field::isTaken)
                .filter(field -> field.getPiece().getColor().equals(player.getPlayerColor()))
                .map(field -> new Pair<Field, List<Field>>(field, field.getPiece().getPieceType().getPossibleMoves(board, field)))
                .reduce(new ArrayList<Movement>(), this::getMovementFromPair, this::mixTwoLists);
    }

    private ArrayList<Movement> mixTwoLists(ArrayList<Movement> oldList, ArrayList<Movement> newList) {
        ArrayList<Movement> outputList = new ArrayList<>(oldList);
        outputList.addAll(newList);
        return outputList;
    }

    private ArrayList<Movement> getMovementFromPair(ArrayList<Movement> oldList, Pair<Field, List<Field>> fieldListPair) {
        ArrayList<Movement> outList = new ArrayList<>(oldList);
        fieldListPair.value
                .stream()
                .map(val -> new Movement(gameId, fieldListPair.key, val, fieldListPair.key.getPiece()))
                .forEach(outList::add);
        return outList;
    }

    public void takeMove(Movement moveTaken) {
        this.moveTaken = moveTaken;
        this.endTime = Calendar.getInstance().getTime();
    }
}

class Pair<K, V> {
    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

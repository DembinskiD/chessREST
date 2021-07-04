package com.github.dembinskid.chessrest.elements.gameboard;

import com.github.dembinskid.chessrest.elements.pieces.Piece;
import com.github.dembinskid.chessrest.elements.pieces.PieceColor;
import com.github.dembinskid.chessrest.elements.pieces.PieceType;
import lombok.Data;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Data
@Log
public class Board {
    private List<Field> gameBoard = new ArrayList<>();

    public Board() {
        for (var x = 1; x <= 8; x++) {
            for (var y = 1; y <= 8; y++) {
                gameBoard.add(new Field(x, y));
            }
        }
        initializeBoard();
    }

    //this method should be used when moving might be in place
    public Field updateField(int x, int y, Field field) {
        return gameBoard.set(((8 * (x - 1) + y) - 1), field);
    }

    //this method should be used to update field in place, without moving
    public Field updateField(Field field) {
        return gameBoard.set(((8 * (field.getX() - 1) + field.getY()) - 1), field);
    }

    public Field getFieldByCoordinates(int x, int y) {
        return gameBoard.get((8 * (x - 1) + y) - 1);
    }

    public void printBoard() {
        var boardPrint = new StringBuilder("\n");
        for (var x = 1; x <= 8; x++) {
            for (var y = 1; y <= 8; y++) {
                if (y == 1) boardPrint.append(x + " ");
                boardPrint.append(getFieldByCoordinates(y, x));
            }
            boardPrint.append("\n");
        }
        for (var i = 1; i <= 8; i++) {
            if (i == 1) boardPrint.append(String.format("    %s  ", Field.getLiteral(i)));
            else boardPrint.append(String.format("  %s  ", Field.getLiteral(i)));
        }
        boardPrint.append("\n");
        log.info(boardPrint.toString());
    }

    public List<Field> getNeighbours(Field field) {
        int minX = field.getX() == 1 ? field.getX() : field.getX() - 1;
        int maxX = field.getX() == 8 ? field.getX() : field.getX() + 1;
        int minY = field.getY() == 1 ? field.getY() : field.getY() - 1;
        int maxY = field.getY() == 8 ? field.getY() : field.getY() + 1;
        ArrayList<Field> neighboursList = new ArrayList<>();
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                if (!getFieldByCoordinates(x, y).isTaken()) neighboursList.add(getFieldByCoordinates(x, y));
            }
        }
        return neighboursList;
    }

    public void initializeBoard() {
        for (PieceType type : PieceType.values()) {
            for (Field field : type.getFields()) {
                PieceColor color = field.getY() == 2 || field.getY() == 1 ? PieceColor.WHITE : PieceColor.BLACK;
                addPieceToBoard(new Piece(type, field.getX(), field.getY(), color));
            }
        }
    }

    public Field addPieceToBoard(Piece piece) {
        getFieldByCoordinates(piece.getInitialPosX(), piece.getInitialPosY()).setTaken(true);
        getFieldByCoordinates(piece.getInitialPosX(), piece.getInitialPosY()).setPiece(piece);
        return getFieldByCoordinates(piece.getInitialPosX(), piece.getInitialPosY());
    }
}

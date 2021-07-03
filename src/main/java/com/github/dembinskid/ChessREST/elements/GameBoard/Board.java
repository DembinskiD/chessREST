package com.github.dembinskid.ChessREST.elements.GameBoard;

import com.github.dembinskid.ChessREST.elements.Pieces.Piece;
import com.github.dembinskid.ChessREST.elements.Pieces.PieceColor;
import com.github.dembinskid.ChessREST.elements.Pieces.PieceType;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Board {
    private ArrayList<Field> board = new ArrayList<>(); //todo przemyśleć rozwiązanie dla Field[](1 dimension)

    public Board() {
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                board.add(new Field(x, y));
            }
        }
        initializeBoard();
    }

    public Field getFieldByCoordinates(int x, int y) {
        return board.get((8*(x-1) + y) - 1);
    }

    public void printBoard() {
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                if(y == 1) System.out.print(x + " ");
                System.out.print(getFieldByCoordinates(y, x).toString());
            }
            System.out.println();
        }
        for (int i = 1; i <= 8; i++) {
            if(i == 1) System.out.printf("    %s  ", Field.getLiteral(i));
            else System.out.printf("  %s  ", Field.getLiteral(i));
        }
    }

    public ArrayList<Field> getNeighbours(Field field) {
        int minX = field.getX() == 1 ? field.getX() : field.getX()-1;
        int maxX = field.getX() == 8 ? field.getX() : field.getX()+1;
        int minY = field.getY() == 1 ? field.getY() : field.getY()-1;
        int maxY = field.getY() == 8 ? field.getY() : field.getY()+1;
        ArrayList<Field> neighboursList = new ArrayList<>();
        for(int x = minX; x <= maxX; x++) {
            for(int y = minY; y <= maxY; y++) {
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

    public void addPieceToBoard(Piece piece) {
        getFieldByCoordinates(piece.getInitialPosX(), piece.getInitialPosY()).setTaken(true);
        getFieldByCoordinates(piece.getInitialPosX(), piece.getInitialPosY()).setPiece(piece);
    }
}

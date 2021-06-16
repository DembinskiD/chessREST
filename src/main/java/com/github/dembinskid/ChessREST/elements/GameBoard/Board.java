package com.github.dembinskid.ChessREST.elements.GameBoard;

import com.github.dembinskid.ChessREST.elements.Pieces.Piece;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Board {
    private Field[][] board = new Field[8][8];
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;

    public Board() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = new Field(new Position(PosX.values()[x].getValue(), PosY.values()[y].getValue()));
            }
        }
        printBoard();
    }

    public void printBoard() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                System.out.print(board[y][7 - x].toString() + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<Position> getNeighbours(Position position, int distance) {
//        int minX = position.getPosX().getValue() == 1 ? position.getPosX().getValue() : position.getPosX().getValue()-1;
//        int maxX = position.getPosX().getValue() == 8 ? position.getPosX().getValue() : position.getPosX().getValue()+1;
//        int minY = position.getPosY().getValue() == 1 ? position.getPosY().getValue() : position.getPosY().getValue()-1;;
//        int maxY = position.getPosY().getValue() == 8 ? position.getPosY().getValue() : position.getPosY().getValue()+1;
        ArrayList<Position> neighboursList = new ArrayList<>();
//        for(int x = minX; x <= maxX; x++) {
//            for(int y = minY; y <= maxY; y++) {
//                if (board[x][y].isFree()) neighboursList.add(board[x][y].getPosition());
//            }
//        }
        return neighboursList;
    }

    public void initializePieces() {

    }
}

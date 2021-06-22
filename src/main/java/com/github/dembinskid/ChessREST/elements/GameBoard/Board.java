package com.github.dembinskid.ChessREST.elements.GameBoard;

import com.github.dembinskid.ChessREST.elements.Pieces.Piece;
import com.github.dembinskid.ChessREST.elements.Pieces.PieceColor;
import com.github.dembinskid.ChessREST.elements.Pieces.PieceType;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Board {
    private Field[][] board = new Field[8][8]; //todo przemyśleć rozwiązanie dla Field[](1 dimension)

    public Board() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = new Field(x, y);
            }
        }
        initializeBoard();
    }

    public void printBoard() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                System.out.print(board[y][7 - x].toString() + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<Field> getNeighbours(Field field) {
        int minX = field.getX() == 0 ? field.getX() : field.getX()-1;
        int maxX = field.getX() == 7 ? field.getX() : field.getX()+1;
        int minY = field.getY() == 0 ? field.getY() : field.getY()-1;
        int maxY = field.getY() == 7 ? field.getY() : field.getY()+1;
        ArrayList<Field> neighboursList = new ArrayList<>();
        for(int x = minX; x <= maxX; x++) {
            for(int y = minY; y <= maxY; y++) {
                if (!board[x][y].isTaken()) neighboursList.add(getFieldByCoordinates(x, y));
            }
        }
        return neighboursList;
    }

    public void initializeBoard() {
        for (PieceType type : PieceType.values()) {
            for (Field field : type.getFields()) {
                PieceColor color = field.getY() == 1 || field.getY() == 0 ? PieceColor.WHITE : PieceColor.BLACK;
                addPieceToBoard(new Piece(type, field.getX(), field.getY(), color));
            }
        }
    }

    public void addPieceToBoard(Piece piece) {
        this.board[piece.getInitialPosX()][piece.getInitialPosY()].setTaken(true);
        this.board[piece.getInitialPosX()][piece.getInitialPosY()].setPiece(piece);
    }


        public boolean hasEnemyInFront(Field field) {
        if (field.isTaken()) {
            if ((field.getY() == 7 && field.getPiece().getPieceColor().equals(PieceColor.WHITE)) ||
                    (field.getY() == 0 && field.getPiece().getPieceColor().equals(PieceColor.BLACK))) {
                return false;
            } else {
                int yVal = field.getPiece().getPieceColor().equals(PieceColor.WHITE) ?
                        field.getY() + 1 :
                        field.getY() - 1;

                return !this.board[field.getX()][yVal].isTaken();
            }
        }
        return false;
    }
    public Field getFieldByCoordinates(int x, int y) {
        return this.board[x][y];
    }
}

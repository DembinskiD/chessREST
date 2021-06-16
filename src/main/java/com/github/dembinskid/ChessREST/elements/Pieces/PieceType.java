package com.github.dembinskid.ChessREST.elements.Pieces;

import com.github.dembinskid.ChessREST.elements.Board;
import com.github.dembinskid.ChessREST.elements.Position;

public enum PieceType {
    KING(1,1){
        @Override
        public void move(Position position, Board board) {

        }
    };//, QUEEN, ROOK, BISHOP, KNIGHT, PAWN //Król, Królowa, Wieża, Goniec, Skoczek, Pion

    abstract public void move(Position position, Board board);
    private int x;
    private int y;
    private PieceType(int x, int y) {
        this.x = x;
        this.y = y;
    }


}

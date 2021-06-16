package com.github.dembinskid.ChessREST.elements;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Board {
    private Field[][] board = new Field[8][8];

    public Board() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = new Field(PosX.values()[x], PosY.values()[y]);
            }
        }
        printBoard();
    }

    public void printBoard() {
        for(int x = 0 ; x < 8 ; x++) {
            for (int y = 0; y < 8; y++) {
                System.out.print(board[y][7-x].toString() + " ");
            }
            System.out.println();
        }
    }
}

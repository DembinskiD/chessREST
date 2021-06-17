package com.github.dembinskid.ChessREST.elements.GameBoard;

import lombok.Data;

@Data
public class Position {
    private PosX posX;
    private PosY posY;

    public Position(int posX, int posY) {
        this.posX = PosX.values()[posX];
        this.posY = PosY.values()[posY];
    }
}

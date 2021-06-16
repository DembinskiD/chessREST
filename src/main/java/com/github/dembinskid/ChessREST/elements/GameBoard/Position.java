package com.github.dembinskid.ChessREST.elements.GameBoard;

import lombok.Data;

@Data
public class Position {
    private PosX posX;
    private PosY posY;

    public Position(int posX, int posY) {
        this.posX = PosX.values()[posX+1];
        this.posY = PosY.values()[posY+1];
    }

    public PosX getPosX() {
        return posX;
    }

    public void setPosX(PosX posX) {
        this.posX = posX;
    }

    public PosY getPosY() {
        return posY;
    }

    public void setPosY(PosY posY) {
        this.posY = posY;
    }
}

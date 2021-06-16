package com.github.dembinskid.ChessREST.elements;

import lombok.Data;

@Data
public class Position {
    private PosX posX;
    private PosY posY;

    public Position(PosX posX, PosY posY) {
        this.posX = posX;
        this.posY = posY;
    }


}

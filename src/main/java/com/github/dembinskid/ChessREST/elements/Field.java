package com.github.dembinskid.ChessREST.elements;

import lombok.Data;

@Data
public class Field {
    private PosX posX;
    private PosY posY;
    private FieldColor fieldColor;
    private boolean isFree;

    public Field(PosX posX, PosY posY) {
        this.posX = posX;
        this.posY = posY;
        this.fieldColor = posX.getValue() + posY.getValue() % 2 == 0
                ? FieldColor.BLACK : FieldColor.WHITE;
        this.isFree = true;
    }

    @Override
    public String toString() {
        return posX + String.valueOf(posY.getValue());
    }
}

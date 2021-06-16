package com.github.dembinskid.ChessREST.elements.GameBoard;

import lombok.Data;

@Data
public class Field {
    private Position position;
    private FieldColor fieldColor;
    private boolean isFree;

    public Field(Position position) {
        this.position = position;
//        this.fieldColor = position.getPosX().getValue() + position.getPosY().getValue() % 2 == 0
//                ? FieldColor.BLACK : FieldColor.WHITE;
        this.isFree = true;
    }

    @Override
    public String toString() {
        return position.getPosX().getValue() + String.valueOf(position.getPosY().getValue());
    }
}

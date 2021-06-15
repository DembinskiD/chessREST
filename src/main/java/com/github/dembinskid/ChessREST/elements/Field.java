package com.github.dembinskid.ChessREST.elements;

import lombok.Data;

@Data
public class Field {
    private FieldCoordX fieldCoordX;
    private FieldCoordY fieldCoordY;
    private FieldColor fieldColor;
    private boolean isFree;

    public Field(FieldCoordX fieldCoordX, FieldCoordY fieldCoordY) {
        this.fieldCoordX = fieldCoordX;
        this.fieldCoordY = fieldCoordY;
        this.fieldColor = fieldCoordX.getValue() + fieldCoordY.getValue() % 2 == 0
                ? FieldColor.BLACK : FieldColor.WHITE;
        this.isFree = true;
    }

    @Override
    public String toString() {
        return fieldCoordX + String.valueOf(fieldCoordY.getValue());
    }
}

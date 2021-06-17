package com.github.dembinskid.ChessREST.elements.GameBoard;

import com.github.dembinskid.ChessREST.elements.Pieces.Piece;
import lombok.Data;

@Data
public class Field {
    private Position position;
    private FieldColor fieldColor;
    private boolean isTaken;
    private Piece piece;

    public Field(Position position) {
        this.position = position;
        this.fieldColor = position.getPosX().getValue() + position.getPosY().getValue() % 2 == 0
                ? FieldColor.BLACK : FieldColor.WHITE;
        this.isTaken = false;
    }

    @Override
    public String toString() {
        return isTaken() ? piece.getPieceType().getShortName() + piece.getPieceColor().getShortName() : position.getPosX().toString() + position.getPosY().toString();
    }
}

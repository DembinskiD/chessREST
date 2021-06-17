package com.github.dembinskid.ChessREST.elements.GameBoard;

import com.github.dembinskid.ChessREST.elements.Pieces.Piece;
import lombok.Data;

@Data
public class Field {
    private Position position;
    private FieldColor fieldColor;
    private boolean isFree;
    private boolean isTaken;
    private Piece piece;

    public Field(Position position) {
        this.position = position;
        this.fieldColor = position.getPosX().getValue() + position.getPosY().getValue() % 2 == 0
                ? FieldColor.BLACK : FieldColor.WHITE;
        this.isFree = true;
        this.isFree = false;
    }

    @Override
    public String toString() {
        return isFree() ? position.getPosX().toString() + position.getPosY().toString() : piece.getPieceType().getShortName() + piece.getPieceColor().getShortName();
    }
}

package com.github.dembinskid.ChessREST.elements.Pieces;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Piece {
    private PieceType pieceType;
    private int initialPosX;
    private int initialPosY;
    private PieceColor pieceColor;

    public Piece(PieceType pieceType, PieceColor pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }
}

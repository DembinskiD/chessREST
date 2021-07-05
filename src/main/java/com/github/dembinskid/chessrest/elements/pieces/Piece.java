package com.github.dembinskid.chessrest.elements.pieces;

import com.github.dembinskid.chessrest.elements.Color;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Piece {
    private PieceType pieceType;
    private int initialPosX;
    private int initialPosY;
    private Color color;

    public Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }
}

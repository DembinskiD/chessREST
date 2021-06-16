package com.github.dembinskid.ChessREST.elements.Pieces;

import com.github.dembinskid.ChessREST.elements.PosX;
import com.github.dembinskid.ChessREST.elements.PosY;
import com.github.dembinskid.ChessREST.elements.Position;
import lombok.Data;

@Data
public class Piece {
    private PieceType pieceType;
    private PieceColor pieceColor;
    private PosX initialPosX;
    private PosY initialPosY;
    private Position position;
}

package com.github.dembinskid.ChessREST.elements.Pieces;

import com.github.dembinskid.ChessREST.elements.GameBoard.PosX;
import com.github.dembinskid.ChessREST.elements.GameBoard.PosY;
import com.github.dembinskid.ChessREST.elements.GameBoard.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Piece {
    private PieceType pieceType;
    private PosX initialPosX;
    private PosY initialPosY;
    private Position position;
    private PieceColor pieceColor;


}

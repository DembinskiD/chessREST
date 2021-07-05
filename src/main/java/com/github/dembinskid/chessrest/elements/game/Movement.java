package com.github.dembinskid.chessrest.elements.game;

import com.github.dembinskid.chessrest.elements.gameboard.Field;
import com.github.dembinskid.chessrest.elements.pieces.Piece;
import lombok.Data;

import java.util.UUID;

@Data
public class Movement {
    UUID movementId;
    UUID gameId;
    Field source;
    Field target;
    Piece movedPiece;

    public Movement(UUID gameId, Field source, Field target, Piece movedPiece) {
        this.movementId = UUID.randomUUID();
        this.gameId = gameId;
        this.source = source;
        this.target = target;
        this.movedPiece = movedPiece;
    }
}

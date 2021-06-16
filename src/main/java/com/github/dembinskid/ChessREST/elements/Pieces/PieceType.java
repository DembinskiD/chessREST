package com.github.dembinskid.ChessREST.elements.Pieces;

import com.github.dembinskid.ChessREST.elements.GameBoard.Position;

import java.util.ArrayList;
import java.util.Arrays;


public enum PieceType {
    KING(new Position(1, 5), new Position(8, 5)),
    QUEEN(new Position(1, 4), new Position(8, 4)),
    ROOK(new Position(1, 1), new Position(1, 8),
            new Position(8, 1), new Position(8, 8)),
    BISHOP(new Position(1, 3), new Position(1, 6),
            new Position(8, 3), new Position(8, 6)),
    KNIGHT(new Position(1, 2), new Position(1, 7),
            new Position(8, 2), new Position(8, 7)),
    PAWN;

    private ArrayList<Position> positions;

    private PieceType(Position... positions) {
        if(positions == null) {
            ArrayList<Integer> xPos = new ArrayList<>();
            xPos.add(2);
            xPos.add(7);
            this.positions = new ArrayList<>();
            xPos.forEach(x -> fillPawns(x, this.positions));

        } else this.positions.addAll(Arrays.asList(positions));
    }
    private void fillPawns(int x, ArrayList<Position> pos) {
        for (int i = 0; i < 8; i++) {
            pos.add(new Position(x, i));
        }
    }

    public void listPositions() {
        this.positions.forEach(System.out::println);
    }


}

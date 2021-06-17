package com.github.dembinskid.ChessREST.elements.Pieces;

import com.github.dembinskid.ChessREST.elements.GameBoard.Position;

import java.util.ArrayList;
import java.util.Arrays;


public enum PieceType {
    KING(new Position(4, 0), new Position(4, 7)) {
        @Override
        public String getShortName() { return "K"; }
    },
    QUEEN(new Position(3, 0), new Position(3, 7)) {
        @Override
        public String getShortName() { return "Q"; }
    },
    ROOK(new Position(0, 0), new Position(7, 0),
            new Position(0, 7), new Position(7, 7)) {
        @Override
        public String getShortName() { return "R"; }
    },
    BISHOP(new Position(2, 0), new Position(5, 0),
            new Position(2, 7), new Position(5, 7)) {
        @Override
        public String getShortName() { return "B"; }
    },
    KNIGHT(new Position(1, 0), new Position(6, 0),
            new Position(1, 7), new Position(6, 7)) {
        @Override
        public String getShortName() { return "S"; }
    },
    PAWN(null) {
        @Override
        public String getShortName() { return "P"; }
    };

    private ArrayList<Position> positions = new ArrayList<>();
    abstract public String getShortName();

    PieceType(Position... positions) {
        if(positions == null) {
            ArrayList<Integer> xPos = new ArrayList<>();
            xPos.add(1);
            xPos.add(6);
            xPos.forEach(x -> fillPawns(x, this.positions));

        } else {
            this.positions.addAll(Arrays.asList(positions));
        }
    }
    private void fillPawns(int x, ArrayList<Position> pos) {
        for (int i = 0; i < 8; i++) {
            pos.add(new Position(i, x));
        }
    }

    public void listPositions() {
        this.positions.forEach(System.out::println);
    }

    public ArrayList<Position> getPositions() {
        return this.positions;
    }


}

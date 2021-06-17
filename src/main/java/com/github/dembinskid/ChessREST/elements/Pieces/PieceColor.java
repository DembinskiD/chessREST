package com.github.dembinskid.ChessREST.elements.Pieces;

public enum PieceColor {
    WHITE("W"), BLACK("B");

    private String shortName;

    PieceColor(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return this.shortName;
    }
}

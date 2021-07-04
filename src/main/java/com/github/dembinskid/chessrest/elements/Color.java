package com.github.dembinskid.chessrest.elements;

public enum Color {
    WHITE("W"), BLACK("B");

    private final String shortName;

    Color(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return this.shortName;
    }
}

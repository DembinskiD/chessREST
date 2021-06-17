package com.github.dembinskid.ChessREST.elements.GameBoard;

public enum PosX {
    A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7);

    private int value;

    private PosX(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.name();
    }
}

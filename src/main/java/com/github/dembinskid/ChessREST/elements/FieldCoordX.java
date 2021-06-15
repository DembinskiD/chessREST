package com.github.dembinskid.ChessREST.elements;

public enum FieldCoordX {
    A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8);

    private int value;

    private FieldCoordX(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

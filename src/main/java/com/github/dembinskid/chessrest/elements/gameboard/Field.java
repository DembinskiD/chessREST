package com.github.dembinskid.chessrest.elements.gameboard;

import com.github.dembinskid.chessrest.elements.pieces.Piece;
import lombok.Data;

@Data
public class Field implements Comparable<Field> {
    private int x;
    private int y;
    private FieldColor fieldColor;
    private boolean isTaken;
    private Piece piece;

    public Field() {
        this.fieldColor = x + y % 2 == 0
                ? FieldColor.BLACK : FieldColor.WHITE;
        this.isTaken = false;
    }

    public Field(char x, int y) {
        new Field();
        this.x = getValueByLiteral(x);
        this.y = y;
    }

    public Field(int x, int y) {
        new Field();
        this.x = x;
        this.y = y;
    }

    public String taken() {
        return isTaken ? "T" : "F";
    }

    @Override
    public String toString() {
        var formatFree = "[%s,%s]";
        var formatTaken = "[%s_%s]";
        return isTaken() ?
                String.format(formatTaken, piece.getPieceColor().getShortName(), piece.getPieceType().getShortName()) :
                String.format(formatFree, getLiteral(x), y);
    }

    public static int getValueByLiteral(char x) {
        int out;
        switch(x) {
            case 'A':
                out = 1;
                break;
            case 'B':
                out = 2;
                break;
            case 'C':
                out = 3;
                break;
            case 'D':
                out = 4;
                break;
            case 'E':
                out = 5;
                break;
            case 'F':
                out = 6;
                break;
            case 'G':
                out = 7;
                break;
            case 'H':
                out = 8;
                break;
            default:
                out = 0;
                break;
        }
        return out;
    }

    public static String getLiteral(int x) {
        String literal;
        switch (x) {
            case 1:
                literal = "A";
                break;
            case 2:
                literal = "B";
                break;
            case 3:
                literal = "C";
                break;
            case 4:
                literal = "D";
                break;
            case 5:
                literal = "E";
                break;
            case 6:
                literal = "F";
                break;
            case 7:
                literal = "G";
                break;
            case 8:
                literal = "H";
                break;
            default:
                literal = "?";
                break;
        }
        return literal;
    }

    @Override
    public int compareTo(Field o) {
        if (this.getX() > o.getX()) {
            return 1;
        } else if (this.getX() < o.getX()) {
            return -1;
        } else return Integer.compare(this.getY(), o.getY());
    }


}

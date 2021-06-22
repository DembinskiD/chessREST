package com.github.dembinskid.ChessREST.elements.GameBoard;

import com.github.dembinskid.ChessREST.elements.Pieces.Piece;
import lombok.Data;

@Data
public class Field implements Comparable<Field> {
    private int x;
    private int y;
    private FieldColor fieldColor;
    private boolean isTaken;
    private Piece piece;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        this.fieldColor = x + y % 2 == 0
                ? FieldColor.BLACK : FieldColor.WHITE;
        this.isTaken = false;
    }



    @Override
    public String toString() {
        return isTaken() ? piece.getPieceColor().getShortName() + piece.getPieceType().getShortName() : getLiteral(x) + y;
//        return isTaken() ? piece.getPieceColor().getShortName() + piece.getPieceType().getShortName() : "[" + x + "," + y + "]";
    }

    private String getLiteral(int x) {
        String literal = "?";
        switch(x) {
            case 0: literal = "A";
                break;
            case 1: literal = "B";
                break;
            case 2: literal = "C";
                break;
            case 3: literal = "D";
                break;
            case 4: literal = "E";
                break;
            case 5: literal = "F";
                break;
            case 6: literal = "G";
                break;
            case 7: literal = "H";
                break;
        }
        return literal;
    }

    @Override
    public int compareTo(Field o) {
        if(this.getX() > o.getX()) {
            return 1;
        } else if(this.getX() < o.getX()) {
            return -1;
        } else return Integer.compare(this.getY(), o.getY());
    }
}

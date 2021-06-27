package com.github.dembinskid.ChessREST.elements.Pieces;

import com.github.dembinskid.ChessREST.elements.GameBoard.Board;
import com.github.dembinskid.ChessREST.elements.GameBoard.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public enum PieceType {
    KING(new Field(5, 1), new Field(5, 8)) {
        @Override
        public String getShortName() {
            return "K";
        }

        @Override
        public ArrayList<Field> getPossibleMoves(Board board, Field field) {
            ArrayList<Field> outputList = new ArrayList<>();
            for (int x = field.getX() - 1; x <= field.getX() + 1; x++) {
                for (int y = field.getY() - 1; y <= field.getY() + 1; y++) {
                        outputList.add(new Field(x, y));
                }
            }
            outputList.removeIf(x -> (x.getX() == field.getX() && x.getY() == field.getY())
                    || (!fieldInBorder(x.getX()) || !fieldInBorder(x.getY())));
            return outputList;
        }
    },
    QUEEN(new Field(4, 1), new Field(4, 8)) {
        @Override
        public String getShortName() {
            return "Q";
        }

        @Override
        public ArrayList<Field> getPossibleMoves(Board board, Field field) {
            ArrayList<Field> outputList = new ArrayList<>(getInlineMoves(field));
            outputList.addAll(getDiagonalMoves(field));
            Collections.sort(outputList);
            return outputList;
        }
    },
    ROOK(new Field(1, 1), new Field(8, 1),
            new Field(1, 8), new Field(8, 8)) {
        @Override
        public String getShortName() {
            return "R";
        }

        @Override
        public ArrayList<Field> getPossibleMoves(Board board, Field field) {
            return getInlineMoves(field);
        }
    },
    BISHOP(new Field(3, 1), new Field(6, 1),
            new Field(3, 8), new Field(6, 8)) {
        @Override
        public String getShortName() {
            return "B";
        }

        @Override
        public ArrayList<Field> getPossibleMoves(Board board, Field field) {
            return getDiagonalMoves(field);
        }
    },
    KNIGHT(new Field(2, 1), new Field(7, 1),
            new Field(2, 8), new Field(7, 8)) {
        @Override
        public String getShortName() {
            return "S";
        }

        @Override
        public ArrayList<Field> getPossibleMoves(Board board, Field field) {
            ArrayList<Field> outputList = new ArrayList<>();
            Arrays.stream(new int[]{-1, 1}).forEach(x -> Arrays.stream(new int[]{-2, 2}).forEach(y -> {
                if (!(field.getX() - x < 0 || field.getX() - x > 7 || field.getY() - y < 0 || field.getY() - y > 7))
                    outputList.add(new Field(field.getX() - x, field.getY() - y));
                if (!(field.getX() - y < 0 || field.getX() - y > 7 || field.getY() - x < 0 || field.getY() - x > 7))
                    outputList.add(new Field(field.getX() - y, field.getY() - x));
            }));
            return outputList;
        }
    },
    PAWN {
        @Override
        public String getShortName() {
            return "P";
        }

        @Override
        public ArrayList<Field> getPossibleMoves(Board board, Field field) {
            ArrayList<Field> outputList = new ArrayList<>();
            int posY = board.getFieldByCoordinates(field.getX(), field.getY()).getPiece().getPieceColor().equals(PieceColor.WHITE) ? field.getY() + 1 : field.getY() - 1;
            if (field.getY() == 1 && board.getFieldByCoordinates(field.getX(), field.getY()).getPiece().getPieceColor().equals(PieceColor.WHITE)) {
                outputList.add(new Field(field.getX(), posY + 1));
            } else if (field.getY() == 6 && board.getFieldByCoordinates(field.getX(), field.getY()).getPiece().getPieceColor().equals(PieceColor.BLACK)) {
                outputList.add(new Field(field.getX(), posY - 1));
            }
            outputList.add(new Field(field.getX(), posY));
            return outputList;
        }
    };

    private ArrayList<Field> fields = new ArrayList<>();

    abstract public String getShortName();

    abstract public ArrayList<Field> getPossibleMoves(Board board, Field field);

    PieceType(Field... fields) {
        if (fields.length == 0) {
            ArrayList<Integer> xPos = new ArrayList<>();
            xPos.add(2);
            xPos.add(7);
            xPos.forEach(x -> fillPawns(x, this.fields));

        } else {
            this.fields.addAll(Arrays.asList(fields));
        }
    }

    private void fillPawns(int x, ArrayList<Field> pos) {
        for (int i = 1; i <= 8; i++) {
            pos.add(new Field(i, x));
        }
    }

    public void listPositions() {
        this.fields.forEach(System.out::println);
    }

    public ArrayList<Field> getFields() {
        return this.fields;
    }

    public ArrayList<Field> getInlineMoves(Field field) {
        ArrayList<Field> outputList = new ArrayList<>();
        ArrayList<Integer> listX = IntStream.rangeClosed(0, 7).boxed().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> listY = new ArrayList<>(listX);
        listX.removeIf(p -> field.getX() == p);
        listY.removeIf(p -> field.getY() == p);

        for (int i = 0; i < listX.size(); i++) {
            outputList.add(new Field(listX.get(i), field.getY()));
        }

        for (int i = 0; i < listY.size(); i++) {
            outputList.add(new Field(field.getX(), listY.get(i)));
        }
        return outputList;
    }

    public ArrayList<Field> getDiagonalMoves(Field field) {
        ArrayList<Field> outputList = new ArrayList<>();
        ArrayList<Integer> listX = IntStream.rangeClosed(0, 7).boxed().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> listY = new ArrayList<>(listX);
        listX.removeIf(p -> field.getX() == p);
        listY.removeIf(p -> field.getY() == p);

        for (int i = 1; i < listX.size(); i++) {
            if (fieldInBorder(field.getX() + i) && fieldInBorder(field.getY() + i))
                outputList.add(new Field(field.getX() + i, field.getY() + i));
            if (fieldInBorder(field.getX() + i) && fieldInBorder(field.getY() - i))
                outputList.add(new Field(field.getX() + i, field.getY() - i));
            if (fieldInBorder(field.getX() - i) && fieldInBorder(field.getY() + i))
                outputList.add(new Field(field.getX() - i, field.getY() + i));
            if (fieldInBorder(field.getX() - i) && fieldInBorder(field.getY() - i))
                outputList.add(new Field(field.getX() - i, field.getY() - i));
        }
        return outputList;
    }

    private static boolean fieldInBorder(int coordinate) {
        return coordinate >= 0 && coordinate <= 7;
    }
}

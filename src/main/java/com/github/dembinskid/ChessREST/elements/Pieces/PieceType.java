package com.github.dembinskid.ChessREST.elements.Pieces;

import com.github.dembinskid.ChessREST.elements.GameBoard.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public enum PieceType {
    KING(new Field(4, 0), new Field(4, 7)) {
        @Override
        public String getShortName() { return "K"; }
        @Override
        public ArrayList<Field> getPossibleMoves(Board board, Field field) {
            int minX = field.getX() == 0 ? field.getX() : field.getX()-1;
            int maxX = field.getX() == 7 ? field.getX() : field.getX()+1;
            int minY = field.getY() == 0 ? field.getY() : field.getY()-1;;
            int maxY = field.getY() == 7 ? field.getY() : field.getY()+1;
            ArrayList<Field> neighboursList = new ArrayList<>();
            for(int x = minX; x <= maxX; x++) {
                for(int y = minY; y <= maxY; y++) {
                    if (!board.getFieldByCoordinates(x, y).isTaken()) neighboursList.add(board.getFieldByCoordinates(x, y));
                }
            }
            return neighboursList;
        }
    },
    QUEEN(new Field(3, 0), new Field(3, 7)) {
        @Override
        public String getShortName() { return "Q"; }
    },
    ROOK(new Field(0, 0), new Field(7, 0),
            new Field(0, 7), new Field(7, 7)) {
        @Override
        public String getShortName() { return "R"; }
        @Override
        public ArrayList<Field> getPossibleMoves(Board board, Field field) {
            ArrayList<Field> outputList = new ArrayList<>();
            ArrayList<Integer> listX = IntStream.rangeClosed(0, 7).boxed().collect(Collectors.toCollection(ArrayList::new));
            ArrayList<Integer> listY = new ArrayList<>(listX);
            listX.removeIf(p -> field.getX() == p);
            listY.removeIf(p -> field.getY() == p);

            for (int i = 0; i < listX.size(); i++) {
                outputList.add(new Field(listX.get(i), listY.get(i)));
            }
            return outputList;
        }
    },
    BISHOP(new Field(2, 0), new Field(5, 0),
            new Field(2, 7), new Field(5, 7)) {
        @Override
        public String getShortName() { return "B"; }
    },
    KNIGHT(new Field(1, 0), new Field(6, 0),
            new Field(1, 7), new Field(6, 7)) {
        @Override
        public String getShortName() { return "S"; }
    },
    PAWN {
        @Override
        public String getShortName() { return "P"; }
        @Override
        public ArrayList<Field> getPossibleMoves(Board board, Field field) {
            ArrayList<Field> outputList = new ArrayList<>();
            int posY = board.getFieldByCoordinates(field.getX(), field.getY()).getPiece().getPieceColor().equals(PieceColor.WHITE) ? field.getY()+1 : field.getY()-1;
            if(field.getY()==1 && board.getFieldByCoordinates(field.getX(), field.getY()).getPiece().getPieceColor().equals(PieceColor.WHITE)) {
                outputList.add(new Field(field.getX(), posY+1));
            } else if (field.getY()==6 && board.getFieldByCoordinates(field.getX(), field.getY()).getPiece().getPieceColor().equals(PieceColor.BLACK)) {
                outputList.add(new Field(field.getX(), posY-1));
            }
            outputList.add(new Field(field.getX(), posY));
            return outputList;
        }
    };

    private ArrayList<Field> fields = new ArrayList<>();
    abstract public String getShortName();
    public ArrayList<Field> getPossibleMoves(Board board, Field field) {
        return new ArrayList<>();
    };

    PieceType(Field... fields) {
        if(fields.length == 0) {
            ArrayList<Integer> xPos = new ArrayList<>();
            xPos.add(1);
            xPos.add(6);
            xPos.forEach(x -> fillPawns(x, this.fields));

        } else {
            this.fields.addAll(Arrays.asList(fields));
        }
    }
    private void fillPawns(int x, ArrayList<Field> pos) {
        for (int i = 0; i < 8; i++) {
            pos.add(new Field(i, x));
        }
    }

    public void listPositions() {
        this.fields.forEach(System.out::println);
    }

    public ArrayList<Field> getFields() {
        return this.fields;
    }


}
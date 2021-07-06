package com.github.dembinskid.chessrest.elements.pieces;

import com.github.dembinskid.chessrest.elements.Color;
import com.github.dembinskid.chessrest.elements.gameboard.Board;
import com.github.dembinskid.chessrest.elements.gameboard.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
            //generate list of moves
            for (int x = field.getX() - 1; x <= field.getX() + 1; x++) {
                for (int y = field.getY() - 1; y <= field.getY() + 1; y++) {
                        if(isFieldInBorder(x) && isFieldInBorder(y)) outputList.add(board.getFieldByCoordinates(x, y));
                }
            }
            //remove checked field position
            outputList.removeIf(x -> (x.getX() == field.getX() && x.getY() == field.getY()));
            //remove taken fields but not ones with enemy on

            outputList.removeIf(fld -> board.getFieldByCoordinates(fld.getX(), fld.getY()).isTaken() &&
                    board.getFieldByCoordinates(fld.getX(), fld.getY()).getPiece().getColor().equals(
                            board.getFieldByCoordinates(field.getX(), field.getY()).getPiece().getColor()
                    ));
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
            ArrayList<Field> outputList = new ArrayList<>(getInlineMoves(board, field));
            outputList.addAll(getDiagonalMoves(board, field));
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
        public List<Field> getPossibleMoves(Board board, Field field) {
            return getInlineMoves(board, field);
        }
    },
    BISHOP(new Field(3, 1), new Field(6, 1),
            new Field(3, 8), new Field(6, 8)) {
        @Override
        public String getShortName() {
            return "B";
        }

        @Override
        public List<Field> getPossibleMoves(Board board, Field field) {
            return getDiagonalMoves(board, field);
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
                checkForUnitCollisionInKnightMovementAndAddToList(board, field, x, y, outputList);
                checkForUnitCollisionInKnightMovementAndAddToList(board, field, y, x, outputList);
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
        public List<Field> getPossibleMoves(Board board, Field field) {
            ArrayList<Field> outputList = new ArrayList<>();
            int posY = board.getFieldByCoordinates(field.getX(), field.getY()).getPiece().getColor().equals(Color.WHITE) ? field.getY() + 1 : field.getY() - 1;
            if (field.getY() == 2 && board.getFieldByCoordinates(field.getX(), field.getY()).getPiece().getColor().equals(Color.WHITE)) {
                outputList.add(new Field(field.getX(), posY + 1));
            } else if (field.getY() == 7 && board.getFieldByCoordinates(field.getX(), field.getY()).getPiece().getColor().equals(Color.BLACK)) {
                outputList.add(new Field(field.getX(), posY - 1));
            }
            outputList.add(new Field(field.getX(), posY));
            return outputList;
        }
    };

    private final List<Field> fields = new ArrayList<>();

    public abstract String getShortName();

    public abstract List<Field> getPossibleMoves(Board board, Field field);

    PieceType(Field... fields) {
        if (fields.length == 0) {
            List<Integer> xPos = new ArrayList<>();
            xPos.add(2);
            xPos.add(7);
            xPos.forEach(x -> fillPawns(x, this.fields));

        } else {
            this.fields.addAll(Arrays.asList(fields));
        }
    }

    private void fillPawns(int x, List<Field> pos) {
        for (var i = 1; i <= 8; i++) {
            //noinspection SuspiciousNameCombination
            pos.add(new Field(i, x));
        }
    }

    public List<Field> getFields() {
        return this.fields;
    }

    List<Field> getInlineMoves(Board board, Field field) {
        ArrayList<Field> outputList = new ArrayList<>();

        ArrayList<Integer> listXAfter = IntStream.rangeClosed(field.getX(), 8).boxed().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> listXBefore = IntStream.rangeClosed(1, field.getX()).boxed().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> listYAfter = IntStream.rangeClosed(field.getY(), 8).boxed().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> listYBefore = IntStream.rangeClosed(1, field.getY()).boxed().collect(Collectors.toCollection(ArrayList::new));

        listXAfter.removeIf(p -> field.getX() == p);
        listXBefore.removeIf(p -> field.getX() == p);
        listYAfter.removeIf(p -> field.getY() == p);
        listYBefore.removeIf(p -> field.getY() == p);

        listXBefore.sort((a, b) -> a < b ? 1 : -1);
        listYBefore.sort((a, b) -> a < b ? 1 : -1);

        var isXAfterTaken = false;
        var isXBeforeTaken = false;
        var isYAfterTaken = false;
        var isYBeforeTaken = false;

        for (Integer x : listXBefore) {
                isXBeforeTaken = checkForUnitCollisionAndAddInLinearMovement(board, isXBeforeTaken, board.getFieldByCoordinates(field.getX(), field.getY()), x, field.getY(), outputList);
        }

        for (Integer x : listXAfter) {
                isXAfterTaken = checkForUnitCollisionAndAddInLinearMovement(board, isXAfterTaken, board.getFieldByCoordinates(field.getX(), field.getY()), x, field.getY(), outputList);
        }

        for (Integer y : listYBefore) {
                isYBeforeTaken = checkForUnitCollisionAndAddInLinearMovement(board, isYBeforeTaken, board.getFieldByCoordinates(field.getX(), field.getY()), field.getX(), y, outputList);
        }

        for (Integer y : listYAfter) {
                isYAfterTaken = checkForUnitCollisionAndAddInLinearMovement(board, isYAfterTaken, board.getFieldByCoordinates(field.getX(), field.getY()), field.getX(), y, outputList);
        }
        return outputList;
    }

    private boolean checkForUnitCollisionAndAddInLinearMovement(Board board, boolean flag, Field field, int posX, int posY, List<Field> outputList) {
        if(!flag) {
            if(!board.getFieldByCoordinates(posX, posY).isTaken()) {
                outputList.add(board.getFieldByCoordinates(posX, posY));
            } else {
                if(!arePiecesSameColorWithPos(board, field.getX(), posY, field.getX(), field.getY())) {
                    outputList.add(board.getFieldByCoordinates(posX, posY));
                }
            }
            flag = board.getFieldByCoordinates(posX, posY).isTaken();
        }
        return flag;
    }


    private boolean arePiecesSameColorWithPos(Board board, int x1, int y1, int x2, int y2) {
        return board.getFieldByCoordinates(x1, y1).getPiece().getColor().equals(
                board.getFieldByCoordinates(x2, y2).getPiece().getColor()
        );
    }

    private boolean checkForUnitCollisionAndAddInDiagonalMovement(Board board, boolean flag, Field field, int posX, int posY, ArrayList<Field> outputList) {

        if(isFieldInBorder(field.getX() + posX) && isFieldInBorder(field.getY() + posY) && !flag) { //todo can it be changed into a stream?
                if(!board.getFieldByCoordinates(field.getX() + posX, field.getY() + posY).isTaken() || //free
                        (board.getFieldByCoordinates(field.getX() + posX, field.getY() + posY).isTaken() &&
                                !board.getFieldByCoordinates(field.getX() + posX, field.getY() + posY).getPiece().getColor().equals(
                                        board.getFieldByCoordinates(field.getX(), field.getY()).getPiece().getColor()
                                ))) { //enemy
                    outputList.add(board.getFieldByCoordinates(field.getX() + posX, field.getY() + posY));
                }
                flag = board.getFieldByCoordinates(field.getX() + posX, field.getY() + posY).isTaken();
        }
        return flag;
    }

    List<Field> getDiagonalMoves(Board board, Field field) {
        ArrayList<Field> outputList = new ArrayList<>();
        ArrayList<Integer> listX = IntStream.rangeClosed(1, 8).boxed().collect(Collectors.toCollection(ArrayList::new));
        listX.removeIf(p -> field.getX() == p);
        var takenNE = false;
        var takenNW = false;
        var takenSE = false;
        var takenSW = false;

        for (var i = 1; i < listX.size(); i++) {
                    takenNE = checkForUnitCollisionAndAddInDiagonalMovement(board, takenNE, field, i, i, outputList);
                    takenNW = checkForUnitCollisionAndAddInDiagonalMovement(board, takenNW, field, -1*i, i, outputList);
                    takenSE = checkForUnitCollisionAndAddInDiagonalMovement(board, takenSE, field, i, -1*i, outputList);
                    takenSW = checkForUnitCollisionAndAddInDiagonalMovement(board, takenSW, field,-1*i, -1*i, outputList);
        }
        return outputList;
    }

    private static boolean isFieldInBorder(int coordinate) {
        return coordinate > 0 && coordinate <= 8;
    }

    private static void checkForUnitCollisionInKnightMovementAndAddToList(Board board, Field field, int x, int y, List<Field> outputList) {
        if(isFieldInBorder(field.getX() - x) &&
                isFieldInBorder(field.getY() - y)) {
            if(!board.getFieldByCoordinates(field.getX() - x, field.getY() - y).isTaken()) outputList.add(board.getFieldByCoordinates(field.getX() - x, field.getY() - y));
            else {
                if(!board.getFieldByCoordinates(field.getX() - x, field.getY() - y).getPiece().getColor().equals(board.getFieldByCoordinates(field.getX(), field.getY()).getPiece().getColor())) outputList.add(board.getFieldByCoordinates(field.getX() - x, field.getY() - y));
            }
        }
    }
}

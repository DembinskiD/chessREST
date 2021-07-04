package com.github.dembinskid.chessrest.elements.gameboard;

import com.github.dembinskid.chessrest.elements.pieces.PieceColor;
import com.github.dembinskid.chessrest.elements.pieces.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    Board board;
    ArrayList<Field> fields;
    ArrayList<Field> outputList;

    @BeforeEach
    void init() {
        this.board = new Board();
        this.fields = new ArrayList<>(this.board.getGameBoard());
        this.outputList = new ArrayList<>();
        board.printBoard();
    }

    @ParameterizedTest(name = "Testing getFieldByCoordinate wit {0} {1}")
    @MethodSource("provideCoordinates")
    void testGetFieldsByCoordinates(int coordX, int coordY) {
        System.out.println(this.board.getFieldByCoordinates(coordX, coordY));
        assertEquals(coordX, this.board.getFieldByCoordinates(coordX, coordY).getX());
        assertEquals(coordY, this.board.getFieldByCoordinates(coordX, coordY).getY());
    }

    private static Stream<Arguments> provideCoordinates() {
        return Stream.of(
                Arguments.of(4, 4),
                Arguments.of(5, 8),
                Arguments.of(8, 8),
                Arguments.of(1, 1),
                Arguments.of(3, 6),
                Arguments.of(2, 2)
        );
    }

    @Test
    void testBoardSize() {
        assertEquals(64, this.fields.size());
    }

    private static Stream<Arguments> provideParams() {
        return Stream.of(
                Arguments.of(16, PieceType.PAWN),
                Arguments.of(2, PieceType.KING),
                Arguments.of(2, PieceType.QUEEN),
                Arguments.of(4, PieceType.ROOK),
                Arguments.of(4, PieceType.KNIGHT),
                Arguments.of(4, PieceType.BISHOP)
        );
    }

    @ParameterizedTest(name = "Test amount of {1} on board")
    @MethodSource("provideParams")
    void testAmountOfPieces(int amount, PieceType pieceType) {
        this.fields.stream().filter(Field::isTaken).filter(x -> x.getPiece().getPieceType().equals(pieceType)).forEach(this.outputList::add);
        assertEquals(amount, this.outputList.size());
    }

    private static Stream<Arguments> provideColorParams() {
        return Stream.of(
                Arguments.of(PieceColor.WHITE),
                Arguments.of(PieceColor.BLACK)
        );
    }

    @ParameterizedTest(name = "Test for {0}")
    @MethodSource("provideColorParams")
    void testAmountOfWhitePieces(PieceColor pieceColor) {
        this.fields.stream().filter(Field::isTaken).filter(x -> x.getPiece().getPieceColor().equals(pieceColor)).forEach(outputList::add);
        assertEquals(16, this.outputList.size());
    }

    private static Stream<Arguments> neighbourHoodArguments() {
        return Stream.of(
                Arguments.of(new Field(1, 1), 0),
                Arguments.of(new Field(4, 1), 0),
                Arguments.of(new Field(1, 2), 2),
                Arguments.of(new Field(2, 2), 3),
                Arguments.of(new Field(1, 8), 0),
                Arguments.of(new Field(1, 7), 2),
                Arguments.of(new Field(2, 7), 3)
        );
    }

    @ParameterizedTest()
    @MethodSource("neighbourHoodArguments")
    void testNeighborhoodFunction(Field field, int expectedValue) {
        System.out.println(this.board.getNeighbours(field));
        assertEquals(expectedValue, this.board.getNeighbours(field).size());
    }
}
package com.github.dembinskid.ChessREST.elements.GameBoard;

import com.github.dembinskid.ChessREST.elements.Pieces.PieceColor;
import com.github.dembinskid.ChessREST.elements.Pieces.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    Board board;
    ArrayList<Field> fields;
    ArrayList<Field> outputList;

    @BeforeEach
    void init() {
        this.board = new Board();
        this.fields = new ArrayList<>();
        this.outputList = new ArrayList<>();
        board.initializeBoard();
        Arrays.stream(this.board.getBoard()).flatMap(Arrays::stream).forEach(x -> fields.add(x));
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

    @ParameterizedTest(name = "Test for {1}")
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
                Arguments.of(new Field(0, 0), 0),
                Arguments.of(new Field(3, 0), 0),
                Arguments.of(new Field(0, 1), 2),
                Arguments.of(new Field(1, 1), 3),
                Arguments.of(new Field(0, 7), 0),
                Arguments.of(new Field(0, 6), 2),
                Arguments.of(new Field(1, 6), 3)
        );
    }

    @ParameterizedTest()
    @MethodSource("neighbourHoodArguments")
    void testNeighborhoodFunction(Field field, int expectedValue) {
        System.out.println(this.board.getNeighbours(field));
        assertEquals(expectedValue, this.board.getNeighbours(field).size());
    }
}
package com.github.dembinskid.ChessREST.elements.GameBoard;

import com.github.dembinskid.ChessREST.elements.Pieces.PieceColor;
import com.github.dembinskid.ChessREST.elements.Pieces.PieceType;
import com.sun.org.apache.xpath.internal.Arg;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
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

    @Test
    void testNeighborhoodFunction() {//todo zrobić parametry tutaj
        Position position = new Position(0, 0);
        assertEquals(this.board.getNeighbours(position).size(), 0);
    }
}
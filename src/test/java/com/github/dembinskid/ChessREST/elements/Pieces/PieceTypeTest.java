package com.github.dembinskid.ChessREST.elements.Pieces;

import com.github.dembinskid.ChessREST.elements.GameBoard.Board;
import com.github.dembinskid.ChessREST.elements.GameBoard.Field;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceTypeTest {

    private static Stream<Arguments> attributeProvider() {
        return Stream.of( //todo sprawdzić argumenty dla sprawdzanych pól, nie powinny występować wartości 0

                Arguments.of(PieceType.KNIGHT, new Field(2, 1), List.of(new Field(3, 3), new Field(1, 3), new Field(4, 2))),
                Arguments.of(PieceType.KNIGHT, new Field(7, 1), List.of(new Field(8, 3), new Field(6, 3), new Field(5, 2))),
                Arguments.of(PieceType.KNIGHT, new Field(4, 7), List.of(new Field(2, 8),
                        new Field(2, 6),
                        new Field(3, 5),
                        new Field(5, 5),
                        new Field(6, 6),
                        new Field(6, 8))),
                Arguments.of(PieceType.PAWN, new Field(1, 2), List.of(
                        new Field(1, 3),
                        new Field(1, 4)
                )),
                Arguments.of(PieceType.PAWN, new Field(6, 7), List.of(
                        new Field(6, 6),
                        new Field(6, 5)
                )),
                Arguments.of(PieceType.BISHOP, new Field(2, 8), List.of(
                        new Field(1, 7),
                        new Field(3, 7),
                        new Field(4, 6),
                        new Field(5, 5),
                        new Field(6, 4),
                        new Field(7, 3),
                        new Field(8, 2)
                )),
                Arguments.of(PieceType.ROOK, new Field(3, 5), List.of(
                        new Field(1, 5),
                        new Field(2, 5),
                        new Field(4, 5),
                        new Field(5, 5),
                        new Field(6, 5),
                        new Field(7, 5),
                        new Field(8, 5),
                        new Field(3, 1),
                        new Field(3, 2),
                        new Field(3, 3),
                        new Field(3, 4),
                        new Field(3, 6),
                        new Field(3, 7),
                        new Field(3, 8)
                )),
                Arguments.of(PieceType.QUEEN, new Field(2, 2), List.of(
                        new Field(3, 3),
                        new Field(1, 3),
                        new Field(4, 4),
                        new Field(5, 5),
                        new Field(6, 6),
                        new Field(8, 8),
                        new Field(2, 1),
                        new Field(3, 1),
                        new Field(2, 3),
                        new Field(2, 4),
                        new Field(2, 5),
                        new Field(2, 6),
                        new Field(2, 7),
                        new Field(2, 8),
                        new Field(1, 2),
                        new Field(3, 2),
                        new Field(4, 2),
                        new Field(6, 2),
                        new Field(1, 1),
                        new Field(7, 2),
                        new Field(7, 7),
                        new Field(5, 2),
                        new Field(8, 2)
                )),
                Arguments.of(PieceType.KING, new Field(5, 8), List.of(
                        new Field(4, 8),
                        new Field(6, 8),
                        new Field(6, 7),
                        new Field(5, 7),
                        new Field(4, 7)
                ))
        );
    }

    @ParameterizedTest(name = "{0} on field {1} ")
    @MethodSource("attributeProvider")
    void getPossibleMoves(PieceType pieceType, Field field, List<Field> list) {
        PieceType pt = pieceType;
        Board board = new Board();
        ArrayList<Field> outList = pt.getPossibleMoves(board, field);
        ArrayList<Field> checkList = new ArrayList<>(list);

        Collections.sort(outList);
        Collections.sort(checkList);
        assertEquals(checkList, outList);
    }
}
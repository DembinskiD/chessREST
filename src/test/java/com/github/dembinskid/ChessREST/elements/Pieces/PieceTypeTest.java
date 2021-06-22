package com.github.dembinskid.ChessREST.elements.Pieces;

import com.github.dembinskid.ChessREST.elements.GameBoard.Board;
import com.github.dembinskid.ChessREST.elements.GameBoard.Field;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PieceTypeTest {

    private static Stream<Arguments> attributeProvider() {
        return Stream.of(
                Arguments.of(new Field(1,0), List.of(new Field(2,2), new Field(0,2), new Field(3,1))),
                Arguments.of(new Field(6,0), List.of(new Field(7,2), new Field(5,2), new Field(4,1))),
                Arguments.of(new Field(3,6), List.of(new Field(1,7),
                        new Field(1,5),
                        new Field(2,4),
                        new Field(4, 4),
                        new Field(5, 5),
                        new Field(5, 7)))
        );
    }

    @ParameterizedTest(name = "Field {0}")
    @MethodSource("attributeProvider")
    void getPossibleMoves(Field field, List<Field> list) {
        PieceType knight = PieceType.KNIGHT;
        Board board = new Board();
        ArrayList<Field> outList = knight.getPossibleMoves(board, field);
        ArrayList<Field> checkList = new ArrayList<>(list);

        Collections.sort(outList);
        Collections.sort(checkList);
        assertEquals(checkList, outList);
    }
}
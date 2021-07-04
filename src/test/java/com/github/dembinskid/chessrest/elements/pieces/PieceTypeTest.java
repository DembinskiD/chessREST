package com.github.dembinskid.chessrest.elements.pieces;

import com.github.dembinskid.chessrest.elements.gameboard.Board;
import com.github.dembinskid.chessrest.elements.gameboard.Field;
import org.assertj.core.util.Lists;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.dembinskid.chessrest.elements.pieces.PieceColor.BLACK;
import static com.github.dembinskid.chessrest.elements.pieces.PieceColor.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceTypeTest {

    private static Stream<Arguments> attributeProvider() {
        return Stream.of( //todo sprawdzić argumenty dla sprawdzanych pól, nie powinny występować wartości 0

                Arguments.of(PieceType.KNIGHT, BLACK, board.getFieldByCoordinates(2, 1), List.of(board.getFieldByCoordinates(3, 3), board.getFieldByCoordinates(1, 3), board.getFieldByCoordinates(4, 2))),
                Arguments.of(PieceType.KNIGHT, BLACK, board.getFieldByCoordinates(7, 1), List.of(board.getFieldByCoordinates(8, 3), board.getFieldByCoordinates(6, 3), board.getFieldByCoordinates(5, 2))),
                Arguments.of(PieceType.KNIGHT, BLACK, board.getFieldByCoordinates(4, 7),
                        List.of(
                                board.getFieldByCoordinates(2, 6),
                                board.getFieldByCoordinates(3, 5),
                                board.getFieldByCoordinates(5, 5),
                                board.getFieldByCoordinates(6, 6))),
                Arguments.of(PieceType.PAWN, WHITE, board.getFieldByCoordinates(1, 2), List.of(
                        board.getFieldByCoordinates(1, 3),
                        board.getFieldByCoordinates(1, 4)
                )),
                Arguments.of(PieceType.PAWN, BLACK, board.getFieldByCoordinates(6, 7), List.of(
                        board.getFieldByCoordinates(6, 6),
                        board.getFieldByCoordinates(6, 5)
                )),
                Arguments.of(PieceType.BISHOP, BLACK, board.getFieldByCoordinates(2, 8), Lists.emptyList()),
                Arguments.of(PieceType.ROOK, BLACK, board.getFieldByCoordinates(3, 5), List.of(
                        board.getFieldByCoordinates(1, 5),
                        board.getFieldByCoordinates(2, 5),
                        board.getFieldByCoordinates(4, 5),
                        board.getFieldByCoordinates(5, 5),
                        board.getFieldByCoordinates(6, 5),
                        board.getFieldByCoordinates(7, 5),
                        board.getFieldByCoordinates(8, 5),
                        board.getFieldByCoordinates(3, 2),
                        board.getFieldByCoordinates(3, 3),
                        board.getFieldByCoordinates(3, 4),
                        board.getFieldByCoordinates(3, 6)
                )),
                Arguments.of(PieceType.QUEEN, WHITE, board.getFieldByCoordinates(2, 2), List.of(
                        board.getFieldByCoordinates(3, 3),
                        board.getFieldByCoordinates(1, 3),
                        board.getFieldByCoordinates(4, 4),
                        board.getFieldByCoordinates(5, 5),
                        board.getFieldByCoordinates(6, 6),
                        board.getFieldByCoordinates(2, 3),
                        board.getFieldByCoordinates(2, 4),
                        board.getFieldByCoordinates(2, 5),
                        board.getFieldByCoordinates(2, 6),
                        board.getFieldByCoordinates(2, 7),
                        board.getFieldByCoordinates(7, 7)
                )),
                Arguments.of(PieceType.KING, BLACK, board.getFieldByCoordinates(5, 8), Lists.emptyList())
        );
    }

    static Board board = new Board();

    @ParameterizedTest(name = "{0} on field {2} ")
    @MethodSource("attributeProvider")
    void getPossibleMoves(PieceType pieceType, PieceColor pieceColor, Field field, List<Field> list) {
        board = new Board();
        Piece piece = new Piece(pieceType, pieceColor);
        field.setPiece(piece);
        field.setTaken(true);
        List<Field> outList = pieceType.getPossibleMoves(board, board.updateField(field.getX(), field.getY(), field));
        List<Field> checkList = list.stream()
                .map(field1 -> board.getFieldByCoordinates(field1.getX(), field1.getY()))
                .collect(Collectors.toList());

        Collections.sort(outList);
        Collections.sort(checkList);
        assertEquals(checkList, outList);
    }
}
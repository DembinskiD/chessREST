package com.github.dembinskid.ChessREST.elements.GameBoard;

import com.github.dembinskid.ChessREST.elements.Pieces.Piece;
import com.github.dembinskid.ChessREST.elements.Pieces.PieceColor;
import com.github.dembinskid.ChessREST.elements.Pieces.PieceType;
import com.sun.org.apache.xalan.internal.xsltc.dom.SingletonIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testAmountOfPawns() {
        this.fields.stream().filter(Field::isTaken).filter(x -> x.getPiece().getPieceType().equals(PieceType.PAWN)).forEach(this.outputList::add);
        assertEquals(16, this.outputList.size());
    }

    @Test
    void testAmountOfKings() {
        this.fields.stream().filter(Field::isTaken).filter(x -> x.getPiece().getPieceType().equals(PieceType.KING)).forEach(outputList::add);
        assertEquals(2, this.outputList.size());
    }

    @Test
    void testAmountOfQueens() {
        this.fields.stream().filter(Field::isTaken).filter(x -> x.getPiece().getPieceType().equals(PieceType.QUEEN)).forEach(outputList::add);
        assertEquals(2, this.outputList.size());
    }

    @Test
    void testAmountOfRooks() {
        this.fields.stream().filter(Field::isTaken).filter(x -> x.getPiece().getPieceType().equals(PieceType.ROOK)).forEach(outputList::add);
        assertEquals(4, this.outputList.size());
    }

    @Test
    void testAmountOfKnights() {
        this.fields.stream().filter(Field::isTaken).filter(x -> x.getPiece().getPieceType().equals(PieceType.KNIGHT)).forEach(outputList::add);
        assertEquals(4, this.outputList.size());
    }

    @Test
    void testAmountOfBishops() {
        this.fields.stream().filter(Field::isTaken).filter(x -> x.getPiece().getPieceType().equals(PieceType.BISHOP)).forEach(outputList::add);
        assertEquals(4, this.outputList.size());
    }

    @Test
    void testAmountOfWhitePieces() {
        this.fields.stream().filter(Field::isTaken).filter(x -> x.getPiece().getPieceColor().equals(PieceColor.WHITE)).forEach(outputList::add);
        assertEquals(16, this.outputList.size());
    }

    @Test
    void testAmountOfBlackPieces() {
        this.fields.stream().filter(Field::isTaken).filter(x -> x.getPiece().getPieceColor().equals(PieceColor.BLACK)).forEach(outputList::add);
        assertEquals(16, this.outputList.size());
    }


}
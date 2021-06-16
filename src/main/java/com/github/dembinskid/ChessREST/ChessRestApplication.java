package com.github.dembinskid.ChessREST;

import com.github.dembinskid.ChessREST.elements.GameBoard.Board;
import com.github.dembinskid.ChessREST.elements.Pieces.PieceType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChessRestApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ChessRestApplication.class, args);
//		Board board = new Board();
		PieceType type = PieceType.BISHOP;
		type.listPositions();
	}

}

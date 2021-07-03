package com.github.dembinskid.ChessREST;

import com.github.dembinskid.ChessREST.elements.GameBoard.Board;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChessRestApplication {

    public static void main(String[] args) {
//		SpringApplication.run(ChessRestApplication.class, args);
        Board board = new Board();
        board.printBoard();
/*		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.println(board.board[i][j].isFree());
			}
		}*/
    }

}

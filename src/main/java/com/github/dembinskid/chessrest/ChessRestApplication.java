package com.github.dembinskid.chessrest;

import com.github.dembinskid.chessrest.elements.gameboard.Board;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChessRestApplication {

    public static void main(String[] args) {
		SpringApplication.run(ChessRestApplication.class, args);
        Board board = new Board();
        board.printBoard();
    }

}

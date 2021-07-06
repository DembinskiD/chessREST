package com.github.dembinskid.chessrest.elements.game;

import com.github.dembinskid.chessrest.elements.Color;
import com.github.dembinskid.chessrest.elements.gameboard.Board;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class TurnTest {

    @Test
    void getPossibleMovesTest() {
        PlayerInGame player = new PlayerInGame("Davey", "testEmail");
        player.setPlayerColor(Color.WHITE);
        Turn turn = new Turn(player, UUID.randomUUID(), new Board());
        System.out.println(turn.getListOfPossibleMoves());
    }

}
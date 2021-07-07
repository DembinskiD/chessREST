package com.github.dembinskid.chessrest.elements.game;

import com.github.dembinskid.chessrest.elements.Color;
import com.github.dembinskid.chessrest.elements.gameboard.Board;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TurnTest {

    @Test
    void getPossibleMovesTest() {
        PlayerInGame player = new PlayerInGame("Davey", "testEmail");
        player.setPlayerColor(Color.WHITE);
        Turn turn = new Turn(0, player, UUID.randomUUID(), new Board());
        System.out.println(turn.getListOfPossibleMoves());
        assertEquals(20, turn.getListOfPossibleMoves().size());
    }

    @Test
    void testNextTurn() {
        PlayerInGame firstPlayer = new PlayerInGame("John");
        PlayerInGame secondPlayer = new PlayerInGame("Gary");
        Game game = new Game(firstPlayer, secondPlayer);
        game.endTurn(game.getPossibleMoves().get(10));
        assertEquals(game.getCurrentTurn().getPlayer(), game.getSecondPlayer());
    }

}
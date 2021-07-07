package com.github.dembinskid.chessrest.elements.game;

import com.github.dembinskid.chessrest.elements.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PlayerInGame extends Player {
    UUID gameId;
    Color playerColor;

    public PlayerInGame(String name, String emailAddress) {
        super(name, emailAddress);
    }

    public PlayerInGame(String name) {
        super(name, "");
    }

    public void setColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public void setGameUUID(UUID gameId) {
        this.gameId = gameId;
    }


}

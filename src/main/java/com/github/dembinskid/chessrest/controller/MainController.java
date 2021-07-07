package com.github.dembinskid.chessrest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.github.dembinskid.chessrest.elements.game.Game;
import com.github.dembinskid.chessrest.elements.game.PlayerInGame;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController{

    @GetMapping(value = "/hi", produces = "text/plain")
    public String returnHi() throws JsonProcessingException {
        var mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        var game = new Game(new PlayerInGame("d"), new PlayerInGame("a"));
        var filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("playerFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("name", "playerColor"));
        mapper.setFilterProvider(filterProvider);
        return mapper.writeValueAsString(game);
    }
}

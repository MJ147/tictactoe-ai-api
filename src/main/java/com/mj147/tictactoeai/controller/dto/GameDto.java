package com.mj147.tictactoeai.controller.dto;

import com.mj147.tictactoeai.domain.Game;
import com.mj147.tictactoeai.domain.Player;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameDto {

    private Long id;
    private BoardDto board;
    private List<PlayerDto> players = new ArrayList<>();

    public GameDto(Game game) {
        this.id = game.getId();
        this.board = new BoardDto(game.getBoard());
        for (Player player : game.getPlayers()) {
            this.players.add(new PlayerDto(player));
        }
    }
}

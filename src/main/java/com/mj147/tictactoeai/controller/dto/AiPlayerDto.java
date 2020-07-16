package com.mj147.tictactoeai.controller.dto;

import com.mj147.tictactoeai.domain.AiPlayer;
import com.mj147.tictactoeai.domain.Move;
import com.mj147.tictactoeai.domain.Player;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class AiPlayerDto {
    private Long id;
    private Integer value;

    public AiPlayerDto(AiPlayer aiPlayer) {
        this.id = aiPlayer.getId();
        this.value = aiPlayer.getValue();
    }
}

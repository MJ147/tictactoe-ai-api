package com.mj147.tictactoeai.controller.dto;

import com.mj147.tictactoeai.domain.Player;
import lombok.Data;

@Data
public class PlayerDto {
    private Long id;
    private Integer value;

    public PlayerDto(Player player) {
        this.id = player.getId();
        this.value = player.getValue();
    }
}

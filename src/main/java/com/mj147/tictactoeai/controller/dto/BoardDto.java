package com.mj147.tictactoeai.controller.dto;

import com.mj147.tictactoeai.domain.Board;
import lombok.Data;

import java.util.List;

@Data
public class BoardDto {

    private Long id;
    private List<Integer> squares;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.squares = board.getSquares();
    }
}

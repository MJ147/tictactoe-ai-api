package com.mj147.tictactoeai.controller.dto;

import com.mj147.tictactoeai.domain.Board;
import com.mj147.tictactoeai.domain.Square;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardDto {

    private Long id;
    private List<SquareDto> squares = new ArrayList<>();

    public BoardDto(Board board) {
        this.id = board.getId();
        for (Square square : board.getSquares()) {
            this.squares.add(new SquareDto(square));
        }
    }
}

package com.mj147.tictactoeai.controller.dto;

import com.mj147.tictactoeai.domain.Square;
import lombok.Data;

@Data
public class SquareDto {
    private Long id;
    private int value;
    private int numberInBoard;

    public SquareDto(Square square) {
        this.id = square.getId();
        this.value = square.getValue();
        this.numberInBoard = square.getNumberInBoard();
    }
}

package com.mj147.tictactoeai.controller.dto;

import com.mj147.tictactoeai.domain.Board;

import java.util.List;

public class BoardDto {

    private Long id;
    private List<Integer> fields;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.fields = board.getFields();
    }
}

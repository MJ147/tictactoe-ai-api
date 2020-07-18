package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Board;

import java.util.List;

public interface BoardService {

    Board resetBoard(Long boardId);

    Board createBoard(Board board);

    Board getBoard(Long id);

    Integer checkBoard(Board board);
}

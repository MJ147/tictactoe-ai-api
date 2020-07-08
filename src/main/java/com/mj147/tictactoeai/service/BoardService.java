package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Board;

import java.util.List;

public interface BoardService {

    Board updateBoard(Board board);

    Board createBoard(Board board);

    Board getBoard(Long id);
}

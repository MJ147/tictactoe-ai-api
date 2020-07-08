package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Board;
import com.mj147.tictactoeai.domain.Square;

import java.util.List;

public interface SquareService {

    Square createSquare(Square square);

    Square updateSquare(Square square);

    Square findById(Long id);

    Integer countAllByBoardIdAndAndValue(Long boardId, Integer value);

}

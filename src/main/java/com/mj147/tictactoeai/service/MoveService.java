package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Move;

import java.util.List;

public interface MoveService {

    Move findMove(String symbol);

    Move createMove(String symbol, Integer number);

    Move changeUpdateStatus(Long moveId, boolean isUpdate);

    Move update(Move move);

    Boolean existsMoveByBoardSymbolAndSquareNumber(String symbol, Integer squareNumber);

    List<Move> findAllByUpdate(Boolean update);
}

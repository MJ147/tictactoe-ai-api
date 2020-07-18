package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Move;

import java.util.List;

public interface MoveService {

    List<Move> findMovesWithBiggestFactor(String symbol);

    List<Move> findMovesByBoardSymbol(String symbol);

    Move createMove(String symbol, Integer number, Integer moveValue);

    Move update(Move move);

    Boolean existsMoveByBoardSymbolAndSquareNumber(String symbol, Integer squareNumber);

    List<Move> findAllByUpdate(Boolean update);
}

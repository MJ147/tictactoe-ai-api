package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Move;

import java.util.List;
import java.util.Set;

public interface MoveService {

    List<Move> findMovesWithBiggestFactor(Set<String> boardSymbols);

    List<Move> findMovesByBoardSymbols(Set<String> boardSymbols);

    List<Move> findMovesByBoardSymbolsAndFactor(Set<String> boardSymbols, Integer line);

    Long countMoveByFactorGreaterThan(Integer line);

    Move createMove(String boardSymbol, Integer moveValue);

    Move update(Move move);

    void deleteAll();

    Boolean isBoardSymbolExists(String symbol);

    List<Move> findAllByUpdate(Boolean update);

    void saveMovesToCsv(String fileName);

    Long loadMovesFromCsv(String fileName);
}

package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Move;
import com.mj147.tictactoeai.repository.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MoveServiceImpl implements MoveService {

    @Autowired
    MoveRepository moveRepository;

    @Override
    public List<Move> findMovesWithBiggestFactor(Set<String> boardSymbols) {
        return moveRepository.findMovesWithBiggestFactor(boardSymbols);
    }

    @Override
    public List<Move> findMovesByBoardSymbols(Set<String> boardSymbols){
        return moveRepository.findMovesByBoardSymbols(boardSymbols);
    }

    @Override
    public List<Move> findMovesByBoardSymbolsAndFactor(Set<String> boardSymbols, Integer line){
        return moveRepository.findMovesByBoardSymbolsAndFactor(boardSymbols, line);
    }

    @Override
    public Move createMove(String boardSymbol, Integer moveValue) {
       Move move = new Move();
       move.setBoardSymbol(boardSymbol);
       move.setMoveValue(moveValue);
       move.setUpdate(false);

       return moveRepository.save(move);
    }

    @Override
    public Move update(Move move){
        return moveRepository.save(move);
    }

    @Override
    public Boolean isBoardSymbolExists(String boardSymbol) {
        return moveRepository.existsMoveByBoardSymbol(boardSymbol);
    }

    @Override
    public List<Move> findAllByUpdate(Boolean update) {
        return moveRepository.findAllByUpdate(update);
    }
}

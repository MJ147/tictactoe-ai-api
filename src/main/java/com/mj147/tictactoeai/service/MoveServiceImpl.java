package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Move;
import com.mj147.tictactoeai.exception.EntityDoesNotExistException;
import com.mj147.tictactoeai.repository.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoveServiceImpl implements MoveService {

    @Autowired
    MoveRepository moveRepository;


    @Override
    public Move findMove(String symbol) {
        return moveRepository.findMax(symbol);
    }

    @Override
    public Move createMove(String symbol, Integer number) {
       Move move = new Move();
       move.setBoardSymbol(symbol);
       move.setSquareNumber(number);
       move.setUpdate(false);

       return moveRepository.save(move);
    }

    @Override
    public Move changeUpdateStatus(Long moveId, boolean isUpdate){
        Move move = moveRepository.findById(moveId)
                .orElseThrow( () -> new EntityDoesNotExistException("Move id: " + moveId + " not found"));
        move.setUpdate(isUpdate);
        return moveRepository.save(move);
    }

    @Override
    public Move update(Move move){
        return moveRepository.save(move);
    }

    @Override
    public Boolean existsMoveByBoardSymbolAndSquareNumber(String symbol, Integer squareNumber) {
        return moveRepository.existsMoveByBoardSymbolAndSquareNumber(symbol, squareNumber);
    }

    @Override
    public List<Move> findAllByUpdate(Boolean update) {
        return moveRepository.findAllByUpdate(update);
    }
}
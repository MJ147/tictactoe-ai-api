package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Move;
import com.mj147.tictactoeai.repository.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.mj147.tictactoeai.utils.CsvUtils.covertCsvToModel;
import static com.mj147.tictactoeai.utils.CsvUtils.covertModelToCsv;

@Service
public class MoveServiceImpl implements MoveService {

    @Autowired
    MoveRepository moveRepository;

    @Override
    public List<Move> findMovesWithBiggestFactor(Set<String> boardSymbols) {
        return moveRepository.findMovesWithBiggestFactor(boardSymbols);
    }

    @Override
    public List<Move> findMovesByBoardSymbols(Set<String> boardSymbols) {
        return moveRepository.findMovesByBoardSymbols(boardSymbols);
    }

    @Override
    public List<Move> findMovesByBoardSymbolsAndFactor(Set<String> boardSymbols, Integer line) {
        return moveRepository.findMovesByBoardSymbolsAndFactor(boardSymbols, line);
    }

    @Override
    public Long countMoveByFactorGreaterThan(Integer line) {
        return moveRepository.countMoveByFactorGreaterThan(line);
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
    public Move update(Move move) {
        return moveRepository.save(move);
    }

    @Override
    public void deleteAll() {
        moveRepository.deleteAll();
    }

    @Override
    public Boolean isBoardSymbolExists(String boardSymbol) {
        return moveRepository.existsMoveByBoardSymbol(boardSymbol);
    }

    @Override
    public List<Move> findAllByUpdate(Boolean update) {
        return moveRepository.findAllByUpdate(update);
    }

    @Override
    public void saveMovesToCsv(String fileName) {
        List<Move> moves = (List<Move>) moveRepository.findAll();
        try {
            covertModelToCsv(moves, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long loadMovesFromCsv(String fileName) {
        moveRepository.deleteAll();
        List<Move> moves = new ArrayList<>();
        try {
            moves = covertCsvToModel(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        moveRepository.saveAll(moves);

        return moveRepository.count();
    }


}

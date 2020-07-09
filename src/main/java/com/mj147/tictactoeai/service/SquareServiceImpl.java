package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Square;

import com.mj147.tictactoeai.exception.EntityDoesNotExistException;
import com.mj147.tictactoeai.repository.SquareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SquareServiceImpl implements SquareService {

    @Autowired
    SquareRepository squareRepository;

    @Override
    public Square createSquare(Square square) {
        return squareRepository.save(square);
    }

    @Override
    public Square updateSquare(Square square) {
        return squareRepository.save(square);
    }

    @Override
    public Square findById(Long id) {
        return squareRepository.findById(id).orElseThrow(
                () -> new EntityDoesNotExistException("Square id: " + id + " not found")
        );
    }

    @Override
    public Integer countAllByBoardIdAndAndValue(Long boardId, Integer value) {
        return squareRepository.countAllByBoardIdAndValue(boardId, value);
    }

}

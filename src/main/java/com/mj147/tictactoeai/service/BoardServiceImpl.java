package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Board;
import com.mj147.tictactoeai.domain.Square;
import com.mj147.tictactoeai.exception.EntityDoesNotExistException;
import com.mj147.tictactoeai.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    SquareService squareService;

    @Override
    public Board createBoard(Board board) {
        boardRepository.save(board);
        board.setSquares(createSquares(board));

        return board;
    }

    @Override
    public Board getBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Board id: " + id + " not found"));
    }

    @Override
    public Board resetBoard(Long boardId) {
        Board board = getBoard(boardId);
        resetSquares(board);

        return board;
    }

    private void resetSquares(Board board) {
        for (int i = 0; i < board.getSize(); i++) {
            Square square = board.getSquares().get(i);
            square.setValue(0);
            squareService.updateSquare(square);
        }
    }

    private List<Square> createSquares(Board board) {
        List<Square> squares = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            Square square = new Square();
            square.setBoard(board);
            square.setNumberInBoard(i);
            squares.add(i, square);
            squareService.createSquare(square);
        }
        return squares;
    }

}

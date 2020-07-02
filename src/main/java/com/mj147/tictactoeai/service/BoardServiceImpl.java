package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Board;
import com.mj147.tictactoeai.exception.EntityDoesNotExistException;
import com.mj147.tictactoeai.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board createBoard() {
        Board board = new Board();
        board.setSquares(createClearBoardSquares(board.getSize()));
        boardRepository.save(board);

        return board;
    }

    @Override
    public Board getBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Board id: " + id + "not found"));
    }

    @Override
    public Board updateBoard(Board board) {
        boardRepository.save(board);
        if (checkIfWon(board.getSquares()) != 0) {
            //win
        }

        return board;
    }

    private List<Integer> createClearBoardSquares(int boardSize) {
        List<Integer> squares = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            squares.add(i, 0);
        }
        return squares;
    }

    private Integer checkIfWon (List<Integer> squares) {
        int result = checkFromCenterSquare(squares);
        return (result != 0) ? result : checkBoardOutline(squares);
    }

    //check if someone won starting from center square
    private Integer checkFromCenterSquare(List<Integer> squares) {
        int centerSquare = squares.get(4);
        if (centerSquare != 0) {
            for (int i = 0; i < 4 ; i++) {
                if (squares.get(i) == centerSquare &&
                    squares.get(i) == checkOppositeSquare(squares.get(i))) {
                    return centerSquare;
                }
            }
        }
        return 0;
    }

    //check if someone won in board outline
    //renumbering of squares for easier finding
    private Integer checkBoardOutline(List<Integer> squares) {
        List<Integer> squaresInNewOrder = changeOrderOfSquares(squares);
        int counter = 0;
        for (int i = 1; i < squaresInNewOrder.size(); i++) {
            if (squaresInNewOrder.get(i) != 0 &&
                    squaresInNewOrder.get(i) == squaresInNewOrder.get(i - 1)) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == 2) {
                return squaresInNewOrder.get(i);
            }
            if (i % 2 == 0) {
                counter = 0;
            }
        }
        return 0;
    }

    private List<Integer> changeOrderOfSquares(List<Integer> squares) {
        List<Integer> squaresInNewOrder = new ArrayList<>();
        squaresInNewOrder.add(0, squares.get(0));
        squaresInNewOrder.add(1, squares.get(1));
        squaresInNewOrder.add(2, squares.get(2));
        squaresInNewOrder.add(3, squares.get(5));
        squaresInNewOrder.add(4, squares.get(8));
        squaresInNewOrder.add(5, squares.get(7));
        squaresInNewOrder.add(6, squares.get(6));
        squaresInNewOrder.add(7, squares.get(5));
        squaresInNewOrder.add(8, squares.get(0));

        return squaresInNewOrder;

    }

    private Integer checkOppositeSquare(Integer square) {
        return 8 - square;
    }

}

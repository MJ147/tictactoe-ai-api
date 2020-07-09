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

    private Integer checkIfWon (List<Square> squares) {
        int result = checkFromCenterSquare(squares);
        return (result != 0) ? result : checkBoardOutline(squares);
    }

    //check if someone won starting from center square
    private Integer checkFromCenterSquare(List<Square> squares) {
        int centerSquareValue = squares.get(4).getValue();
        if (centerSquareValue != 0) {
            for (int i = 0; i < 4 ; i++) {
                if (squares.get(i).getValue() == centerSquareValue &&
                    squares.get(i).getValue() == checkOppositeSquare(squares.get(i))) {
                    return centerSquareValue;
                }
            }
        }
        return 0;
    }

    //check if someone won in board outline
    //renumbering of squares for easier finding
    private Integer checkBoardOutline(List<Square> squares) {
        List<Square> squaresInNewOrder = changeOrderOfSquares(squares);
        int counter = 0;
        for (int i = 1; i < squaresInNewOrder.size(); i++) {
            if (squaresInNewOrder.get(i).getValue() != 0 &&
                    squaresInNewOrder.get(i) == squaresInNewOrder.get(i - 1)) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == 2) {
                return squaresInNewOrder.get(i).getValue();
            }
            if (i % 2 == 0) {
                counter = 0;
            }
        }
        return 0;
    }

    private List<Square> changeOrderOfSquares(List<Square> squares) {
        List<Square> squaresInNewOrder = new ArrayList<>();
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

    private Integer checkOppositeSquare(Square square) {
        return 8 - square.getValue();
    }

}

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
        board.setFields(createClearBoardFields(board.getSize()));
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
        if (checkIfWon(board.getFields()) != 0) {
            //win
        }

        return board;
    }

    private List<Integer> createClearBoardFields(int boardSize) {
        List<Integer> fields = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            fields.set(i, 0);
        }
        return fields;
    }

    private Integer checkIfWon (List<Integer> fields) {
        int result = checkFromCenterField(fields);
        return (result != 0) ? result : checkBoardOutline(fields);
    }

    //check if someone won starting from center field
    private Integer checkFromCenterField(List<Integer> fields) {
        int centerField = fields.get(4);
        if (centerField != 0) {
            for (int i = 0; i < 4 ; i++) {
                if (fields.get(i) == centerField &&
                    fields.get(i) == checkOppositeField(fields.get(i))) {
                    return centerField;
                }
            }
        }
        return 0;
    }

    //check if someone won in board outline
    //renumbering of fields for easier finding
    private Integer checkBoardOutline(List<Integer> fields) {
        List<Integer> fieldsInNewOrder = changeOrderOfFields(fields);
        int counter = 0;
        for (int i = 1; i < fieldsInNewOrder.size(); i++) {
            if (fieldsInNewOrder.get(i) != 0 &&
                    fieldsInNewOrder.get(i) == fieldsInNewOrder.get(i - 1)) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == 2) {
                return fieldsInNewOrder.get(i);
            }
            if (i % 2 == 0) {
                counter = 0;
            }
        }
        return 0;
    }

    private List<Integer> changeOrderOfFields(List<Integer> fields) {
        List<Integer> fieldsInNewOrder = new ArrayList<>();
        fieldsInNewOrder.set(0, fields.get(0));
        fieldsInNewOrder.set(1, fields.get(1));
        fieldsInNewOrder.set(2, fields.get(2));
        fieldsInNewOrder.set(3, fields.get(5));
        fieldsInNewOrder.set(4, fields.get(8));
        fieldsInNewOrder.set(5, fields.get(7));
        fieldsInNewOrder.set(6, fields.get(6));
        fieldsInNewOrder.set(7, fields.get(5));
        fieldsInNewOrder.set(8, fields.get(0));

        return fieldsInNewOrder;

    }

    private Integer checkOppositeField(Integer field) {
        return 8 - field;
    }

}

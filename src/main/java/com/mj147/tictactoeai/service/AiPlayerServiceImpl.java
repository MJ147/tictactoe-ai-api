package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.*;
import com.mj147.tictactoeai.repository.AiPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AiPlayerServiceImpl implements AiPlayerService{

    @Autowired
    AiPlayerRepository aiPlayerRepository;
    @Autowired
    SquareService squareService;
    @Autowired
    MoveService moveService;

    Random random = new Random();


    @Override
    public AiPlayer createAiPlayer(int squareValue) {
        AiPlayer aiPlayer = new AiPlayer();
        aiPlayer.setValue(squareValue);
        aiPlayerRepository.save(aiPlayer);

        return aiPlayer;
    }

    @Override
    public Square makeMove(Board board, AiPlayer player) {
        Square square;
        Move move;
        String boardSymbol = convertBoardToString(board);
        move = randomMove(board, boardSymbol, player);
        move = (move != null) ? move : findBestMove(boardSymbol);
        square = board.getSquares().get(move.getSquareNumber());
        return square;
    }

    @Override
    public void updateFactors(Player player, Integer checkIfWon) {
        int changeFactor;
        List<Move> moves = moveService.findAllByUpdate(false);
        for (Move move : moves) {
            changeFactor = convertResultToPoints(checkIfWon, move.getMoveValue());
            Long newFactor = move.getFactor() + changeFactor;
            move.setFactor(newFactor < 20 ? newFactor : 20);
            move.setUpdate(true);
            moveService.update(move);
        }
    }

    private int convertResultToPoints(Integer checkIfWon, Integer moveValue) {
        if (checkIfWon == 3) return 0;
        if (checkIfWon == 1 || checkIfWon == 2) {
            if (checkIfWon == moveValue) {
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }

    private Move findBestMove(String boardSymbol) {
        List<Move> moves;
        Move move;
        if ("000000000".equals(boardSymbol)) {
            moves = moveService.findMovesByBoardSymbol(boardSymbol);
            move = moves.get(random.nextInt(moves.size()));
        } else {
            moves = moveService.findMovesWithBiggestFactor(boardSymbol);
            move = moves.get(random.nextInt(moves.size()));
        }
        System.out.println(move);
        move.setUpdate(false);
        moveService.update(move);
        return move;
    }

    public Move randomMove(Board board, String boardSymbol, AiPlayer player) {
        List<Square> squares = board.getSquares();
        int randomSquareNumber = random.nextInt(9);
        int squareNumber = randomSquareNumber;
        while(squares.get(squareNumber).getValue() != 0 ||
              !isMoveInBase(boardSymbol, squareNumber)) {
            if (squareNumber < 8) {
                squareNumber++;
            } else {
                squareNumber = 0;
            }
            if(squareNumber == randomSquareNumber) {
                return null;
            }
        }
        Move move = moveService.createMove(boardSymbol, squareNumber, player.getValue());
        return move;
    }

    public Boolean isMoveInBase(String boardSymbol, Integer squareNumber) {
        return !moveService.existsMoveByBoardSymbolAndSquareNumber(boardSymbol, squareNumber);
    }

    private String convertBoardToString(Board board) {
        List<Square> squares = board.getSquares();
        String stringSquares = squares.stream()
                .map( s -> Integer.toString(s.getValue()))
                .collect(Collectors.joining());
        return stringSquares;
    }

}

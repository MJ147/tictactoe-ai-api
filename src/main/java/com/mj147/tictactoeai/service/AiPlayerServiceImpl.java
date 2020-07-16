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
    public Square makeMove(Board board, AiPlayer player2) {
        Square square;
        Move move;
        String boardSymbol = convertBoardToString(board);
        move = randomMove(board, boardSymbol, player2);
        move = (move != null) ? move : findBestMove(boardSymbol);
        square = board.getSquares().get(move.getSquareNumber());
        System.out.println(move);
        return square;
    }

    @Override
    public void updateFactors(Player player2, Integer checkIfWon) {
        int changeFactor = convertResultToPoints(checkIfWon, player2.getValue());
        List<Move> moves = moveService.findAllByUpdate(false);
        for (Move move : moves) {
            System.out.println("------------>>>>" + move.getFactor() + changeFactor);
            move.setFactor(move.getFactor() + changeFactor);
            move.setUpdate(true);
            moveService.update(move);
        }
    }

    private int convertResultToPoints(Integer checkIfWon, Integer playerValue) {
        if (checkIfWon == 3) return 1;
        if (checkIfWon == 0) return 0;
        if (checkIfWon == 1 || checkIfWon == 2) {
            if (checkIfWon.equals(playerValue)) {
                return 3;
            } else {
                return -1;
            }
        }
        return -100;
    }


    private Move findBestMove(String boardSymbol) {
        Move move = moveService.findMove(boardSymbol);
        moveService.changeUpdateStatus(move.getId(), false);
        return move;
    }

    public Move randomMove(Board board, String boardSymbol, AiPlayer player2) {
        List<Square> squares = board.getSquares();
        int randomSquareNumber = random.nextInt(9);
        int squareNumber = randomSquareNumber;
        while(squares.get(squareNumber).getValue() != 0 ||
              !isSquareNumberIsAvailableForNewMove(boardSymbol, squareNumber)) {
            if (squareNumber < 8) {
                squareNumber++;
            } else {
                squareNumber = 0;
            }
            if(squareNumber == randomSquareNumber) {
                return null;
            }
        }
        Move move = moveService.createMove(boardSymbol, squareNumber);
        player2.setMoves(move);

        return move;
    }

    public Boolean isSquareNumberIsAvailableForNewMove(String boardSymbol, Integer squareNumber) {
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

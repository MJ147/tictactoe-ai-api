package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.*;
import com.mj147.tictactoeai.repository.AiPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.mj147.tictactoeai.utils.MoveConverter.*;

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
    public Square makeRandomMove(Board board, AiPlayer player) {
        List<Square> squares = board.getSquares();
        int squareNumber = random.nextInt(9);
        while (squares.get(squareNumber).getValue() != 0) {
            if (squareNumber < 8) {
                squareNumber++;
            } else {
                squareNumber = 0;
            }
        }
        return squares.get(squareNumber);
    }

    @Override
    public Square makeMove(Board board, AiPlayer player) {
        Square square;
        String boardSymbol = convertBoardToString(board);
        int squareNumber = newMove(board, boardSymbol, player);
        squareNumber = (squareNumber != -1) ? squareNumber : findBestMove(boardSymbol, player);
        square = board.getSquares().get(squareNumber);
        return square;
    }

    @Override
    public void updateFactors(Player player, Integer checkIfWon) {
        int changeFactor;
        List<Move> moves = moveService.findAllByUpdate(false);
        for (Move move : moves) {
            int multiply = 8 - countZeros(move.getBoardSymbol());
            changeFactor = convertResultToPoints(checkIfWon, move.getMoveValue()) * multiply;
            Integer newFactor = move.getFactor() + changeFactor;
            newFactor = newFactor < 500 ? newFactor : 500;
            newFactor = newFactor > -500 ? newFactor : -500;
            move.setFactor(newFactor);
            move.setUpdate(true);
            moveService.update(move);
        }
    }

    private Integer findBestMove(String boardSymbol, AiPlayer player) {
        Map<String, Integer> boardSymbols = new HashMap<>();
        Map<Integer, Integer> lines = createLines();
        for (int i = 0; i < 9; i++) {
            if ('0' == boardSymbol.charAt(i)) {
                boardSymbols.put(convertMove(addSquareToBoard(boardSymbol, i, player.getValue())), i);
            }
        }
        List<Move> moves = moveService.findMovesWithBiggestFactor(boardSymbols.keySet());
        Move move;
        int randomNumber = random.nextInt(501 - moves.get(0).getFactor());
        int randomLines = random.nextInt(27);
        if ("000000000".equals(boardSymbol) || randomNumber != 0) {
            moves = moveService.findMovesByBoardSymbolsAndFactor(boardSymbols.keySet(), lines.get(randomLines));
            if (moves.isEmpty()) {
                moves = moveService.findMovesByBoardSymbols(boardSymbols.keySet());
            }
        }
        move = moves.get(random.nextInt(moves.size()));
        move.setUpdate(false);
        moveService.update(move);
        return boardSymbols.get(move.getBoardSymbol());
    }

    public Integer newMove(Board board, String boardSymbol, AiPlayer player) {
        List<Square> squares = board.getSquares();
        int randomSquareNumber = random.nextInt(9);
        int squareNumber = randomSquareNumber;
        String convertedBoardSymbol;
        do {
            if (squares.get(squareNumber).getValue() == 0) {
                convertedBoardSymbol = convertMove(addSquareToBoard(boardSymbol, squareNumber, player.getValue()));
                if (!isMoveInBase(convertedBoardSymbol)) {
                    moveService.createMove(convertedBoardSymbol, player.getValue());
                    return squareNumber;
                }
            }
            if (squareNumber < 8) {
                squareNumber++;
            } else {
                squareNumber = 0;
            }
        } while (squareNumber != randomSquareNumber);

        return -1;
    }

    public Boolean isMoveInBase(String boardSymbol) {
        return moveService.isBoardSymbolExists(boardSymbol);
    }

    private Map<Integer, Integer> createLines() {
        return new HashMap<Integer, Integer>(){{
            put(1, 450);
            put(2, 450);
            put(3, 450);
            put(4, 450);
            put(5, 450);
            put(6, 450);
            put(7, 400);
            put(8, 400);
            put(9, 400);
            put(10, 400);
            put(11, 400);
            put(12, 400);
            put(13, 400);
            put(14, 300);
            put(15, 300);
            put(16, 300);
            put(17, 300);
            put(18, 200);
            put(19, 200);
            put(20, 200);
            put(21, 200);
            put(22, 100);
            put(23, 100);
            put(24, 0);
            put(25, -100);
            put(26, -200);
        }};
    }


}

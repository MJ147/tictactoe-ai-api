package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Board;
import com.mj147.tictactoeai.domain.Player;
import com.mj147.tictactoeai.domain.Square;
import com.mj147.tictactoeai.exception.SquareIsAlreadyTakenException;
import com.mj147.tictactoeai.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    BoardService boardService;
    @Autowired
    SquareService squareService;

    private int squareValue = 1;

    @Override
    public Player createPlayer() {
        Player player = new Player();
        player.setValue(squareValue);
        playerRepository.save(player);
        squareValue = (squareValue == 1) ? 2 : 1;

        return player;
    }

    @Override
    public Square makeMove(Long squareId) {
        Square square = squareService.findById(squareId);
        isSquareFree(square);
        Player player = whoseTurn(square);
        square.setValue(player.getValue());

        return squareService.updateSquare(square);
    }

    private void isSquareFree(Square square) {
        if (square.getValue() != 0) {
            throw new SquareIsAlreadyTakenException("Square: " + square.getNumberInBoard() + " is already taken");
        }
    }

    private Player whoseTurn(Square square) {
        Long boardId = square.getBoard().getId();
        List<Player> players = square.getBoard().getGame().getPlayers();
        int numberOfFreeSquares = squareService.countAllByBoardIdAndAndValue(boardId, 0);
        return numberOfFreeSquares % 2 == 1 ? players.get(0) : players.get(1);
    }


}

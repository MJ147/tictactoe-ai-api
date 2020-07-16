package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Player;
import com.mj147.tictactoeai.domain.Square;
import com.mj147.tictactoeai.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    SquareService squareService;

    @Override
    public Player createPlayer(int squareValue) {
        Player player = new Player();
        player.setValue(squareValue);
        playerRepository.save(player);

        return player;
    }

    @Override
    public Player whoseTurn(Square square) {
        Long boardId = square.getBoard().getId();
        List<Player> players = square.getBoard().getGame().getPlayers();
        int numberOfFreeSquares = squareService.countAllByBoardIdAndAndValue(boardId, 0);
        return numberOfFreeSquares % 2 == 1 ? players.get(0) : players.get(1);
    }

}

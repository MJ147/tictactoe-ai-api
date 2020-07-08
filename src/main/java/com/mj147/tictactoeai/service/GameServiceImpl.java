package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Board;
import com.mj147.tictactoeai.domain.Game;
import com.mj147.tictactoeai.domain.Player;
import com.mj147.tictactoeai.exception.EntityDoesNotExistException;
import com.mj147.tictactoeai.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    GameRepository gameRepository;
    @Autowired
    BoardService boardService;
    @Autowired
    PlayerService playerService;

    @Override
    public Game createGame() {
        Game game = new Game();
        gameRepository.save(game);
        Player player1 = playerService.createPlayer();
        Player player2 = playerService.createPlayer();
        game.setPlayers(Arrays.asList(player1, player2));
        Board board = new Board();
        board.setGame(game);
        game.setBoard(board);
        boardService.createBoard(board);

        return game;
    }

    @Override
    public Game getGame(Long id) {
        return gameRepository.findById(id)
                .orElseThrow( () -> new EntityDoesNotExistException("Game id: " + id + " not found"));
    }

}

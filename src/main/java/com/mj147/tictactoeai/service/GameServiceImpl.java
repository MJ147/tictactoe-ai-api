package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.*;
import com.mj147.tictactoeai.exception.EntityDoesNotExistException;
import com.mj147.tictactoeai.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    BoardService boardService;
    @Autowired
    PlayerService playerService;
    @Autowired
    AiPlayerService aiPlayerService;
    @Autowired
    SquareService squareService;
    @Autowired
    MoveService moveService;

    @Override
    public Game createGame() {
        Game game = new Game();
        gameRepository.save(game);
        Player player1 = playerService.createPlayer(1);
        AiPlayer player2 = aiPlayerService.createAiPlayer(2);
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
                .orElseThrow(() -> new EntityDoesNotExistException("Game id: " + id + " not found"));
    }

    @Override
    public void removeGame(Long id) {
        getGame(id);
        gameRepository.removeById(id);
    }

    @Override
    public Board resetBoard(Long boardId) {
        Board board = boardService.resetBoard(boardId);
        List<Move> moves = moveService.findAllByUpdate(false);
        for (Move move : moves) {
            move.setUpdate(true);
            moveService.update(move);
        }
        return board;
    }

    @Override
    public Board makeMove(Long squareId) {
        Square square = squareService.findById(squareId);
        squareService.isSquareFree(square);
        Player player = playerService.whoseTurn(square);
        square.setValue(player.getValue());
        squareService.updateSquare(square);
        Player player2 = playerService.whoseTurn(square);
        Board board = square.getBoard();
        if (player2 instanceof AiPlayer && checkIfWon(board.getId()) == 0) {
            Square square2 = aiPlayerService.makeMove(board, (AiPlayer) player2);
            square2.setValue(player2.getValue());
            squareService.updateSquare(square);
        }
        if (checkIfWon(board.getId()) != 0) {
            aiPlayerService.updateFactors(player2, checkIfWon(board.getId()));
        }

        return square.getBoard();
    }

    @Override
    public Integer checkIfWon(Long boardId) {
        Board board = boardService.getBoard(boardId);
        return boardService.checkBoard(board);
    }

    @Override
    public void learnAi(Long gameId, Long numberOfGames) {
        Game game = this.getGame(gameId);
        AiPlayer player = (AiPlayer) game.getPlayers().stream()
                .filter(p -> p instanceof AiPlayer)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        Board board = game.getBoard();
        boardService.resetBoard(board.getId());
        simulateGames(board, player, numberOfGames);
    }

    private void simulateGames(Board board, AiPlayer player, Long numberOfGames) {
        for (int i = 0; i < numberOfGames; i++) {
            simulateOneGame(board, player);
        }
    }

    @Transactional
    public void simulateOneGame(Board board, AiPlayer player) {
        while (checkIfWon(board.getId()) == 0) {

            simulateOneMove(board, player);
        }
        player.setValue(2);
        aiPlayerService.updateFactors(player, checkIfWon(board.getId()));
        boardService.resetBoard(board.getId());
    }

    private void simulateOneMove(Board board, AiPlayer player) {
        player.setValue(player.getValue() == 1 ? 2 : 1);
        Square square = aiPlayerService.makeMove(board, player);
        square.setValue(player.getValue());
        squareService.updateSquare(square);
    }

}

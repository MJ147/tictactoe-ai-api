package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Board;
import com.mj147.tictactoeai.domain.Game;

public interface GameService {

    Game createGame();

    Game getGame(Long id);

    void removeGame(Long id);

    Board resetBoard(Long boardId);

    Board makeMove(Long squareId, Boolean isAiPlayer);

    Integer checkIfWon(Long boardId);

    void learnAi(Long gameId, Long numberOfGames);

}

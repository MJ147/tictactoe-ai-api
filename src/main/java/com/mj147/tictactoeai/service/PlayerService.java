package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Board;
import com.mj147.tictactoeai.domain.Player;
import com.mj147.tictactoeai.domain.Square;

public interface PlayerService {

    Player createPlayer(int squareValue);

    Player whoseTurn(Square square);

    Square makeRandomMove(Board board);

    Square makeMove(Board board, Player player);

    void updateFactors(Player player, Integer checkIfWon);
}

package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.AiPlayer;
import com.mj147.tictactoeai.domain.Board;
import com.mj147.tictactoeai.domain.Player;
import com.mj147.tictactoeai.domain.Square;

public interface AiPlayerService {

    AiPlayer createAiPlayer(int squareValue);

    Square makeRandomMove(Board board, AiPlayer player);

    Square makeMove(Board board, AiPlayer player2);

    void updateFactors(Player player2, Integer checkIfWon);
}

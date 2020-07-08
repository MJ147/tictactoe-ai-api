package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Player;
import com.mj147.tictactoeai.domain.Square;

public interface PlayerService {

    Player createPlayer();

    Square makeMove(Long squareId);
}

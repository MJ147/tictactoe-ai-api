package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Game;
import com.mj147.tictactoeai.domain.Square;

public interface GameService {

    Game createGame();

    Game getGame(Long id);

}

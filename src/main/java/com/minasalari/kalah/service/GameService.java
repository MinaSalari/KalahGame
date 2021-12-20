package com.minasalari.kalah.service;

import com.minasalari.kalah.dto.GameResponse;
import com.minasalari.kalah.model.Game;

public interface GameService {

    GameResponse startNewGame();

    GameResponse play(Integer gameId, Integer pitId);

    Game findGameById(Integer gameId);

}

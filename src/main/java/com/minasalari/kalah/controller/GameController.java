package com.minasalari.kalah.controller;

import com.minasalari.kalah.dto.BusinessMessage;
import com.minasalari.kalah.dto.GameResponse;
import com.minasalari.kalah.exception.GameAccessException;
import com.minasalari.kalah.model.Game;
import com.minasalari.kalah.service.GameServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/kalahGame", consumes = "application/json", produces = "application/json")
public class GameController {
    public static final String GAME_TICKET= "Game-Ticket";
    private final GameServiceImpl gameService;

    public GameController(GameServiceImpl gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<GameResponse> startNewGame() {
        GameResponse gameResponse = gameService.startNewGame();
        return ResponseEntity.status(HttpStatus.CREATED).header(GAME_TICKET, getGameTicket(gameResponse)).body(gameResponse);
    }

    @PutMapping("/play/{gameId}/{inputPitId}")
    public ResponseEntity<GameResponse> play(@PathVariable Integer gameId,
                                             @Parameter(description = "inputPitId must be 1 to 6 for player1 and 8 to 13 for player2") @PathVariable Integer inputPitId,
                                             @RequestHeader(GAME_TICKET) String gameTicket) {
        validateGameTicket(gameId, gameTicket);
        GameResponse gameResponse = gameService.play(gameId,inputPitId);
        return ResponseEntity.status(HttpStatus.OK).header(GAME_TICKET, getGameTicket(gameResponse)).body(gameResponse);
    }

    private void validateGameTicket(Integer gameId, String gameTicket) {
        Game game = gameService.findGameById(gameId);
        if(!gameTicket.equals(game.getGameTicket()))
            throw new GameAccessException(BusinessMessage.GAME_ACCESS_IS_DENIED);
    }

    private String getGameTicket(GameResponse gameResponse) {
        return gameResponse.getGame().getGameTicket();
    }
}

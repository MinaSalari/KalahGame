package com.minasalari.kalah.exception;

import com.minasalari.kalah.dto.BusinessMessage;
import com.minasalari.kalah.dto.GameResponse;
import com.minasalari.kalah.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GameExceptionHandler {

    @ResponseBody
    @ExceptionHandler(GameAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    GameResponse gameAccessExceptionHandler(final GameAccessException ex) {
        GameResponse gameResponse = new GameResponse();
        setGameResponse(ex, gameResponse);
        return gameResponse;
    }

    @ResponseBody
    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    GameResponse gameNotFoundExceptionHandler(final GameNotFoundException ex) {
        GameResponse gameResponse = new GameResponse();
        setGameResponse(ex, gameResponse);
        return gameResponse;
    }

    @ResponseBody
    @ExceptionHandler(IllegalMoveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    GameResponse illegalMoveHandler(final IllegalMoveException ex) {
        GameResponse gameResponse = new GameResponse();
        setGameResponse(ex, gameResponse);
        return gameResponse;
    }

    private void setGameResponse(RuntimeException ex, GameResponse gameResponse) {
        BusinessMessage businessMessage = BusinessMessage.valueOf(ex.getMessage());
        Message message = new Message(businessMessage);
        gameResponse.setMessage(message);
        log.error("gameResponse: {}",gameResponse);

    }

}

package com.minasalari.kalah.exception;

import com.minasalari.kalah.dto.BusinessMessage;

public class GameAccessException extends RuntimeException{

    public GameAccessException(BusinessMessage message) {
        super(message.name());
    }
}

package com.minasalari.kalah.exception;

import com.minasalari.kalah.dto.BusinessMessage;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(BusinessMessage message) {
        super(message.name());
    }
}

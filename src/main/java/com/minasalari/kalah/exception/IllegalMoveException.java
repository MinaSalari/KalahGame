package com.minasalari.kalah.exception;

import com.minasalari.kalah.dto.BusinessMessage;

public class IllegalMoveException extends RuntimeException{

    public IllegalMoveException(final BusinessMessage message) {
        super(message.name());
    }
}

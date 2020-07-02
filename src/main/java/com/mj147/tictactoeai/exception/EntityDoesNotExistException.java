package com.mj147.tictactoeai.exception;

public class EntityDoesNotExistException extends RuntimeException {

    public EntityDoesNotExistException() {
        super();
    }

    public EntityDoesNotExistException(String message) {
        super(message);
    }
}

package com.mj147.tictactoeai.exception;

public class SquareIsAlreadyTakenException extends RuntimeException {

    public SquareIsAlreadyTakenException() {
        super();
    }

    public SquareIsAlreadyTakenException(String message) {
        super(message);
    }
}

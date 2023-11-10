package com.compassuol.sp.challenge.msuser.exceptions.customExceptions;

public class UnauthorizedOperationException extends RuntimeException{
    public UnauthorizedOperationException(String message) {
        super(message);
    }
}

package com.compassuol.sp.challenge.msuser.exceptions.customExceptions;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(String message) {
        super(message);
    }
}

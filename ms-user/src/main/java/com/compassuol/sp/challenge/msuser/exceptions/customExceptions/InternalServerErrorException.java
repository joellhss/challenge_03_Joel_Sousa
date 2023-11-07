package com.compassuol.sp.challenge.msuser.exceptions.customExceptions;

public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException(String message) {
        super(message);
    }
}

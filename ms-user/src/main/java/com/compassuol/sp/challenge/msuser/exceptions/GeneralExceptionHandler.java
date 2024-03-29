package com.compassuol.sp.challenge.msuser.exceptions;

import com.compassuol.sp.challenge.msuser.exceptions.customExceptions.*;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.ParseException;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<UserErrorResponse> handleInvalidDataException(InvalidDataException ex) {
        UserErrorResponse error = new UserErrorResponse();

        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setStatus(HttpStatus.BAD_REQUEST.name());
        error.setMessage(ex.getMessage());
        error.setDetails(new DetailsUserErrorResponse(ex.getField(), ex.getMessage()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<UserErrorResponse> handleBusinessException(BusinessException ex) {
        UserErrorResponse error = new UserErrorResponse();

        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setStatus(HttpStatus.BAD_REQUEST.name());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<UserErrorResponse> InternalServerErrorException(InternalServerErrorException ex) {
        UserErrorResponse error = new UserErrorResponse();

        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setStatus(HttpStatus.BAD_REQUEST.name());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<UserErrorResponse> handleEntityNotFoundException(NotFoundUserException ex){
        var httpStatus = HttpStatus.NOT_FOUND;
        var productErrorResponse = new UserErrorResponse();

        productErrorResponse.setCode(httpStatus.value());
        productErrorResponse.setStatus(httpStatus.name());
        productErrorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(productErrorResponse, httpStatus);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<UserErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        UserErrorResponse error = new UserErrorResponse();

        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedOperationException.class)
    public ResponseEntity<UserErrorResponse> handleUnauthorizedOperationException(UnauthorizedOperationException ex) {
        UserErrorResponse error = new UserErrorResponse();

        error.setCode(HttpStatus.FORBIDDEN.value());
        error.setStatus(HttpStatus.FORBIDDEN.name());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<UserErrorResponse> handleParseException(ParseException ex) {
        UserErrorResponse error = new UserErrorResponse();

        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        error.setMessage("We seem to have a problem with the Birthdate field");

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}

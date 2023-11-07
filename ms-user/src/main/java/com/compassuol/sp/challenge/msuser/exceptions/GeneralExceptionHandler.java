package com.compassuol.sp.challenge.msuser.exceptions;

import com.compassuol.sp.challenge.msuser.exceptions.customExceptions.BusinessException;
import com.compassuol.sp.challenge.msuser.exceptions.customExceptions.InternalServerErrorException;
import com.compassuol.sp.challenge.msuser.exceptions.customExceptions.InvalidDataException;
import org.springframework.dao.DataIntegrityViolationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<UserErrorResponse> handleEntityNotFoundException(){
        var httpStatus = HttpStatus.NOT_FOUND;
        var productErrorResponse = new UserErrorResponse();

        productErrorResponse.setCode(httpStatus.value());
        productErrorResponse.setStatus(httpStatus.name());
        productErrorResponse.setMessage("User not found.");

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

}

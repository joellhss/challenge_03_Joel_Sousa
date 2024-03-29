package com.compassuol.sp.challenge.msnotification.exceptions;

import com.compassuol.sp.challenge.msnotification.exceptions.customExceptions.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<NotificationErrorResponse> handleInvalidDataException(InvalidDataException ex) {
        NotificationErrorResponse error = new NotificationErrorResponse();

        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setStatus(HttpStatus.BAD_REQUEST.name());
        error.setMessage(ex.getMessage());
        error.setDetails(new DetailsNotificationErrorResponse(ex.getField(), ex.getMessage()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<NotificationErrorResponse> handleBusinessException(BusinessException ex) {
        NotificationErrorResponse error = new NotificationErrorResponse();

        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setStatus(HttpStatus.BAD_REQUEST.name());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<NotificationErrorResponse> InternalServerErrorException(InternalServerErrorException ex) {
        NotificationErrorResponse error = new NotificationErrorResponse();

        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setStatus(HttpStatus.BAD_REQUEST.name());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundNotificationException.class)
    public ResponseEntity<NotificationErrorResponse> handleEntityNotFoundException(NotFoundNotificationException ex){
        var httpStatus = HttpStatus.NOT_FOUND;
        var productErrorResponse = new NotificationErrorResponse();

        productErrorResponse.setCode(httpStatus.value());
        productErrorResponse.setStatus(httpStatus.name());
        productErrorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(productErrorResponse, httpStatus);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<NotificationErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        NotificationErrorResponse error = new NotificationErrorResponse();

        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedOperationException.class)
    public ResponseEntity<NotificationErrorResponse> handleUnauthorizedOperationExceptionException(UnauthorizedOperationException ex) {
        NotificationErrorResponse error = new NotificationErrorResponse();

        error.setCode(HttpStatus.FORBIDDEN.value());
        error.setStatus(HttpStatus.FORBIDDEN.name());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}

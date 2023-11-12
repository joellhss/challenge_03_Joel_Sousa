package com.compassuol.sp.challenge.msuser.exceptions;

import com.compassuol.sp.challenge.msuser.exceptions.customExceptions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GeneralExceptionHandlerTest {
    @InjectMocks
    private GeneralExceptionHandler exceptionHandler;

    @Test
    public void testHandleInvalidDataException() {
        InvalidDataException ex = new InvalidDataException("Invalid data", "fieldName");
        ex.setField("Teste");

        ResponseEntity<UserErrorResponse> response = exceptionHandler.handleInvalidDataException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Invalid data", response.getBody().getMessage());
        assertEquals("BAD_REQUEST", response.getBody().getStatus());
        assertEquals(400, response.getBody().getCode());
    }
    @Test
    public void testHandleBusinessException() {
        BusinessException ex = new BusinessException("Invalid data");

        ResponseEntity<UserErrorResponse> response = exceptionHandler.handleBusinessException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Invalid data", response.getBody().getMessage());
    }

    @Test
    public void testInternalServerErrorException() {
        InternalServerErrorException ex = new InternalServerErrorException("Invalid data");

        ResponseEntity<UserErrorResponse> response = exceptionHandler.InternalServerErrorException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Invalid data", response.getBody().getMessage());
    }

    @Test
    public void testNotFoundUserException() {
        NotFoundUserException ex = new NotFoundUserException("Invalid data");

        ResponseEntity<UserErrorResponse> response = exceptionHandler.handleEntityNotFoundException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Invalid data", response.getBody().getMessage());
    }

    @Test
    public void testDataIntegrityViolationException() {
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Invalid data");

        ResponseEntity<UserErrorResponse> response = exceptionHandler.handleDataIntegrityViolationException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Invalid data", response.getBody().getMessage());
    }

    @Test
    public void testUnauthorizedOperationException() {
        UnauthorizedOperationException ex = new UnauthorizedOperationException("Invalid data");

        ResponseEntity<UserErrorResponse> response = exceptionHandler.handleUnauthorizedOperationException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Invalid data", response.getBody().getMessage());
    }
}

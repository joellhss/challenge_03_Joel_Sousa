package com.compassuol.sp.challenge.msuser.controllers;

import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestCreate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestPasswordUpdate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestUpdate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoResponse;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserServiceImpl userService;

    @Test
    public void createUser_WithValidData_ReturnsCreated() throws Exception {
        UserDtoRequestCreate request = new UserDtoRequestCreate();
        request.setPassword("123456789");
        request.setBirthdate("1990-10-07");
        request.setActive(true);
        request.setFirstName("Test");
        request.setLastName("Spring");
        request.setCpf("123.123.123-00");
        request.setEmail("test@email.com");

        User entity = request.toEntity();

        UserDtoResponse response = new UserDtoResponse();
        response.setId(1L);
        response.setActive(request.isActive());
        response.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").format(entity.getBirthdate()));
        response.setCpf(request.getCpf());
        response.setEmail(request.getEmail());
        response.setFirstName(request.getFirstName());
        response.setLastName(request.getLastName());

        when(userService.createUser(request)).thenReturn(response);

        ResponseEntity<UserDtoResponse> sut = userController.createUser(request);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(sut.getBody()).isEqualTo(response);
    }

    @Test
    public void updateUser_WithValidData_ReturnsCreated() throws Exception {
        UserDtoRequestUpdate request = new UserDtoRequestUpdate();
        request.setId(1L);
        request.setBirthdate("1990-10-07");
        request.setActive(true);
        request.setFirstName("Test");
        request.setLastName("Spring");
        request.setCpf("123.123.123-00");
        request.setEmail("test@email.com");

        User entity = request.toEntity();

        UserDtoResponse response = new UserDtoResponse();
        response.setId(request.getId());
        response.setActive(request.isActive());
        response.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").format(entity.getBirthdate()));
        response.setCpf(request.getCpf());
        response.setEmail(request.getEmail());
        response.setFirstName(request.getFirstName());
        response.setLastName(request.getLastName());

        UserDetails userDetailsMock = mock(UserDetails.class);

        doNothing().when(userService).updateUser(1L, request, "test@email.com");
        when(userDetailsMock.getUsername()).thenReturn("test@email.com");

        ResponseEntity<String> sut = userController.updateUser(1L, request, userDetailsMock);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isEqualTo("User updated successfully");
    }

    @Test
    public void updatePasswordUser_WithValidData_ReturnsCreated() throws Exception {
        UserDtoRequestPasswordUpdate DTOPassword = new UserDtoRequestPasswordUpdate("1234567");

        UserDetails userDetailsMock = mock(UserDetails.class);

        doNothing().when(userService).updatePassword(1L, DTOPassword, "test@email.com");
        when(userDetailsMock.getUsername()).thenReturn("test@email.com");

        ResponseEntity<String> sut = userController.updatePasswordUser(1L, DTOPassword, userDetailsMock);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isEqualTo("Password updated successfully");
    }

    @Test
    public void userGetById_WithValidData_ReturnsCreated() throws Exception {
        UserDtoResponse response = new UserDtoResponse();
        response.setId(1L);
        response.setBirthdate("07/10/1990");
        response.setActive(true);
        response.setFirstName("Test");
        response.setLastName("Spring");
        response.setCpf("123.123.123-00");
        response.setEmail("test@email.com");

        UserDetails userDetailsMock = mock(UserDetails.class);

        when(userService.getUserById(1L, "test@email.com")).thenReturn(response);
        when(userDetailsMock.getUsername()).thenReturn("test@email.com");

        ResponseEntity<UserDtoResponse> sut = userController.getUserById(1L, userDetailsMock);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody()).isEqualTo(response);
    }
}


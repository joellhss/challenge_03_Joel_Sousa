package com.compassuol.sp.challenge.msuser.controllers;

import com.compassuol.sp.challenge.msuser.config.TokenService;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoResponseLogin;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestLogin;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAuthControllerTest {
    @InjectMocks
    private UserAuthController authController;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenService tokenService;

    @Test
    public void loginUser_WithValidData_ReturnsOk() throws Exception {

        UserDtoRequestLogin login = new UserDtoRequestLogin("email@example.com", "password");
        Authentication authenticate = mock(Authentication.class);

        when(authenticate.getPrincipal()).thenReturn(new User());
        when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(authenticate);
        String token = "your_mocked_token";
        when(tokenService.generateToken(any(User.class))).thenReturn(token);

        ResponseEntity<UserDtoResponseLogin> sut = authController.loginUser(login);

        assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sut.getBody().getToken()).isEqualTo(token);
    }
}

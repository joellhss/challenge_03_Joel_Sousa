package com.compassuol.sp.challenge.msuser.controllers;

import com.compassuol.sp.challenge.msuser.Enums.EventsForNotification;
import com.compassuol.sp.challenge.msuser.config.security.TokenService;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoResponseLogin;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestLogin;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/login")
public class UserAuthController {
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserDtoResponseLogin> loginUser(@RequestBody UserDtoRequestLogin login){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) authenticate.getPrincipal());

        userService.sendUserCreationMessage(((User) authenticate.getPrincipal()).getEmail(), EventsForNotification.LOGIN);

        return ResponseEntity.status(HttpStatus.OK).body(new UserDtoResponseLogin(token));
    }
}

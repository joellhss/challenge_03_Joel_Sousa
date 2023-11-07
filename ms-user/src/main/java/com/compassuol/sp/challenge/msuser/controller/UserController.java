package com.compassuol.sp.challenge.msuser.controller;

import com.compassuol.sp.challenge.msuser.model.dto.UserDtoReponse;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestCreate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestLogin;
import com.compassuol.sp.challenge.msuser.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class UserController {

    UserServiceImpl userService;
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDtoRequestLogin login){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.status(HttpStatus.OK).body("Logged in successfully");
    }

    @PostMapping("/users")
    ResponseEntity<UserDtoReponse> createUser(@RequestBody UserDtoRequestCreate user){
        UserDtoReponse create = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }


}

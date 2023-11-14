package com.compassuol.sp.challenge.msuser.controllers;

import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestPasswordUpdate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestUpdate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoResponse;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestCreate;
import com.compassuol.sp.challenge.msuser.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private UserServiceImpl userService;

    @PostMapping
    ResponseEntity<UserDtoResponse> createUser(@RequestBody UserDtoRequestCreate user){
        UserDtoResponse create = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        String authenticatedUserEmail = userDetails.getUsername();
        UserDtoResponse response = userService.getUserById(id, authenticatedUserEmail);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDtoRequestUpdate user, @AuthenticationPrincipal UserDetails userDetails) {
        String authenticatedUserEmail = userDetails.getUsername();
        userService.updateUser(id, user, authenticatedUserEmail);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }

    @PutMapping("/{id}/password")
    ResponseEntity<String> updatePasswordUser(@PathVariable Long id, @RequestBody UserDtoRequestPasswordUpdate password, @AuthenticationPrincipal UserDetails userDetails) {
        String authenticatedUserEmail = userDetails.getUsername();
        userService.updatePassword(id, password, authenticatedUserEmail);
        return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully");
    }


}

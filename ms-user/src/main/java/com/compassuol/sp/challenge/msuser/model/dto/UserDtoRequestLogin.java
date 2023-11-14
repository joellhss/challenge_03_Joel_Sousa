package com.compassuol.sp.challenge.msuser.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDtoRequestLogin {
    private String email;
    private String password;
}


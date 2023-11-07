package com.compassuol.sp.challenge.msuser.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UserDtoRequestLogin {
    private String email;
    private String password;
}


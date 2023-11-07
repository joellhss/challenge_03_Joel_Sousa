package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestCreate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoReponse;

public interface UserService {
    UserDtoReponse getUserById(long userId);
    UserDtoReponse createUser(UserDtoRequestCreate user);
    UserDtoReponse updateUser(Long userId, UserDtoRequestCreate user);
    UserDtoReponse updatePassword(Long userId, String password);
}

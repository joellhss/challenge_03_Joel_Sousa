package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestPasswordUpdate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestCreate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestUpdate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDtoResponse getUserById(long userId, String authenticatedUserEmail);
    UserDtoResponse createUser(UserDtoRequestCreate user);
    void updateUser(Long userId, UserDtoRequestUpdate user, String authenticatedUserEmail);
    void updatePassword(Long userId, UserDtoRequestPasswordUpdate password, String authenticatedUserEmail);
}

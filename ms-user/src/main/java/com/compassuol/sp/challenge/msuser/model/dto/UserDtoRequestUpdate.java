package com.compassuol.sp.challenge.msuser.model.dto;

import com.compassuol.sp.challenge.msuser.model.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class UserDtoRequestUpdate {
    private String firstName;
    private String lastName;
    private String cpf;
    private Date birthdate;
    private String email;
    private boolean active;

    public UserDtoRequestUpdate toDto(User userEntity) {
        UserDtoRequestUpdate userDto = new UserDtoRequestUpdate();
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setCpf(userEntity.getCpf());
        userDto.setBirthdate(userEntity.getBirthdate());
        userDto.setEmail(userEntity.getEmail());
        userDto.setActive(userEntity.isActive());
        return userDto;
    }

    public User toEntity(UserDtoRequestUpdate userDto) {
        User userEntity = new User();
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setCpf(userDto.getCpf());
        userEntity.setBirthdate(userDto.getBirthdate());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setActive(userDto.isActive());
        return userEntity;
    }
}


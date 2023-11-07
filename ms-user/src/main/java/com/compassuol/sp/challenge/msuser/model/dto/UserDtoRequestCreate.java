package com.compassuol.sp.challenge.msuser.model.dto;

import com.compassuol.sp.challenge.msuser.model.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class UserDtoRequestCreate {
    private String firstName;
    private String lastName;
    private String cpf;
    private Date birthdate;
    private String email;
    private String password;
    private boolean active;

    public UserDtoRequestCreate toDto(User userEntity) {
        UserDtoRequestCreate userDto = new UserDtoRequestCreate();
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setCpf(userEntity.getCpf());
        userDto.setBirthdate(userEntity.getBirthdate());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setActive(userEntity.isActive());
        return userDto;
    }

    public User toEntity() {
        User userEntity = new User();
        userEntity.setFirstName(getFirstName());
        userEntity.setLastName(getLastName());
        userEntity.setCpf(getCpf());
        userEntity.setBirthdate(getBirthdate());
        userEntity.setEmail(getEmail());
        userEntity.setPassword(getPassword());
        userEntity.setActive(isActive());
        return userEntity;
    }
}


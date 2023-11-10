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
        setFirstName(userEntity.getFirstName());
        setLastName(userEntity.getLastName());
        setCpf(userEntity.getCpf());
        setBirthdate(userEntity.getBirthdate());
        setEmail(userEntity.getEmail());
        setPassword(userEntity.getPassword());
        setActive(userEntity.isActive());
        return this;
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


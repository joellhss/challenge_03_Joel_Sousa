package com.compassuol.sp.challenge.msuser.model.dto;

import com.compassuol.sp.challenge.msuser.model.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class UserDtoReponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    private Date birthdate;
    private String email;
    private boolean active;

    public UserDtoReponse toDto(User userEntity) {
        setId(userEntity.getId());
        setFirstName(userEntity.getFirstName());
        setLastName(userEntity.getLastName());
        setCpf(userEntity.getCpf());
        setBirthdate(userEntity.getBirthdate());
        setEmail(userEntity.getEmail());
        setActive(userEntity.isActive());
        return this;
    }

    public User toEntity(UserDtoReponse userDto) {
        User userEntity = new User();
        userEntity.setId(userDto.getId());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setCpf(userDto.getCpf());
        userEntity.setBirthdate(userDto.getBirthdate());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setActive(userDto.isActive());
        return userEntity;
    }
}


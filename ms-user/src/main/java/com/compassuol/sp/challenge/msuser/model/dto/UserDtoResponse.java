package com.compassuol.sp.challenge.msuser.model.dto;

import com.compassuol.sp.challenge.msuser.model.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class UserDtoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    private Date birthdate;
    private String email;
    private boolean active;

    public UserDtoResponse toDto(User userEntity) {
        setId(userEntity.getId());
        setFirstName(userEntity.getFirstName());
        setLastName(userEntity.getLastName());
        setCpf(userEntity.getCpf());
        setBirthdate(userEntity.getBirthdate());
        setEmail(userEntity.getEmail());
        setActive(userEntity.isActive());
        return this;
    }

    public User toEntity() {
        User userEntity = new User();
        userEntity.setId(getId());
        userEntity.setFirstName(getFirstName());
        userEntity.setLastName(getLastName());
        userEntity.setCpf(getCpf());
        userEntity.setBirthdate(getBirthdate());
        userEntity.setEmail(getEmail());
        userEntity.setActive(isActive());
        return userEntity;
    }
}


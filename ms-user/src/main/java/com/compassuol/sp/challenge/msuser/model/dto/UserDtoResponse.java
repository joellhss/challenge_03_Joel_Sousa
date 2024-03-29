package com.compassuol.sp.challenge.msuser.model.dto;

import com.compassuol.sp.challenge.msuser.model.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class UserDtoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String birthdate;
    private String email;
    private boolean active;

    public UserDtoResponse toDto(User userEntity) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        setId(userEntity.getId());
        setFirstName(userEntity.getFirstName());
        setLastName(userEntity.getLastName());
        setCpf(userEntity.getCpf());
        setBirthdate(dateFormat.format(userEntity.getBirthdate()));
        setEmail(userEntity.getEmail());
        setActive(userEntity.isActive());
        return this;
    }

    public User toEntity() throws ParseException {
        User userEntity = new User();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        userEntity.setId(getId());
        userEntity.setFirstName(getFirstName());
        userEntity.setLastName(getLastName());
        userEntity.setCpf(getCpf());
        userEntity.setBirthdate(dateFormat.parse(getBirthdate()));
        userEntity.setEmail(getEmail());
        userEntity.setActive(isActive());
        return userEntity;
    }
}


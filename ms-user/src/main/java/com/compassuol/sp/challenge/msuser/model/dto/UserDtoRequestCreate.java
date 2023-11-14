package com.compassuol.sp.challenge.msuser.model.dto;

import com.compassuol.sp.challenge.msuser.exceptions.customExceptions.InternalServerErrorException;
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
public class UserDtoRequestCreate {
    private String firstName;
    private String lastName;
    private String cpf;
    private String birthdate;
    private String email;
    private String password;
    private boolean active;

    public UserDtoRequestCreate toDto(User userEntity) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        UserDtoRequestCreate userDto = new UserDtoRequestCreate();
        setFirstName(userEntity.getFirstName());
        setLastName(userEntity.getLastName());
        setCpf(userEntity.getCpf());
        setBirthdate(dateFormat.format(userEntity.getBirthdate()));
        setEmail(userEntity.getEmail());
        setPassword(userEntity.getPassword());
        setActive(userEntity.isActive());
        return this;
    }

    public User toEntity() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        User userEntity = new User();
        userEntity.setFirstName(getFirstName());
        userEntity.setLastName(getLastName());
        userEntity.setCpf(getCpf());
        userEntity.setEmail(getEmail());
        userEntity.setPassword(getPassword());
        userEntity.setActive(isActive());

        try {
            userEntity.setBirthdate(dateFormat.parse(getBirthdate()));
        } catch (ParseException ex) {
           throw new InternalServerErrorException("We had a problem dealing with date parse.");
        }

        return userEntity;
    }
}


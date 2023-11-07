package com.compassuol.sp.challenge.msuser.services.impl;

import com.compassuol.sp.challenge.msuser.exceptions.customExceptions.BusinessException;
import com.compassuol.sp.challenge.msuser.exceptions.customExceptions.InvalidDataException;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoReponse;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestCreate;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.services.UserService;
import com.compassuol.sp.challenge.msuser.utils.Validations;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Override
    public UserDtoReponse getUserById(long userId) {
        return null;
    }

    @Override
    public UserDtoReponse createUser(UserDtoRequestCreate user) {
        if(user.getFirstName().length() < 3) throw new InvalidDataException("The first name field cannot be less than 3 characters long", "firstName");
        if(user.getLastName().length() < 3) throw new InvalidDataException("The last name field cannot be less than 3 characters long", "lastName");
        if(!Validations.emailValidator(user.getEmail())) throw new InvalidDataException("Please enter a valid email", "email");
        if(!Validations.cpfValidator(user.getCpf())) throw new InvalidDataException("The CPF required is in the pattern xxx.xxx.xxx-xx", "cpf");
        if(user.getPassword().length() < 6) throw new InvalidDataException("The password field cannot be less than 6 characters long", "password");

        if(repository.findByEmail(user.getEmail()).isPresent()) throw new BusinessException("This email is already registered in the system");
        if(repository.findByCpf(user.getCpf()).isPresent()) throw new BusinessException("This CPF is already registered in the system");

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);

        User resultSave = repository.save(user.toEntity());
        return new UserDtoReponse().toDto(resultSave);
    }

    @Override
    public UserDtoReponse updateUser(Long userId, UserDtoRequestCreate user) {
        return null;
    }

    @Override
    public UserDtoReponse updatePassword(Long userId, String password) {
        return null;
    }
}

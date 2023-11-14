package com.compassuol.sp.challenge.msuser.services.impl;

import com.compassuol.sp.challenge.msuser.enums.EventsForNotification;
import com.compassuol.sp.challenge.msuser.config.RabbitMQ.UserCreationMessage;
import com.compassuol.sp.challenge.msuser.exceptions.customExceptions.*;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestPasswordUpdate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestUpdate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoResponse;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestCreate;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.services.UserService;
import com.compassuol.sp.challenge.msuser.utils.Validations;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private RabbitTemplate rabbitTemplate;

    @Override
    public UserDtoResponse getUserById(long userId, String authenticatedUserEmail) {
        Optional<User> response = repository.findById(userId);
        if(response.isEmpty()) throw new NotFoundUserException("User does not exist in the database.");
        authenticationValidator(userId, authenticatedUserEmail);
        return new UserDtoResponse().toDto(response.get());
    }

    @Override
    public UserDtoResponse createUser(UserDtoRequestCreate user) {
        if(user.getFirstName().length() < 3) throw new InvalidDataException("The first name field cannot be less than 3 characters long", "firstName");
        if(user.getLastName().length() < 3) throw new InvalidDataException("The last name field cannot be less than 3 characters long", "lastName");
        if(!Validations.emailValidator(user.getEmail())) throw new InvalidDataException("Please enter a valid email", "email");
        if(!Validations.cpfValidator(user.getCpf())) throw new InvalidDataException("The CPF required is in the pattern xxx.xxx.xxx-xx", "cpf");
        if(!Validations.dateValidator(user.getBirthdate())) throw new InvalidDataException("The date must be in the following format: 'yyyy-MM-dd' (year/month/day).", "birthdate");
        if(user.getPassword().length() < 6) throw new InvalidDataException("The password field cannot be less than 6 characters long", "password");

        if(repository.findByEmail(user.getEmail()).isPresent()) throw new BusinessException("This email is already registered in the system");
        if(repository.findByCpf(user.getCpf()).isPresent()) throw new BusinessException("This CPF is already registered in the system");

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        sendUserCreationMessage(user.getEmail(), EventsForNotification.CREATE);
        User resultSave = repository.save(user.toEntity());



        return new UserDtoResponse().toDto(resultSave);
    }

    @Override
    public void updateUser(Long userId, UserDtoRequestUpdate user, String authenticatedUserEmail) {
        authenticationValidator(userId, authenticatedUserEmail);
        Optional<User> userRepository = repository.findById(userId);
        user.setId(userId);

        if(user.getFirstName().length() < 3) throw new InvalidDataException("The first name field cannot be less than 3 characters long", "firstName");
        if(user.getLastName().length() < 3) throw new InvalidDataException("The last name field cannot be less than 3 characters long", "lastName");
        if(!Validations.emailValidator(user.getEmail())) throw new InvalidDataException("Please enter a valid email", "email");
        if(!Validations.cpfValidator(user.getCpf())) throw new InvalidDataException("The CPF required is in the pattern xxx.xxx.xxx-xx", "cpf");
        if(!Validations.dateValidator(user.getBirthdate())) throw new InvalidDataException("The date must be in the following format: 'yyyy-MM-dd' (year/month/day).", "birthdate");

        Optional<User> resultEmail = repository.findByEmail(user.getEmail());
        Optional<User> resultCPF = repository.findByCpf(user.getCpf());
        if(resultEmail.isPresent() && !resultEmail.get().getEmail().equals(user.getEmail())) throw new BusinessException("This email is already registered in the system");
        if(resultCPF.isPresent() && !resultCPF.get().getCpf().equals(user.getCpf())) throw new BusinessException("This CPF is already registered in the system");

        User userUpdated = user.toEntity();
        userUpdated.setPassword(userRepository.get().getPassword());

        try {
            repository.save(userUpdated);
            sendUserCreationMessage(userUpdated.getEmail(), EventsForNotification.UPDATE);
        } catch (Exception ex) {
            throw new InternalServerErrorException("unknown error. Please contact support");
        }
    }

    @Override
    public void updatePassword(Long userId, UserDtoRequestPasswordUpdate password, String authenticatedUserEmail) {
        authenticationValidator(userId, authenticatedUserEmail);
        if(password.getPassword().length() < 6) throw new InvalidDataException("The password field cannot be less than 6 characters long", "password");
        User userRepository = repository.findById(userId).get();
        String encryptedPassword = new BCryptPasswordEncoder().encode(password.getPassword());
        userRepository.setPassword(encryptedPassword);
        repository.save(userRepository);
        sendUserCreationMessage(userRepository.getEmail(), EventsForNotification.UPDATE_PASSWORD);
    }
    public void authenticationValidator(Long userId, String authenticatedUserEmail) {
        Optional<User> responseUser = repository.findByEmail(authenticatedUserEmail);
        if (responseUser.isEmpty()) throw new InternalServerErrorException("Something unexpected occurred. Try logging in again");
        if (!responseUser.get().getId().equals(userId)) throw new UnauthorizedOperationException("You do not have permission to change this account");
    }

    public void sendUserCreationMessage(String userEmail, EventsForNotification operation) {
        UserCreationMessage message = new UserCreationMessage();
        message.setEmail(userEmail);
        message.setEvent(operation);
        message.setDate(LocalDateTime.now().toString());

        rabbitTemplate.convertAndSend("msuser-notification", message);
    }
}

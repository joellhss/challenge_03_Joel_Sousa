package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.exceptions.customExceptions.NotFoundUserException;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestCreate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestPasswordUpdate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoRequestUpdate;
import com.compassuol.sp.challenge.msuser.model.dto.UserDtoResponse;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void createUser_withValidData_ReturnsOrder() throws ParseException {
        UserDtoRequestCreate request = new UserDtoRequestCreate();
        request.setPassword("123456789");
        request.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse("1990-10-07"));
        request.setActive(true);
        request.setFirstName("Test");
        request.setLastName("Spring");
        request.setCpf("123.123.123-00");
        request.setEmail("test@email.com");

        User user = new User();
        user.setId(1L);
        user.setPassword("123456789");
        user.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse("1990-10-07"));
        user.setActive(true);
        user.setFirstName("Test");
        user.setLastName("Spring");
        user.setCpf("123.123.123-00");
        user.setEmail("test@email.com");

        UserDtoResponse response = new UserDtoResponse();
        response.setId(1L);
        response.setActive(request.isActive());
        response.setBirthdate(request.getBirthdate());
        response.setCpf(request.getCpf());
        response.setEmail(request.getEmail());
        response.setFirstName(request.getFirstName());
        response.setLastName(request.getLastName());

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDtoResponse sut = userService.createUser(request);

        assertThat(sut.getId()).isEqualTo(response.getId());
        assertThat(sut.getBirthdate()).isEqualTo(response.getBirthdate());
        assertThat(sut.getFirstName()).isEqualTo(response.getFirstName());
        assertThat(sut.getLastName()).isEqualTo(response.getLastName());
        assertThat(sut.getCpf()).isEqualTo(response.getCpf());
        assertThat(sut.getEmail()).isEqualTo(response.getEmail());
        assertThat(sut.isActive()).isEqualTo(response.isActive());

    }
    @Test
    public void updateUser_withValidData() throws ParseException {
        UserDtoRequestUpdate request = new UserDtoRequestUpdate();
        request.setId(1L);
        request.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse("1990-10-07"));
        request.setActive(true);
        request.setFirstName("Test");
        request.setLastName("Spring NEW");
        request.setCpf("123.123.123-00");
        request.setEmail("test@email.com");

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setPassword("123456789");
        existingUser.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse("1990-10-07"));
        existingUser.setActive(true);
        existingUser.setFirstName("Test");
        existingUser.setLastName("Spring OLD");
        existingUser.setCpf("123.123.123-00");
        existingUser.setEmail("test@email.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail("test@email.com")).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(existingUser));
        when(userRepository.findByCpf(request.getCpf())).thenReturn(Optional.empty());

        userService.updateUser(1L, request, "test@email.com");

        verify(userRepository, times(1)).findById(1L);

        verify(userRepository, times(2)).findByEmail("test@email.com");

        verify(userRepository, times(1)).findByCpf(request.getCpf());

        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void updatePassword_withValidData() {
        Long userId = 1L;
        String authenticatedUserEmail = "authenticated@example.com";
        UserDtoRequestPasswordUpdate passwordDto = new UserDtoRequestPasswordUpdate();
        passwordDto.setPassword("newPassword");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail(authenticatedUserEmail);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail(authenticatedUserEmail)).thenReturn(Optional.of(existingUser));

        userService.updatePassword(userId, passwordDto, authenticatedUserEmail);
    }

    @Test
    public void getUserById_withValidData_ReturnsUser() {
        long userId = 1L;
        String authenticatedUserEmail = "authenticated@example.com";

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail(authenticatedUserEmail);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail(authenticatedUserEmail)).thenReturn(Optional.of(existingUser));

        UserDtoResponse result = userService.getUserById(userId, authenticatedUserEmail);

        assertNotNull(result);
        assertThat(userId).isEqualTo(result.getId());
        assertThat(authenticatedUserEmail).isEqualTo(result.getEmail());
    }

    @Test
    public void getUserById_withInvalidId_ReturnsException() {
        long userId = 1L;
        String authenticatedUserEmail = "authenticated@example.com";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundUserException.class,
                () -> userService.getUserById(userId, authenticatedUserEmail));
    }
}

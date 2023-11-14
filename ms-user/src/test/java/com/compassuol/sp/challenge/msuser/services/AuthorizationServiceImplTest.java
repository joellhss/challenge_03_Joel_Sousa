package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.exceptions.customExceptions.InvalidDataException;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.services.impl.AuthorizationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorizationServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthorizationServiceImpl authorizationService;

    @Test
    void testLoadUserByUsername() {
        // Dados de exemplo
        String username = "user@example.com";

        // Mock e dados de retorno simulados
        User user = new User(); // Certifique-se de que você tenha uma classe User
        user.setEmail(username);
        user.setPassword("hashedPassword"); // Certifique-se de que a senha seja definida de acordo com o seu modelo

        when(userRepository.findByEmail(username)).thenReturn(Optional.of(user));

        // Executa o método a ser testado
        UserDetails userDetails = authorizationService.loadUserByUsername(username);

        // Verificações
        assertEquals(username, userDetails.getUsername());
        // Adicione mais verificações conforme necessário, como roles, authorities, etc.
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Dados de exemplo
        String username = "nonexistent@example.com";

        // Mock e dados de retorno simulados
        when(userRepository.findByEmail(username)).thenReturn(Optional.empty());

        // Executa o método a ser testado e espera uma exceção
        try {
            authorizationService.loadUserByUsername(username);
        } catch (InvalidDataException e) {
            assertEquals("E-mail not found", e.getMessage());
        }
    }
}

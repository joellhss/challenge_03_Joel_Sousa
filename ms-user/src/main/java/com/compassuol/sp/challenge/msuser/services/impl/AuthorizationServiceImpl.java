package com.compassuol.sp.challenge.msuser.services.impl;

import com.compassuol.sp.challenge.msuser.exceptions.customExceptions.InvalidDataException;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorizationServiceImpl implements UserDetailsService {
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> response = userRepository.findByEmail(username);
        if(response.isEmpty()) throw new InvalidDataException("E-mail not found", "email");
        return response.get();
    }
}

package com.lab2.emtbackend.service.impl;

import com.lab2.emtbackend.dto.UserDto;
import com.lab2.emtbackend.model.User;
import com.lab2.emtbackend.model.enums.Role;
import com.lab2.emtbackend.repository.UserRepository;
import com.lab2.emtbackend.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<Boolean> register(UserDto userDto) throws Exception {
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.ROLE_LIBRARIAN);

        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new Exception();
        }

        return Optional.of(true);
    }
}

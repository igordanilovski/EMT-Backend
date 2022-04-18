package com.lab2.emtbackend.service;

import com.lab2.emtbackend.dto.UserDto;
import com.lab2.emtbackend.dto.UserLoginDto;
import com.lab2.emtbackend.model.User;
import com.lab2.emtbackend.model.projections.JWTProjection;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<JWTProjection> authenticate(UserLoginDto userLoginDto) throws Exception;

    Optional<Boolean> register(UserDto userDto) throws Exception;

    Optional<User> findByEmail(String email);
}

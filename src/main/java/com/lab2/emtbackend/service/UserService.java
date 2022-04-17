package com.lab2.emtbackend.service;

import com.lab2.emtbackend.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    UserDetails loadUserByUsername(String username);

    Optional<Boolean> register(UserDto userDto) throws Exception;
}

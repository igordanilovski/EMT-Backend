package com.lab2.emtbackend.service.impl;

import com.lab2.emtbackend.dto.UserDto;
import com.lab2.emtbackend.dto.UserLoginDto;
import com.lab2.emtbackend.model.User;
import com.lab2.emtbackend.model.enums.Role;
import com.lab2.emtbackend.model.projections.JWTProjection;
import com.lab2.emtbackend.repository.UserRepository;
import com.lab2.emtbackend.service.UserService;
import com.lab2.emtbackend.configuration.utils.JWTUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils JWTUtils;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager, JWTUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.JWTUtils = jwtUtils;
    }

    @Override
    public Optional<JWTProjection> authenticate(UserLoginDto userLoginDto) throws Exception {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect credentials", e);
        }

        final UserDetails userDetails = this.loadUserByUsername(userLoginDto.getEmail());
        final String token = this.JWTUtils.generateToken(userDetails);

        return Optional.of(new JWTProjection(token));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByEmail(username).get();

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().getAuthority())));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with that email doesn't exist.")));
    }
}

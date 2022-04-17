package com.lab2.emtbackend.configuration.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab2.emtbackend.model.User;
import com.lab2.emtbackend.model.exceptions.CustomInvalidActionException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (credentials == null) {
            throw new UsernameNotFoundException("Invalid");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.getUsername());

        if (!passwordEncoder.matches(credentials.getPassword(), userDetails.getPassword())) {
            throw new CustomInvalidActionException();
        }

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities())
        );
    }
}

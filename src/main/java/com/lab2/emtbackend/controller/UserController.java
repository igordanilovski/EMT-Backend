package com.lab2.emtbackend.controller;

import com.lab2.emtbackend.dto.UserDto;
import com.lab2.emtbackend.model.exceptions.CustomNotFoundException;
import com.lab2.emtbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody UserDto userDto) throws Exception {
        return this.userService.register(userDto)
                .map(status -> ResponseEntity.ok().body(status))
                .orElseThrow(CustomNotFoundException::new);
    }
}

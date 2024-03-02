package ru.avedernikov.asyncarchitecture.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avedernikov.asyncarchitecture.auth.UserRepository;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

}

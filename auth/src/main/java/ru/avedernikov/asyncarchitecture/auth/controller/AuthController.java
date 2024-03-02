package ru.avedernikov.asyncarchitecture.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avedernikov.asyncarchitecture.auth.UserRepository;
import ru.avedernikov.asyncarchitecture.auth.model.User;
import ru.avedernikov.asyncarchitecture.auth.utils.UserEventConverter;
import ru.avedernikov.asyncarchitecture.eventmodel.UserEvent;

import java.util.UUID;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<UUID, UserEvent> userEventTemplate;

    @PostMapping("/sign_in")
    signIn() {
        // todo
        User user = null;
        UserEvent event = UserEventConverter.userToUserEvent(user);
        userEventTemplate.send(, event);
    }

}

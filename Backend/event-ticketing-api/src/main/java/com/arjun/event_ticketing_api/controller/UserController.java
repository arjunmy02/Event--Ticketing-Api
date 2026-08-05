package com.arjun.event_ticketing_api.controller;

import com.arjun.event_ticketing_api.model.UsersEntity;
import com.arjun.event_ticketing_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userservice;

    @PostMapping("/register")
    public UsersEntity addUser(@RequestBody UsersEntity users) {
        return userservice.addUser(users);
    }
    @PostMapping("/login")
    public String login(@RequestBody UsersEntity user ){
        return userservice.verify(user);
    }


}

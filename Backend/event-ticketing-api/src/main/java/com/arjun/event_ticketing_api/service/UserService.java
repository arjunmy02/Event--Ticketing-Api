package com.arjun.event_ticketing_api.service;

import com.arjun.event_ticketing_api.model.UsersEntity;
import com.arjun.event_ticketing_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private JWTService jwtService;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UsersEntity addUser(UsersEntity user) {

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);

    }
//    public String verify(UsersEntity user) {
//        UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(
//                        user.getUsername(),
//                        user.getPassword()
//                );
//        Authentication authentication =
//                authenticationManager.authenticate(authToken);
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(user.getUsername());
//        }
//
//        return "fail";
//
//
//    }
public String verify(UsersEntity user) {
    try {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                );

        Authentication authentication =
                authenticationManager.authenticate(authToken);

        System.out.println("Authenticated = " + authentication.isAuthenticated());

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }

        return "fail";

    } catch (Exception e) {
        e.printStackTrace();
        return "fail";
    }
}
}


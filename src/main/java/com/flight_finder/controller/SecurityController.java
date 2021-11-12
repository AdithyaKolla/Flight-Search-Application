package com.flight_finder.controller;

import com.flight_finder.domain.LoginRequest;
import com.flight_finder.domain.User;
import com.flight_finder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@CrossOrigin
public class SecurityController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsernamePassword(loginRequest.getUserName(), loginRequest.getPassword());
        if (user != null) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword(), Collections.singleton(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "ADMIN";
                }
            })));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        user = userService.save(user);
        if (user.getUserId() != null) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(), Collections.singleton((GrantedAuthority) () -> "ADMIN")));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/user-details")
    public ResponseEntity<?> getLoggedInUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String userName = (String) authentication.getPrincipal();
            User user = userService.findByUsername(userName);
            if (user != null) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(403).build();
    }

}

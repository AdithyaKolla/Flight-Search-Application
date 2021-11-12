package com.flight_finder.controller;

import com.flight_finder.domain.LoginRequest;
import com.flight_finder.domain.User;
import com.flight_finder.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class SecurityControllerTest {

    @InjectMocks
    SecurityController securityController;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    UserService userService;

    @Mock
    Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin() {
        User mockedUser = new User();
        when(userService.findByUsernamePassword(null, null)).thenReturn(mockedUser);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        Assertions.assertEquals(ResponseEntity.ok(mockedUser), securityController.login(new LoginRequest()));
        Assertions.assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void testSignup() {
        User mockedUser = new User();
        mockedUser.setUserId(1L);
        when(userService.save(mockedUser)).thenReturn(mockedUser);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        Assertions.assertEquals(ResponseEntity.ok(mockedUser), securityController.signup(mockedUser));
        Assertions.assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void testGetLoggedInUserDetails() {
        User mockedUser = new User();
        mockedUser.setUserId(1L);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userService.findByUsername(anyString())).thenReturn(mockedUser);
        when(authentication.getPrincipal()).thenReturn("");

        Assertions.assertEquals(ResponseEntity.ok(mockedUser), securityController.getLoggedInUserDetails());
    }

}

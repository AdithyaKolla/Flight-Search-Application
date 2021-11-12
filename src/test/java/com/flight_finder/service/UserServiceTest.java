package com.flight_finder.service;

import com.flight_finder.dao.UserRepository;
import com.flight_finder.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByUsernamePassword() {
        User mockedUser = new User();
        when(userRepository.findByUserName(anyString())).thenReturn(mockedUser);
        Assertions.assertEquals(mockedUser, userService.findByUsername(""));
    }

    @Test
    public void testSave() {
        User mockedUser = new User();
        when(userRepository.save(any())).thenReturn(mockedUser);
        Assertions.assertEquals(mockedUser, userService.save(mockedUser));
    }

    @Test
    public void testFindByUsername() {
        User mockedUser = new User();
        when(userRepository.findByUserName(anyString())).thenReturn(mockedUser);
        Assertions.assertEquals(mockedUser, userService.findByUsername(""));
    }

}

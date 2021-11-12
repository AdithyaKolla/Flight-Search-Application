package com.flight_finder.service;

import com.flight_finder.dao.UserRepository;
import com.flight_finder.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findByUsernamePassword(String userName, String password) {
        return userRepository.findByUsernamePassword(userName, password);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }
}

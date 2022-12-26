package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public interface UserService extends UserDetailsService{

    void addUser(User user);

    void updateUser(User user);

    void removeUser(Long id);

    User findByUsername(String username);


    User findUserById(Long id);

    List<User> getListUsers();
}
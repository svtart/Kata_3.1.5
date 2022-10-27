package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Override
    @Transactional
    public List<User> index() {
        return userDAO.index();
    }

    @Override
    @Transactional
    public User show(int id) {
        return userDAO.show(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        userDAO.save(user);
    }


    @Override
    @Transactional
    public void update(int id, User user) {
        userDAO.update(id, user);
    }
    @Override
    @Transactional
    public void delete(int id) {
        userDAO.delete(id);
    }
}

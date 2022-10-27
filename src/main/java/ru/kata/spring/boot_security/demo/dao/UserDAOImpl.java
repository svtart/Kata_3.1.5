package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    private static int PEOPLE_COUNT;


    //    private List<User> users;
//
//    {
//        users = new ArrayList<>();
//        users.add(new User(++PEOPLE_COUNT, "Artur", "Svetozaorv", "IT", 300));
//        users.add(new User(++PEOPLE_COUNT, "Boris", "Moni", "HR", 300));
//        users.add(new User(++PEOPLE_COUNT, "Lela", "Monick", "Sales", 150));
//        users.add(new User(++PEOPLE_COUNT, "Bony", "Claid", "IT", 200));
//    }

    @Override
    public List<User> index() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User show(int id) {
//        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.id = :id", User.class);
//        q.setParameter("id", id);
//        return q.getResultList().stream().findAny().orElse(null);
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
//        user.setId(++PEOPLE_COUNT);
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
//        users.removeIf(u -> u.getId() == id);
        entityManager.remove(show(id));
    }
}

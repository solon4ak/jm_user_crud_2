package ru.solon4ak.jm.repository;

import ru.solon4ak.jm.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();
    User get(long id);
    void save(User user);
    void delete(User user);
    void update(User user);
}

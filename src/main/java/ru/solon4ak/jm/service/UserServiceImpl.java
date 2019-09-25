package ru.solon4ak.jm.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.solon4ak.jm.model.User;
import ru.solon4ak.jm.repository.UserDao;

/**
 *
 * @author solon4ak
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        user.setDateCreated(Date.from(Instant.now()));
        user.setLastUpdate(Date.from(Instant.now()));
        userDao.save(user);
    }

    @Override
    public List<User> listAll() {
        return userDao.getAll();
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(userDao.get(id));
    }

    @Override
    public void update(User user) {
        user.setLastUpdate(Date.from(Instant.now()));
        userDao.update(user);
    }

}

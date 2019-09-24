package ru.solon4ak.jm.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.solon4ak.jm.model.User;
import ru.solon4ak.jm.repository.UserRepository;

/**
 *
 * @author solon4ak
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        if (user.getId() == null) {
            user.setDateCreated(Date.from(Instant.now()));
            user.setLastUpdate(Date.from(Instant.now()));
        } else {
            user.setLastUpdate(Date.from(Instant.now()));
        }
        userRepository.save(user);
    }

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}

package ru.solon4ak.jm.repository;

import org.springframework.data.repository.CrudRepository;
import ru.solon4ak.jm.model.User;

/**
 *
 * @author solon4ak
 */
public interface UserRepository extends CrudRepository<User, Long> {
    
}

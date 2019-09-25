/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.solon4ak.jm.service;

import java.util.List;
import ru.solon4ak.jm.model.User;

/**
 *
 * @author solon4ak
 */
public interface UserService {
    void save(User user);
    List<User> listAll();
    User get(Long id);
    void delete(Long id);
    void update(User user);
}

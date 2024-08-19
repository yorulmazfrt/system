package com.ad.system.service;

import com.ad.system.entity.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    User saveUser(User user);

    User updateUser(User user);

    void deleteUserById(Long id);

    List<User> getUsers();

    User getUserById(Long id);

    Optional<User> findUserByUsername(String userName);
}

package com.ad.system.service;

import com.ad.system.entity.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);
}

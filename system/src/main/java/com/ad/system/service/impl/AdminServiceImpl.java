package com.ad.system.service.impl;

import com.ad.system.entity.User;
import com.ad.system.repository.UserRepository;
import com.ad.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setId(user.getId());
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Boyle bir kullanici yok."));
        this.userRepository.deleteById(user.getId());
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Boyle bir kullanici yok."));
        return user;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

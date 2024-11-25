package com.vinhuni.booking.service.admin;


import com.vinhuni.booking.model.user.User;
import com.vinhuni.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
    }

    public void updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setFullName(updatedUser.getFullName());
        if (!updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
        }
        userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

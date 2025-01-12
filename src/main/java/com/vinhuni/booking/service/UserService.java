package com.vinhuni.booking.service;

import com.vinhuni.booking.model.user.Role;
import com.vinhuni.booking.model.user.User;
import com.vinhuni.booking.repository.RoleRepository;
import com.vinhuni.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    public boolean isUsernameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isEmailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    public boolean registerUser(String username,String password,
                                String email, Set<String> roleNames) {
        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }
        if (userRepository.findByEmail(email).isPresent()) {
            return false;
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        Set<Role> roles = new HashSet<>();

        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName);

            roles.add(role);
        }
        if (newUser.getEnabled() == null) {
            newUser.setEnabled(true);
        }
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return true;
    }

    public boolean loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}

package com.vinhuni.booking.service.user;


import com.vinhuni.booking.model.user.User;
import com.vinhuni.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}

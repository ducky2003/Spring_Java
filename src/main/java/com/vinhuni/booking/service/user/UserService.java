package com.vinhuni.booking.service.user;

import com.vinhuni.booking.model.user.User;

public interface UserService {
    User findByEmail(String email);
}

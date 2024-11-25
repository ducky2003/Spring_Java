package com.vinhuni.booking.service.user;


import com.vinhuni.booking.model.user.User;
import com.vinhuni.booking.model.user.UserDetail;
import com.vinhuni.booking.model.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        Set<UserRole> role = user.getUserRoles();
        for(UserRole userRole : role) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
        }
        return new UserDetail(user, grantedAuthoritySet);
    }
}

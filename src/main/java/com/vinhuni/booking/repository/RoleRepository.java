package com.vinhuni.booking.repository;

import com.vinhuni.booking.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role,Long>{
    Role findByName(String name);
}

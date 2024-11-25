package com.vinhuni.booking.repository;


import com.vinhuni.booking.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepositoy  extends JpaRepository<Services,Long> {
}

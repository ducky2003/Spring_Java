package com.vinhuni.booking.repository;

import com.vinhuni.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository <Booking,Long>{
    List<Booking> findByUserId(Long userId);
    List<Booking> findByRoomId(Long roomId);
    Optional<Booking> findById(Long id);

}

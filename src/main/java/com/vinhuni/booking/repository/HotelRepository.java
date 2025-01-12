package com.vinhuni.booking.repository;


import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.model.Region;
import com.vinhuni.booking.model.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT h FROM Hotel h WHERE h.region.id = :regionId")
    List<Hotel> findByRegionId(@Param("regionId") Integer regionId);
    Optional<Hotel> findById(Long hotelId);
    Page<Hotel> findByHotelNameContaining(String name, Pageable pageable);
    Page<Hotel> findByLocationContaining(String location, Pageable pageable);
    List<Hotel> findByHotelNameContainingIgnoreCaseOrLocationContainingIgnoreCase(String hotelName, String location);
}

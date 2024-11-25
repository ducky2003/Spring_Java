package com.vinhuni.booking.repository;


import com.vinhuni.booking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    @Query("SELECT h FROM Hotel h WHERE h.region.id = :regionId")
    List<Hotel> findByRegionId(@Param("regionId") Integer regionId);
}

package com.vinhuni.booking.repository;


import com.vinhuni.booking.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    @Query("SELECT r.id AS regionId, COUNT(h.id) AS hotelCount " +
            "FROM Hotel h RIGHT JOIN h.region r " +
            "GROUP BY r.id")
    List<Object[]> getRegionHotelCounts();

    Region findByRegionName(String regionName);
}

package com.vinhuni.booking.service;


import com.vinhuni.booking.model.Region;
import com.vinhuni.booking.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public List<Region> getLimitRegions(int limit) {
        return regionRepository.findAll().stream().limit(limit).toList();
    }

    public List<Region> getAllRegionsWithHotelCount() {
        List<Region> regions = regionRepository.findAll();
        List<Object[]> regionHotelCounts = regionRepository.getRegionHotelCounts();

        for (Region region : regions) {
            for (Object[] countResult : regionHotelCounts) {
                int regionId = (int) countResult[0];
                int hotelCount = ((Number) countResult[1]).intValue();

                if (region.getId() == regionId) {
                    region.setHotelCount(hotelCount);
                    break;
                }
            }
        }
        return regions;
    }
}

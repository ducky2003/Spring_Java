package com.vinhuni.booking.service;


import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    public List<Hotel> getHotelbyRegion(Integer regionId){
        return hotelRepository.findByRegionId(regionId);
    }
}

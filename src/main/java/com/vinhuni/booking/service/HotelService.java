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
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
    }
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
    public List<Hotel> searchHotelsByNameOrLocation(String keyword) {
        return hotelRepository.findByHotelNameContainingIgnoreCaseOrLocationContainingIgnoreCase(keyword, keyword);
    }
}

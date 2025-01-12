package com.vinhuni.booking.service.admin;

import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.model.Region;
import com.vinhuni.booking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminHotelService {
    @Autowired
    private HotelRepository hotelRepository;
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
    public Page<Hotel> getHotels(Pageable pageable) {
        return hotelRepository.findAll(pageable);
    }
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
    public Hotel updateHotel(Integer id, Hotel hotel) {
        hotel.setId(id);
        return hotelRepository.save(hotel);
    }
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }
    public Page<Hotel> findByNameContaining(String name, Pageable pageable) {
        return hotelRepository.findByHotelNameContaining(name, pageable);
    }
    public Page<Hotel> findByLocationContaining(String location, Pageable pageable) {
        return hotelRepository.findByLocationContaining(location, pageable);
    }
}

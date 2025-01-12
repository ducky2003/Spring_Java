package com.vinhuni.booking.service;

import com.vinhuni.booking.model.Room;
import com.vinhuni.booking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    public List<Room> searchRooms(String roomType, Double priceFrom, Double priceTo, String hotelName, String location, Integer floor) {
        return roomRepository.searchRooms(roomType, priceFrom, priceTo, hotelName, location, floor);
    }
    public List<Room> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }
    public Optional<Room> getRoomById(Long roomId) {
        return roomRepository.findById(roomId);
    }
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }
    public boolean isRoomAvailable(Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        return room != null && room.getAvailable();
    }
}
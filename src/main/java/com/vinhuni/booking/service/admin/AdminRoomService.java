package com.vinhuni.booking.service.admin;

import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.model.Room;
import com.vinhuni.booking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRoomService {
    @Autowired
    private RoomRepository roomRepository;
    public Page<Room> getAllRooms(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }
    public Room updateRoom(Long id, Room room) {
        room.setId(id);
        return roomRepository.save(room);
    }
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }
    public List<Room> getRoomsByHotel(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }
    public Page<Room> findByRoomType(String name, Pageable pageable) {
        return roomRepository.findByRoomType(name, pageable);
    }
    public List<Room> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }
}

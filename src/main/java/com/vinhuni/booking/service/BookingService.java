package com.vinhuni.booking.service;

import com.vinhuni.booking.model.Booking;
import com.vinhuni.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    public void cancelBooking(Booking booking) {
        bookingRepository.delete(booking);
    }
    public List<Booking> getBookingsByRoomId(Long roomId) {
        return bookingRepository.findByRoomId(roomId);
    }
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }
}

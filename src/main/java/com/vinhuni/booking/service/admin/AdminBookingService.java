package com.vinhuni.booking.service.admin;

import com.vinhuni.booking.model.Booking;
import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class AdminBookingService {
    @Autowired
    private BookingRepository bookingRepository;
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    public Page<Booking> getBookings(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    public Booking updateBooking(Long id, Booking booking) {
        booking.setId(id);
        return bookingRepository.save(booking);
    }
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

}

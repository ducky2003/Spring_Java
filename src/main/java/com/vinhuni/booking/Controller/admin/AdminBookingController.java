package com.vinhuni.booking.Controller.admin;

import com.vinhuni.booking.model.Booking;
import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.model.Region;
import com.vinhuni.booking.model.Room;
import com.vinhuni.booking.model.user.User;
import com.vinhuni.booking.service.admin.AdminBookingService;
import com.vinhuni.booking.service.admin.AdminHotelService;
import com.vinhuni.booking.service.admin.AdminRoomService;
import com.vinhuni.booking.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("admin/bookings")
public class AdminBookingController {
    @Autowired
    private AdminBookingService adminBookingService;
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminRoomService adminRoomService;
    @Autowired
    private AdminHotelService adminHotelService;
    @GetMapping
    public String listHotels(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(required = false) String search,
                             @RequestParam(required = false) String searchBy
            , Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Booking> bookingPage = adminBookingService.getBookings(pageable);
        model.addAttribute("bookingPage", bookingPage);
        model.addAttribute("search", search);
        model.addAttribute("searchBy", searchBy);
        return "admin/bookings/index";
    }
    @PostMapping("/update-status")
    public String updateBookingStatus(@RequestParam("bookingId") Long bookingId,
                                      @RequestParam("status") String status) {
        Booking booking = adminBookingService.getBookingById(bookingId);
        booking.setStatus(status);
        adminBookingService.saveBooking(booking);
        return "redirect:/admin/bookings";
    }
    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") Long id) {
        adminBookingService.deleteBooking(id);
        return "redirect:/admin/bookings";
    }
}

package com.vinhuni.booking.Controller;


import com.vinhuni.booking.model.*;

import com.vinhuni.booking.model.user.User;
import com.vinhuni.booking.service.*;
import com.vinhuni.booking.service.BookingService;
import com.vinhuni.booking.service.HotelService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;
    @RequestMapping("/home")
    public String home(HttpSession session, Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("full_name", user.getFullName());
            boolean isAdmin = user.getRoles().stream().anyMatch(role -> "ADMIN".equals(role.getName()));
            model.addAttribute("isAdmin", isAdmin);
        }
        model.addAttribute("user", user);
        List<Services> services = serviceService.getLimitedServices(4);
        model.addAttribute("services", services);
        List<Region> regions = regionService.getAllRegionsWithHotelCount();
        model.addAttribute("regions", regions);
        List<Hotel> hotels;
        if (keyword != null && !keyword.isEmpty()) {
            hotels = hotelService.searchHotelsByNameOrLocation(keyword);
        } else {
            hotels = hotelService.getAllHotels();
        }
        model.addAttribute("hotels", hotels);
        return "index";
    }
    @GetMapping("/services")
    public String allServices(Model model) {
        List<Services> services = serviceService.getAllServices();
        model.addAttribute("services", services);
        return "components/services";
    }
    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }
        List<Booking> bookings = bookingService.getBookingsByUserId(user.getId());
        for (Booking booking : bookings) {
            Optional<Room> roomOptional = roomService.getRoomById(booking.getRoom().getId());
            if (roomOptional.isPresent()) {
                Room room = roomOptional.get();
                Hotel hotel = room.getHotel();
                if (hotel != null) {
                    booking.setHotel(hotel);
                }
            }
        }
        model.addAttribute("bookings", bookings);
        model.addAttribute("user", user);
        return "components/profile";
    }
    @GetMapping("/rooms/search")
    public String searchRooms(
            @RequestParam(required = false) String roomType,
            @RequestParam(required = false) Double priceFrom,
            @RequestParam(required = false) Double priceTo,
            @RequestParam(required = false) String hotelName,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer floor,
            Model model) {
        List<Room> rooms = roomService.searchRooms(roomType, priceFrom, priceTo, hotelName, location, floor);
        model.addAttribute("rooms", rooms);
        return "components/rooms";
    }
}

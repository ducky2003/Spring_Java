package com.vinhuni.booking.Controller;

import com.vinhuni.booking.model.Booking;
import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.model.Room;
import com.vinhuni.booking.model.user.User;
import com.vinhuni.booking.service.BookingService;
import com.vinhuni.booking.service.HotelService;
import com.vinhuni.booking.service.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private HotelService hotelService;
    @GetMapping("/rooms/detail/{hotelId}")
    public String viewRooms(@PathVariable Long hotelId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("full_name", user.getFullName());
            boolean isAdmin = user.getRoles().stream().anyMatch(role -> "ADMIN".equals(role.getName()));
            model.addAttribute("isAdmin", isAdmin);
        }
        List<Room> rooms1 = roomService.getRoomsByHotelId(hotelId);
        for (Room room : rooms1) {
            boolean isAvailable = roomService.isRoomAvailable(room.getId());
            room.setIsAvailable(isAvailable);
        }
        model.addAttribute("user", user);
        Hotel hotel = hotelService.getHotelById(hotelId);
        List<Room> rooms = roomService.getRoomsByHotelId(hotelId);
        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms", rooms);
        return "components/room-details";
    }

    @GetMapping("/rooms/booking/{roomId}")
    public String showBookingForm(@PathVariable Long roomId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            String currentUrl = "/rooms/booking/" + roomId;
            session.setAttribute("prevUrl", currentUrl);
            return "redirect:/auth/login";
        }
        if (!roomService.isRoomAvailable(roomId)) {
            model.addAttribute("errorMessage", "This room is currently unavailable.");
            return "components/room-details";
        }
        if (user != null) {
            model.addAttribute("full_name", user.getFullName());
            boolean isAdmin = user.getRoles().stream().anyMatch(role -> "ADMIN".equals(role.getName()));
            model.addAttribute("isAdmin", isAdmin);
        }
        model.addAttribute("user", user);

        Room room = roomService.getRoomById(roomId).orElse(null); // Room may be null
        if (room == null) {
            model.addAttribute("errorMessage", "Room not found.");
            return "redirect:/404";
        }
        model.addAttribute("room", room);
        return "components/booking-form";

    }

    @PostMapping("/rooms/booking/{roomId}")
    public String bookRoom(@RequestParam Long roomId,
                           @RequestParam String checkInDate,
                           @RequestParam String checkOutDate,
                           HttpSession session,
                           Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("full_name", user.getFullName());
            boolean isAdmin = user.getRoles().stream().anyMatch(role -> "ADMIN".equals(role.getName()));
            model.addAttribute("isAdmin", isAdmin);
        }

        model.addAttribute("user", user);
        Room room = roomService.getRoomById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
        if (room != null && room.getImageUrl() != null) {
            model.addAttribute("roomImage", room.getImageUrl());
        } else {
            return "redirect:/error";
        }
        if (room == null) {
            model.addAttribute("error", "Room not found");
            return "404";
        }
        LocalDate checkIn = LocalDate.parse(checkInDate);
        LocalDate checkOut = LocalDate.parse(checkOutDate);
        List<Booking> existingBookings = bookingService.getBookingsByRoomId(roomId);
        for (Booking existingBooking : existingBookings) {
            if ((checkIn.isBefore(existingBooking.getCheckOutDate()) && checkOut.isAfter(existingBooking.getCheckInDate()))) {
                model.addAttribute("errorMessage", "This room is already booked during the selected period.");
                return "components/booking-form";
            }
        }

        long daysBetween = ChronoUnit.DAYS.between(checkIn, checkOut);
        BigDecimal totalPrice = room.getPrice().multiply(BigDecimal.valueOf(daysBetween));
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckInDate(checkIn);
        booking.setCheckOutDate(checkOut);
        booking.setTotalPrice(totalPrice);
        booking.setStatus("PENDING");
        bookingService.saveBooking(booking);
        room.setAvailable(false);
        roomService.saveRoom(room);
        model.addAttribute("booking", booking);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("room", room);
        model.addAttribute("user", user);
        model.addAttribute("hotel", room.getHotel());
        return "confirmation";

    }

    @GetMapping("/rooms/booking/confirmation")
    public String showConfirmationPage(Model model) {

        model.addAttribute("message", "Booking confirmed successfully!");
        return "confirmation";
    }

}
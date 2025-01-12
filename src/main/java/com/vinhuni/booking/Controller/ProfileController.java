package com.vinhuni.booking.Controller;

import com.vinhuni.booking.model.Booking;
import com.vinhuni.booking.model.Room;
import com.vinhuni.booking.model.user.User;
import com.vinhuni.booking.service.BookingService;
import com.vinhuni.booking.service.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @PostMapping("/cancel/{bookingId}")
    public String cancelBooking(@PathVariable Long bookingId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking != null && booking.getStatus().equals("PENDING")) {
            booking.setStatus("CANCELLED");
            bookingService.saveBooking(booking);

            Room room = booking.getRoom();
            if (room != null) {
                room.setAvailable(true);
                roomService.saveRoom(room);
            }
            model.addAttribute("message", "Booking cancelled successfully.");
            return "redirect:/profile";
        } else {
            session.setAttribute("error", "Unable to cancel the booking.");
        }
        return "redirect:/profile";
    }

}

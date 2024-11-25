package com.vinhuni.booking.Controller;


import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @GetMapping("/hotels/{regionId}")
    public String getHotelbyReion(@PathVariable("regionId") Integer regionId, Model model) {
        List<Hotel> hotels = hotelService.getHotelbyRegion(regionId);
        model.addAttribute("hotels", hotels);
        return "components/hotels";
    }
}

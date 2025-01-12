package com.vinhuni.booking.Controller.admin;

import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.model.Region;
import com.vinhuni.booking.service.admin.AdminHotelService;
import com.vinhuni.booking.service.admin.AdminRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("admin/hotels")
public class AdminHotelController {
    @Autowired
    private AdminHotelService adminHotelService;
    @Autowired
    private AdminRegionService adminRegionService;
    @GetMapping
    public String listHotels(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(required = false) String search,
                             @RequestParam(required = false) String searchBy
                             ,Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Hotel> hotelPage = adminHotelService.getHotels(pageable);
        if (search != null && !search.isEmpty() && searchBy != null) {
            switch (searchBy) {
                case "name":
                    hotelPage = adminHotelService.findByNameContaining(search, pageable);
                    break;
                case "location":
                    hotelPage = adminHotelService.findByLocationContaining(search, pageable);
                    break;
                default:
                    hotelPage = adminHotelService.getHotels(pageable);
            }
        } else {
            hotelPage = adminHotelService.getHotels(pageable);
        }
        model.addAttribute("hotelPage", hotelPage);
        model.addAttribute("search", search);
        model.addAttribute("searchBy", searchBy);
        return "admin/hotels/index";
    }
    @GetMapping("/add")
    public String addHotelForm(Model model) {
        Hotel hotel = new Hotel();
        List<Region> regions = adminRegionService.getAllRegions();
        model.addAttribute("hotel", hotel);
        model.addAttribute("regions", regions);
        return "admin/hotels/add";
    }
    @PostMapping("/add")
    public String addHotel(@ModelAttribute("hotel") Hotel hotel,
                           @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/fe/img";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            hotel.setImageUrl(fileName);
        }
        adminHotelService.saveHotel(hotel);
        return "redirect:/admin/hotels";
    }
    @GetMapping("/edit/{id}")
    public String editHotelForm(@PathVariable("id") Long id, Model model) {
        Hotel hotel = adminHotelService.getHotelById(id);
        List<Region> regions = adminRegionService.getAllRegions();
        model.addAttribute("hotel", hotel);
        model.addAttribute("regions", regions);
        return "admin/hotels/add";
    }

    @PostMapping("/edit/{id}")
    public String editHotel(@PathVariable("id") Integer id,
                            @ModelAttribute("hotel") Hotel hotel,
                            @RequestParam("file") MultipartFile file) throws IOException {
        hotel.setId(id);
        if (!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/fe/img";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            hotel.setImageUrl(fileName);
        }
        adminHotelService.saveHotel(hotel);
        return "redirect:/admin/hotels";
    }

    @GetMapping("/delete/{id}")
    public String deleteHotel(@PathVariable("id") Long id) {
        adminHotelService.deleteHotel(id);
        return "redirect:/admin/hotels";
    }
}

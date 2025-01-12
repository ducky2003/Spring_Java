package com.vinhuni.booking.Controller.admin;

import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.model.Region;
import com.vinhuni.booking.model.Room;
import com.vinhuni.booking.service.admin.AdminHotelService;
import com.vinhuni.booking.service.admin.AdminRoomService;
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
@RequestMapping("admin/rooms")
public class AdminRoomController {
    @Autowired
    private AdminRoomService adminRoomService;
    @Autowired
    private AdminHotelService adminHotelService;
    @GetMapping
    public String listHotels(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(required = false) String search,
                             @RequestParam(required = false) String searchBy,
                             @RequestParam(value = "roomType", required = false) String roomType
                            ,Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Room> roomPage = adminRoomService.getAllRooms(pageable);
        if (roomType != null && !roomType.isEmpty()) {
            roomPage = adminRoomService.findByRoomType(roomType, pageable);
        }
        else {
            roomPage = adminRoomService.getAllRooms(pageable);
        }
        model.addAttribute("roomPage", roomPage);
        model.addAttribute("search", search);
        model.addAttribute("searchBy", searchBy);
        model.addAttribute("roomType", roomType);
        return "admin/rooms/index";
    }
    @GetMapping("/add")
    public String addRoomForm(Model model) {
        Room room = new Room();
        List<Hotel> hotels = adminHotelService.getAllHotels();
        model.addAttribute("room", room);
        model.addAttribute("hotels", hotels);
        return "admin/rooms/add";
    }
    @PostMapping("/add")
    public String addRoom(@ModelAttribute("room") Room room,
                           @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/fe/img";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            room.setImageUrl(fileName);
        }
        adminRoomService.saveRoom(room);
        return "redirect:/admin/rooms";
    }
    @GetMapping("/edit/{id}")
    public String editRoomForm(@PathVariable("id") Long id, Model model) {
        Room room = adminRoomService.getRoomById(id);
        List<Hotel> hotels = adminHotelService.getAllHotels();
        model.addAttribute("room", room);
        model.addAttribute("hotels", hotels);
        return "admin/rooms/add";
    }

    @PostMapping("/edit/{id}")
    public String editRoom(@PathVariable("id") Long id,
                            @ModelAttribute("room") Room room,
                            @RequestParam("file") MultipartFile file) throws IOException {
        Room existingRoom = adminRoomService.getRoomById(id);
        room.setId(id);
        if (file != null && !file.isEmpty()) {
            String uploadDir = "src/main/resources/static/fe/img";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            room.setImageUrl(fileName);
        } else {
            room.setImageUrl(existingRoom.getImageUrl());
        }
        adminRoomService.saveRoom(room);
        return "redirect:/admin/rooms";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") Long id) {
        adminRoomService.deleteRoom(id);
        return "redirect:/admin/rooms";
    }
    @GetMapping("/{roomId}")
    @ResponseBody
    public Room getRoomDetails(@PathVariable Long roomId) {
        return adminRoomService.getRoomById(roomId);
    }
}

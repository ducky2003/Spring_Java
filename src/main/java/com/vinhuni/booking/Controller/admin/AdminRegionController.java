package com.vinhuni.booking.Controller.admin;

import com.vinhuni.booking.model.Region;
import com.vinhuni.booking.service.admin.AdminRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/admin/regions")
public class AdminRegionController {
    @Autowired
    private AdminRegionService adminRegionService;
    @GetMapping
    public String adminRegions(Model model) {
        model.addAttribute("adminRegions", adminRegionService.getAllRegions());
        return "admin/regions/index";
    }
    @RequestMapping("/add")
    public String addRegion(Model model) {
        model.addAttribute("region", new Region());
        return "admin/regions/add";
    }
    @PostMapping("/add")
    public String addRegion(@ModelAttribute("region") Region region,
                            @RequestParam("file") MultipartFile file) throws IOException {
        if (region.getRegionName() == null || region.getRegionName().isEmpty()) {
            region.setRegionName("Unnamed Region");
        }

        if(!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/fe/img";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir,fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            region.setImage(fileName);
        }
        adminRegionService.saveRegion(region);
        return "redirect:/admin/regions";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Region region = adminRegionService.getRegionId(id); // Lấy vùng miền từ DB
        if (region == null) {
            throw new IllegalArgumentException("Invalid region ID: " + id);
        }
        model.addAttribute("region", region); // Đưa dữ liệu vào model
        return "admin/regions/edit"; // Trả về form chỉnh sửa
    }
    @PostMapping("/edit/{id}")
    public String editRegion(@PathVariable Integer id,
                             @ModelAttribute("region") Region region,
                             @RequestParam("file") MultipartFile file) throws IOException {
        Region existingRegion = adminRegionService.getRegionId(id);
        existingRegion.setRegionName(region.getRegionName());
        existingRegion.setDescription(region.getDescription());
        if (!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/fe/img";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            existingRegion.setImage(fileName);
        }
        existingRegion.setRegionName(region.getRegionName());
        existingRegion.setDescription(region.getDescription());
        adminRegionService.saveRegion(existingRegion);
        return "redirect:/admin/regions";
    }

    @GetMapping("/delete/{id}")
    public String deleteRegion(@PathVariable Integer id) {
        adminRegionService.deleteRegion(id);
        return "redirect:/admin/regions";
    }
}

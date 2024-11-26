package com.vinhuni.booking.Controller.admin;

import com.vinhuni.booking.model.Membershipplan;
import com.vinhuni.booking.service.MemberShipService;
import com.vinhuni.booking.service.admin.AdminMemberShipService;
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
public class AdminMemberShipController {
    @Autowired
    private AdminMemberShipService adminMemberShipService;
    @Autowired
    private MemberShipService memberShipService;

    @RequestMapping("admin/memberships")
    public String adminMemberShip (Model model) {
        model.addAttribute("members", adminMemberShipService.getAllMembershipplans());
        return "admin/memberships/index";
    }
    @RequestMapping("admin/memberships/add")
    public String adminMemberShipAdd (Model model) {
        model.addAttribute("members", new Membershipplan());
        return "admin/memberships/add";
    }
    @PostMapping("admin/memberships/add")
    public String adminMemberShipAdd (@ModelAttribute("members") Membershipplan membershipplan,
                                      @RequestParam("file") MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/fe/img";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir,fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            membershipplan.setImage(fileName);
        }
        adminMemberShipService.saveMembershipplan(membershipplan);
        return "redirect:/admin/memberships";
    }
    @RequestMapping("admin/memberships/edit/{id}")
    public String adminMemberShipEdit (@PathVariable("id") Integer id, Model model) {
        Membershipplan membershipplan = adminMemberShipService.getMembershipplanById(id);
        model.addAttribute("members", membershipplan);
        return "admin/memberships/add";
    }
    @PostMapping("admin/memberships/edit/{id}")
    public String adminMemberShipEdit (@PathVariable Integer id, @ModelAttribute("members")  Membershipplan membershipplan,
                                       @RequestParam("file") MultipartFile file) throws IOException {
        Membershipplan members = adminMemberShipService.getMembershipplanById(id);
        members.setName(membershipplan.getName());
        members.setDescription(membershipplan.getDescription());
        members.setDiscountRate(membershipplan.getDiscountRate());
        members.setMonthlyFee(membershipplan.getMonthlyFee());
        members.setMaxBookingsPerMonth(membershipplan.getMaxBookingsPerMonth());
        if (!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/fe/img";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            members.setImage(fileName);
        }
        members.setName(membershipplan.getName());
        members.setDescription(membershipplan.getDescription());
        members.setDiscountRate(membershipplan.getDiscountRate());
        members.setMonthlyFee(membershipplan.getMonthlyFee());
        members.setMaxBookingsPerMonth(membershipplan.getMaxBookingsPerMonth());
        adminMemberShipService.saveMembershipplan(members);
        return "redirect:/admin/memberships";
    }
    @GetMapping("admin/memberships/delete/{id}")
    public String adminMemberShipDelete (@PathVariable("id") Integer id) {
        adminMemberShipService.deleteMembershipplan(id);
        return "redirect:/admin/memberships";
    }
}

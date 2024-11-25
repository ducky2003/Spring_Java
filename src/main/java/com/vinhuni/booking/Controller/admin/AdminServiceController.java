package com.vinhuni.booking.Controller.admin;


import com.vinhuni.booking.model.Services;
import com.vinhuni.booking.service.admin.AdminServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class AdminServiceController {
    @Autowired
    private AdminServiceService adminServiceService;
    @RequestMapping("admin/services")
    public String services(Model model) {
        model.addAttribute("services", adminServiceService.getAllService());
        return "admin/services/index";
    }
    @RequestMapping("admin/services/add")
    public String addService(Model model) {
        model.addAttribute("service", new Services());
        return "admin/services/add";
    }
    @PostMapping("admin/services/add")
    public String saveService(@ModelAttribute("service") Services service) {
        adminServiceService.saveService(service);
        return "redirect:/admin/services";
    }
    @GetMapping("admin/services/edit/{id}")
    public String editServiceForm(@PathVariable("id") Long id, Model model) {
        Services service = adminServiceService.getServiceById(id);
        model.addAttribute("service", service);
        return "admin/services/add";
    }
    @PostMapping("admin/services/edit/{id}")
    public String updateService(@PathVariable("id") int id, @ModelAttribute("service") Services service) {
        service.setId(id);
        adminServiceService.saveService(service);
        return "redirect:/admin/services";
    }
    @GetMapping("admin/services/delete/{id}")
    public String deleteService(@PathVariable("id") Long id) {
        adminServiceService.deleteService(id);
        return "redirect:/admin/services";
    }
}

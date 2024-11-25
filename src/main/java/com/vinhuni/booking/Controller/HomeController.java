package com.vinhuni.booking.Controller;

import com.vinhuni.booking.model.Region;
import com.vinhuni.booking.model.Services;
import com.vinhuni.booking.service.RegionService;
import com.vinhuni.booking.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private RegionService regionService;
    @RequestMapping("/")
    public String home(Model model) {
        List<Services> services = serviceService.getLimitedServices(6);
        model.addAttribute("services", services);
        List<Region> regions = regionService.getAllRegionsWithHotelCount();
        model.addAttribute("regions", regions);
        return "index";
    }
    @GetMapping("/services")
    public String allServices(Model model) {
        List<Services> services = serviceService.getAllServices();
        model.addAttribute("services", services);
        return "components/services";
    }
}

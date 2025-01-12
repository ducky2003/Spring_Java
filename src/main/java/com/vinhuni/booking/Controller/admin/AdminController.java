package com.vinhuni.booking.Controller.admin;

import com.vinhuni.booking.model.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
@GetMapping
public String index() {
    return "redirect:/admin/";
}
    @RequestMapping("/")
    public String admin(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }
        boolean hasAdminRole = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ADMIN"));

        if (!hasAdminRole) {
            return "redirect:/home";
        }
        return "admin/index";
    }
}

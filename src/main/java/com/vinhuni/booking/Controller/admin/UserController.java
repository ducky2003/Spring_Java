package com.vinhuni.booking.Controller.admin;


import com.vinhuni.booking.model.user.User;
import com.vinhuni.booking.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class UserController {
    @RequestMapping("/404")
    public String error() {
        return "404";
    }
    @Autowired
    private AdminUserService adminUserService;
    @RequestMapping("admin/users")
    public String listUsers(Model model) {
        model.addAttribute("users", adminUserService.getAllUsers());
        return "admin/user/index";
    }
    @GetMapping("admin/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/add";
    }
    @PostMapping("admin/users/add")
    public String saveUser(@ModelAttribute User user) {
        adminUserService.saveUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping("admin/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = adminUserService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/edit";
    }
    @PostMapping("admin/users/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        User existingUser = adminUserService.getUserById(id);
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(existingUser.getPassword());
        }
        adminUserService.updateUser(id, user);
        return "redirect:/admin/users";
    }
    @GetMapping("admin/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        adminUserService.deleteUser(id);
        return "redirect:/admin/users";
    }

}

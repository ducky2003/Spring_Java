package com.vinhuni.booking.Controller;

import com.vinhuni.booking.model.user.User;
import com.vinhuni.booking.repository.UserRepository;
import com.vinhuni.booking.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam Set<String> roles) {
        boolean success = userService.registerUser(username, password, email, roles);
        if (userService.userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username đã tồn tại");
        }
        if (userService.userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email da ton tai");
        }
        if (success) {
            return ResponseEntity.ok("Đăng ký thành công");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Người dùng đã tồn tại");
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
        User user = optionalUser.get();
        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
        session.setAttribute("user", user);
        String prevUrl = (String) session.getAttribute("prevUrl");
        if (prevUrl != null) {

            return "redirect:" + prevUrl;
        }
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }
    @GetMapping("/api/currentUser")
    @ResponseBody
    public ResponseEntity<User> getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

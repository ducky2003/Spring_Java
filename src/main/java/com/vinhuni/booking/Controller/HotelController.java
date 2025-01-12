package com.vinhuni.booking.Controller;


import com.vinhuni.booking.model.Article;
import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.model.Room;
import com.vinhuni.booking.model.user.User;
import com.vinhuni.booking.service.ArticleService;
import com.vinhuni.booking.service.HotelService;

import com.vinhuni.booking.service.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ArticleService articleService;
    @GetMapping("/hotels/{regionId}")
    public String getHotelbyReion(@PathVariable("regionId") Integer regionId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("full_name", user.getFullName());
            boolean isAdmin = user.getRoles().stream().anyMatch(role -> "ADMIN".equals(role.getName()));
            model.addAttribute("isAdmin", isAdmin);
        }
        model.addAttribute("user", user);
        List<Hotel> hotels = hotelService.getHotelbyRegion(regionId);
        model.addAttribute("hotels", hotels);
        return "components/hotels";

    }

    @GetMapping("/detail-hotel/{hotelId}")
    public String viewHotelDetails(@PathVariable Long hotelId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("full_name", user.getFullName());
            boolean isAdmin = user.getRoles().stream().anyMatch(role -> "ADMIN".equals(role.getName()));
            model.addAttribute("isAdmin", isAdmin);
        }
        model.addAttribute("user", user);
        Hotel hotel = hotelService.getHotelById(hotelId);
        List<Room> rooms = roomService.getRoomsByHotelId(hotelId);
        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms", rooms);

        return "components/detail-hotel";
    }
    @GetMapping("/hotels/{hotelId}/articles")
    public String viewHotelArticles(@PathVariable Long hotelId, Model model,
                                    @RequestParam(required = false) Long articleId) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        if (hotel == null) {
            throw new IllegalArgumentException("Khách sạn không tồn tại với ID: " + hotelId);
        }
        List<Article> articles = articleService.findByHotelId(hotelId);
        if (articles.isEmpty()) {
            model.addAttribute("error", "Không có bài viết nào.");
            return "components/hotel-articles";
        }
        Article mainArticle = articleId != null
                ? articles.stream().filter(a -> a.getId().equals(articleId)).findFirst().orElse(articles.get(0))
                : articles.get(0);
        List<Article> recentPosts = articles.stream().filter(a -> !a.getId().equals(mainArticle.getId())).toList();
        model.addAttribute("hotel", hotel);
        model.addAttribute("articles", articles);
        model.addAttribute("mainArticle", mainArticle);
        model.addAttribute("recentPosts", recentPosts);
        return "components/hotel-articles";
    }
}

package com.vinhuni.booking.Controller.admin;

import com.vinhuni.booking.model.Article;
import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.model.Region;
import com.vinhuni.booking.model.Room;
import com.vinhuni.booking.repository.ArticleRepository;
import com.vinhuni.booking.service.admin.AdminArticleService;
import com.vinhuni.booking.service.admin.AdminHotelService;
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
@RequestMapping("admin/articles")
public class AdminArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AdminArticleService articleService;

    @Autowired
    private AdminHotelService hotelService;

    @GetMapping
    public String listArticles(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page <Article> articlePage = articleService.getArticlesWithShortContent(pageable);
        articlePage = articleService.getArticles(pageable);
        model.addAttribute("articlePage", articlePage);
        return "admin/articles/index";
    }
    @GetMapping("/add")
    public String addArticleForm(Model model) {
        Article article = new Article();
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("article", article);
        model.addAttribute("hotels", hotels);
        return "admin/articles/add";
    }
    @PostMapping("/add")
    public String addArticle(@ModelAttribute("article") Article article,
                           @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/fe/img";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            article.setImage(fileName);
        }
        articleService.saveArticle(article);
        return "redirect:/admin/articles";
    }
    @GetMapping("/edit/{id}")
    public String editArticleForm(@PathVariable("id") Long id, Model model) {
        Article article = articleService.getArticleById(id);
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("article", article);
        model.addAttribute("hotels", hotels);
        return "admin/articles/add";
    }

    @PostMapping("/edit/{id}")
    public String editArticle(@PathVariable("id") Long id,
                            @ModelAttribute("article") Article article,
                            @RequestParam("file") MultipartFile file) throws IOException {
        Article article1 = articleService.getArticleById(id);
        article.setId(id);
        if (file != null && !file.isEmpty()) {
            String uploadDir = "src/main/resources/static/fe/img";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            article.setImage(fileName);
        }else {
            article.setImage(article1.getImage());
        }
        articleService.saveArticle(article);
        return "redirect:/admin/articles";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteArticle(id);
        return "redirect:/admin/articles";
    }
    @GetMapping("/detail/{id}")
    public String viewArticleDetail(@PathVariable Long id, Model model) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article ID: " + id));
        model.addAttribute("article", article);
        return "admin/articles/detail";
    }
}

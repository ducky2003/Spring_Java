package com.vinhuni.booking.service;

import com.vinhuni.booking.model.Article;
import com.vinhuni.booking.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
    public List<Article> findByHotelId(Long hotelId) {
        return articleRepository.findByHotelId(hotelId);
    }
}

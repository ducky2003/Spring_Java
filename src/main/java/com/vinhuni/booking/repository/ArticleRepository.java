package com.vinhuni.booking.repository;

import com.vinhuni.booking.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByHotelId(Long hotelId);
}

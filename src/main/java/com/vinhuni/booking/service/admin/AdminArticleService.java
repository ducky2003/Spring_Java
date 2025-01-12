package com.vinhuni.booking.service.admin;

import com.vinhuni.booking.model.Article;
import com.vinhuni.booking.model.Hotel;
import com.vinhuni.booking.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getArticlesByHotelId(Long hotelId) {
        return articleRepository.findByHotelId(hotelId);
    }
    public Page<Article> getArticles(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    public Article getArticleById(Long articleId) {
        return articleRepository.findById(articleId).orElse(null);
    }
    public Page<Article> getArticlesWithShortContent(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        articles.forEach(article -> {
            String content = article.getContent();
            article.setContent(content.length() > 50 ? content.substring(0, 50) + "..." : content);
        });

        return articles;
    }
}

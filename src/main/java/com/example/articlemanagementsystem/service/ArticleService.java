package com.example.articlemanagementsystem.service;

import com.example.articlemanagementsystem.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    List<Article> getArticles();
    Article getArticleById(int id);
    List<Article> getArticlesByAuthorName(String name);
    List<Article> getArticleByCategoryId(int id);
    void addNewArticle(Article article);
    void deleteArticle(int id);
    void editArticle(int id, Article article);
}

package com.example.articlemanagementsystem.service.ServiceImpl;

import com.example.articlemanagementsystem.model.Article;
import com.example.articlemanagementsystem.repository.ArticleRepository;
import com.example.articlemanagementsystem.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;
    ArticleServiceImpl(){
        articleRepository = new ArticleRepository();
    }
    @Override
    public List<Article> getArticles() {
        return articleRepository.getArticles();
    }

    @Override
    public Article getArticleById(int id) {
        return articleRepository.getArticleById(id);
    }

    @Override
    public List<Article> getArticlesByAuthorName(String name) {
        return articleRepository.getArticleByAuthorName(name);
    }

    @Override
    public List<Article> getArticleByCategoryId(int id) {
        return articleRepository.getArticleByCategoryId(id);
    }

    @Override
    public void addNewArticle(Article article) {

    }
    @Override
    public void deleteArticle(int id) {
        articleRepository.deleteArticle(id);
    }
}

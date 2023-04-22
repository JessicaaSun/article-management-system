package com.example.articlemanagementsystem.repository;

import com.example.articlemanagementsystem.model.Article;
import com.example.articlemanagementsystem.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ArticleRepository {
    private List<Article> articles = new ArrayList<>(){{
        add(new Article(6, "The Art of Procrastination", "I could tell you all about the benefits of procrastination, but I'll just do it later.", "https://i.pinimg.com/564x/ec/f4/64/ecf464ef13d817120638df55cfc1aa13.jpg",
                new AuthorRepository().getAuthors().get(0), new Category[]{new CategoryRepository().getCategories().get(3), new CategoryRepository().getCategories().get(4)}));
        add(new Article(1, "ALIEN", "alien is taking over the world", "https://i.pinimg.com/564x/19/d8/51/19d851065d0fa43c356328bcec240bf9.jpg",
                new AuthorRepository().getAuthors().get(0), new Category[]{new CategoryRepository().getCategories().get(0), new CategoryRepository().getCategories().get(2)}));
        add(new Article(2, "How to wake up early",
                "Scientist found a way to wake up early: you have to go to sleep early.", "https://i.pinimg.com/564x/e8/a6/29/e8a6295025285f37aeb1a9ecbd9c642f.jpg",
                new AuthorRepository().getAuthors().get(1), new Category[]{new CategoryRepository().getCategories().get(1), new CategoryRepository().getCategories().get(0), new CategoryRepository().getCategories().get(2)}));
        add(new Article(3, "Notice your self-obsession", "How to know you love yourself too much that turn you into a narcissist" +
                "death", "https://i.pinimg.com/736x/a1/d6/02/a1d602b3cefe4553db2b4c780c6fefa3.jpg",
                new AuthorRepository().getAuthors().get(2),   new Category[]{new CategoryRepository().getCategories().get(1), new CategoryRepository().getCategories().get(3)}));
        add(new Article(4, "The most delicous coffee", "There are many ways to indulge your caffeine fix, beyond the Americano.", "https://i.pinimg.com/564x/7d/b9/01/7db9017c2ab0ffa801f41eb59e9f9fb4.jpg",
                new AuthorRepository().getAuthors().get(3),   new Category[]{new CategoryRepository().getCategories().get(4)}));
        add(new Article(5, "The Joy of Coffee", "I like my coffee like I like my humor - dark and bitter." +
                "death", "https://i.pinimg.com/564x/f9/0d/4e/f90d4e708f4f288e7c51ecb2b2add425.jpg",
                new AuthorRepository().getAuthors().get(2),   new Category[]{new CategoryRepository().getCategories().get(1), new CategoryRepository().getCategories().get(0)}));
    }};
    public List<Article> getArticles(){
        return articles;
    }
    public Article getArticleById(int id){
        return articles.stream().filter(e->e.getId()==id).findFirst().orElse(null);
    }

    public void addNewArticle(Article article){
        articles.add(article);
    }

    public void editArticle(int id, Article newArticle){
        for(int i=0; i<articles.size(); i++){
            Article article = articles.get(i);
            if(article.getId()==id){
                articles.set(i, newArticle);
            }
        }
    }

    // ***
    public List<Article> getArticleByAuthorName(String name) {
        return articles.stream().filter(e-> Objects.equals(e.getAuthor().getUsername(), name)).toList();
    }

    public List<Article> getArticleByCategoryId(int id){
        return articles.stream()
                .filter(article -> Arrays.stream(article.getCategory())
                        .anyMatch(category -> category.getCategoryId() == id)).toList();
    }
    public void deleteArticle(int id){
        articles = articles.stream().filter(e->e.getId() != id).collect(Collectors.toList());
//        for(int i=0;i<articles.size(); i++){
//            Article article = articles.get(i);
//            if(article.getId()==id){
//                articles.remove(i);
//            }
//
//        }
    }

}

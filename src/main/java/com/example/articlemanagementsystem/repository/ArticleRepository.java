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
        add(new Article(1, "ALIEN", "alien is taking over the world", "https://i.pinimg.com/564x/19/d8/51/19d851065d0fa43c356328bcec240bf9.jpg",
                new AuthorRepository().getAuthors().get(0), new Category[]{new CategoryRepository().getCategories().get(0), new CategoryRepository().getCategories().get(2)}));
        add(new Article(2, "How to kill people without getting caught",
                "10 ways to murder people when you're bored without getting caught", "https://i.pinimg.com/564x/e8/a6/29/e8a6295025285f37aeb1a9ecbd9c642f.jpg",
                new AuthorRepository().getAuthors().get(1), new Category[]{new CategoryRepository().getCategories().get(1), new CategoryRepository().getCategories().get(0)}));
        add(new Article(3, "Painless death", "Scientist found a drug that you can take to achieve a happy" +
                "death", "https://i.pinimg.com/564x/7d/b9/01/7db9017c2ab0ffa801f41eb59e9f9fb4.jpg",
                new AuthorRepository().getAuthors().get(2),   new Category[]{new CategoryRepository().getCategories().get(1)}));
    }};
    public List<Article> getArticles(){
        return articles;
    }
    public Article getArticleById(int id){
        return articles.stream().filter(e->e.getId()==id).findFirst().orElse(null);
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

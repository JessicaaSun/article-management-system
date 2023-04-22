package com.example.articlemanagementsystem.repository;

import com.example.articlemanagementsystem.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    List<Category> categories = new ArrayList<>(){{
        add(new Category(1, "Science"));
        add(new Category(2, "Education"));
        add(new Category(3, "Entertainment"));
        add(new Category(4, "Programming"));
        add(new Category(5, "Sport"));
    }};
    public List<Category> getCategories() {
        return categories;
    }
    public Category getCategoryById(int id){
        return categories.stream().filter((category -> category.getCategoryId()==id)).findFirst().orElse(
                null
        );
    }
}

package com.example.articlemanagementsystem.service;

import com.example.articlemanagementsystem.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(int id);
}

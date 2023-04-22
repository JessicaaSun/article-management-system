package com.example.articlemanagementsystem.service.ServiceImpl;

import com.example.articlemanagementsystem.model.Category;
import com.example.articlemanagementsystem.repository.CategoryRepository;
import com.example.articlemanagementsystem.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    CategoryServiceImpl(){
        categoryRepository = new CategoryRepository();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getCategories();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.getCategoryById(id);
    }
}

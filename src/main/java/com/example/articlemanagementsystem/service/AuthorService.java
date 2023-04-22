package com.example.articlemanagementsystem.service;

import com.example.articlemanagementsystem.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorById(int id);
}

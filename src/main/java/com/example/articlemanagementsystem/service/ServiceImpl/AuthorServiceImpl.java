package com.example.articlemanagementsystem.service.ServiceImpl;

import com.example.articlemanagementsystem.model.Author;
import com.example.articlemanagementsystem.repository.AuthorRepository;
import com.example.articlemanagementsystem.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    AuthorServiceImpl() {
        authorRepository = new AuthorRepository();
    }
    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAuthors();
    }

    @Override
    public Author getAuthorById(int id) {
        return authorRepository.getAuthorById(id);
    }
}

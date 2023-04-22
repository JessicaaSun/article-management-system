package com.example.articlemanagementsystem.controller;

import com.example.articlemanagementsystem.model.Article;
import com.example.articlemanagementsystem.model.Author;
import com.example.articlemanagementsystem.model.Category;
import com.example.articlemanagementsystem.model.request.ArticleRequest;
import com.example.articlemanagementsystem.service.ArticleService;
import com.example.articlemanagementsystem.service.AuthorService;
import com.example.articlemanagementsystem.service.CategoryService;
import com.example.articlemanagementsystem.service.FileUploadService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    ArticleService articleService;
    AuthorService authorService;
    FileUploadService fileUploadService;
    CategoryService categoryService;
    HomeController(ArticleService articleService, AuthorService authorService, CategoryService categoryService, FileUploadService fileUploadService) {
        this.articleService = articleService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.fileUploadService = fileUploadService;
    }
    @GetMapping("/")
    public String getAllArticles(Model model){
        model.addAttribute("articles", articleService.getArticles().stream().sorted(((o1, o2) -> o2.getId()-o1.getId())));
        model.addAttribute("categories" , categoryService.getAllCategories());
        return  "pages/index";
    }
    @GetMapping("/article/{articleID}")
    public String getArticleByID(@PathVariable int articleID, Model model){
        model.addAttribute("articles", articleService.getArticleById(articleID));
        return "pages/articleDetail";
    }
    @GetMapping("/add-new-article")
    public String addNewArticle(Model model){
        model.addAttribute("articles", new ArticleRequest());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/pages/addArticle";
    }

    @PostMapping("/handle-add-article")
    public  String handleAddArticle(@ModelAttribute("articles") @Valid ArticleRequest article, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "/pages/addArticle";
        }
        Article newArticle = new Article();
        try{
            String filenames ="http://localhost:8080/images/"+ fileUploadService.uploadFile(article.getFile());
            System.out.println("Filename is "+filenames);
            newArticle.setImgURL(filenames);
        } catch (Exception ex){
            newArticle.setImgURL("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Placeholder_view_vector.svg/681px-Placeholder_view_vector.svg.png");
            System.out.println("Error : "+ex.getMessage());
        }
        newArticle.setTitle(article.getTitle());
        newArticle.setDescription(article.getDescription());
        newArticle.setAuthor(authorService.getAllAuthors().stream().filter(e->e.getId()==article.getAuthorId()).findFirst().orElse(null));
        // Create an array of Category objects from the categoryIds in the ArticleRequest object
        List<Category> categories = new ArrayList<>();
        for(int categoryId: article.getCategoryId()){
            categories.add(categoryService.getAllCategories().stream().filter(e -> e.getCategoryId() == categoryId).findFirst().orElse(null));
        }
        Category[] categoryArray = categories.toArray(new Category[categories.size()]);
        newArticle.setCategory(categoryArray);
        // Get the maximum ID from the existing articles and increment it by 1 to set as the ID of the new article
        newArticle.setId(articleService.getArticles().stream().max(Comparator.comparingInt(Article::getId)).stream().toList().get(0).getId()+1);

        articleService.addNewArticle(newArticle);
        return "redirect:/";
    }

    @GetMapping("/delete-article/{id}")
    public String deleteArticle(@PathVariable int id, @RequestParam String source, Model model){
        articleService.deleteArticle(id);
        // Should I add these 3 lines code below??
        model.addAttribute("articles", articleService.getArticles());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "redirect:/";
    }
    @GetMapping("/edit-article/{id}")
    public String editArticle(@PathVariable int id, Model model){
        List<Author> authors = authorService.getAllAuthors();
        List<Category> categories = categoryService.getAllCategories();
        Article article = articleService.getArticleById(id);
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setTitle(articleService.getArticleById(id).getTitle());
        articleRequest.setDescription(articleService.getArticleById(id).getDescription());
        articleRequest.setId(articleService.getArticleById(id).getId());
        articleRequest.setAuthorId(articleService.getArticleById(id).getAuthor().getId());
        articleRequest.setImgURL(articleService.getArticleById(id).getImgURL());
        // Get the category IDs of the article and create an integer array
        int[] categoryIds = Arrays.stream(article.getCategory()).mapToInt(Category::getCategoryId).toArray();
        articleRequest.setCategoryId(categoryIds);
        System.out.println(articleRequest);
        model.addAttribute("articles", articleRequest);
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        return "pages/editArticle";

    }
    @PostMapping("/handle-edit-article/{id}")
    public  String handleEditArticle(@PathVariable("id") int id, @ModelAttribute("articles") @Valid ArticleRequest article, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "/pages/editArticle";
        }
        Article newArticle = articleService.getArticleById(id);
        // Check if a new file was uploaded and update the image URL accordingly
        if (!article.getFile().isEmpty()) {
            try {
                String filenames = "http://localhost:8080/images/" + fileUploadService.uploadFile(article.getFile());
                newArticle.setImgURL(filenames);
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        newArticle.setTitle(article.getTitle());
        newArticle.setDescription(article.getDescription());
        newArticle.setId(article.getId());
//        newArticle.setImgURL(article.getImgURL());
        newArticle.setAuthor(authorService.getAllAuthors().stream().filter(e->e.getId()== article.getAuthorId()).findFirst().orElse(null));
        List<Category> categories = new ArrayList<>();
        for(int categoryId: article.getCategoryId()){
            categories.add(categoryService.getAllCategories().stream().filter(e -> e.getCategoryId() == categoryId).findFirst().orElse(null));
        }
        Category[] categoryArray = categories.toArray(new Category[categories.size()]);
        newArticle.setCategory(categoryArray);
        newArticle.setId(article.getId());

        articleService.editArticle(id, newArticle);
        return "redirect:/";
    }
    @GetMapping("/category/{categoryID}")
    public String getArticleByCategoryId(@PathVariable int categoryID, Model model){
        model.addAttribute("articles", articleService.getArticleByCategoryId(categoryID));
        model.addAttribute("category", categoryService.getCategoryById(categoryID));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "pages/category";
    }
}

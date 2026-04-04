package libraryManager.controller;

import libraryManager.entity.Category;
import libraryManager.repository.BookRepository;
import libraryManager.service.BookService;
import libraryManager.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final BookService bookService;
    private final CategoryService categoryService;
    public HomeController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }
    @GetMapping({"/","/books"})
    public String listBooks(@RequestParam(name = "q", required = false) String q, Model model) {

        List<Category> categories = categoryService.listAll();
        model.addAttribute("categories", categories);
        if (q == null || q.isBlank()) {
            model.addAttribute("books", bookService.findAll());
        } else {
            model.addAttribute("books", bookService.findByTitleContainingIgnoreCase(q));
        }
        model.addAttribute("q", q);
        return "books/list";
    }

    @GetMapping("/books/detail")
    public String detail(@RequestParam(name = "id") Long id, Model model) {
        bookService.findById(id).ifPresent(b -> model.addAttribute("book", b));
        return "books/detail";
    }
}

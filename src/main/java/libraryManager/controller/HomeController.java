package libraryManager.controller;

import libraryManager.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final BookRepository bookRepository;

    public HomeController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping({"/","/books"})
    public String listBooks(@RequestParam(name = "q", required = false) String q, Model model) {
        if (q == null || q.isBlank()) {
            model.addAttribute("books", bookRepository.findAll());
        } else {
            model.addAttribute("books", bookRepository.findByTitleContainingIgnoreCase(q));
        }
        model.addAttribute("q", q);
        return "books/list";
    }

    @GetMapping("/books/detail")
    public String detail(@RequestParam(name = "id") Long id, Model model) {
        bookRepository.findById(id).ifPresent(b -> model.addAttribute("book", b));
        return "books/detail";
    }
}

package libraryManager.controller;

import libraryManager.entity.Book;
import libraryManager.entity.Category;
import libraryManager.repository.CategoryRepository;
import libraryManager.service.BookService;
import libraryManager.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/books")
public class AdminBookController {

    private final BookService bookService;
    private final CategoryService categoryService;
    public AdminBookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(@RequestParam(name = "q", required = false) String q, Model model) {
        model.addAttribute("books", bookService.search(q));
        model.addAttribute("q", q);
        return "admin/books/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        List<Category> categories = categoryService.listAll();
        model.addAttribute("categories", categories);
        model.addAttribute("book", new Book());
        model.addAttribute("action", "/admin/books");
        return "admin/books/form";
    }

    @PostMapping
    public String create(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id).orElseThrow();
        model.addAttribute("categories", categoryService.listAll());
        model.addAttribute("book", book);
        model.addAttribute("action", "/admin/books");
        return "admin/books/form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return "redirect:/admin/books";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id).orElseThrow();
        model.addAttribute("book", book);
        return "admin/books/detail";
    }

    @GetMapping("/books")
    public String listBooks(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Long categoryId,
            Model model) {

        model.addAttribute("categories", categoryService.listAll());

        List<Book> books;
        if (categoryId != null || (q != null && !q.isEmpty())) {
            books = bookService.search(q, categoryId);
        } else {
            books = bookService.findAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("q", q);
        model.addAttribute("selectedCatId", categoryId); // Giữ trạng thái đã chọn trên dropdown

        return "books/index";
    }
}

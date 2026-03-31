package libraryManager.controller;

import libraryManager.entity.BookItem;
import libraryManager.repository.BookRepository;
import libraryManager.service.BookItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/book-items")
public class AdminBookItemController {

    private final BookItemService bookItemService;
    private final BookRepository bookRepository;

    public AdminBookItemController(BookItemService bookItemService, BookRepository bookRepository) {
        this.bookItemService = bookItemService;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String list(@RequestParam(name = "q", required = false) String q, Model model) {
        model.addAttribute("items", bookItemService.search(q));
        model.addAttribute("q", q);
        return "admin/book-items/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("item", new BookItem());
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("statuses", BookItem.Status.values());
        model.addAttribute("action", "/admin/book-items");
        return "admin/book-items/form";
    }

    @PostMapping
    public String create(@ModelAttribute BookItem item) {
        bookItemService.save(item);
        return "redirect:/admin/book-items";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        BookItem it = bookItemService.findById(id).orElseThrow();
        model.addAttribute("item", it);
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("statuses",BookItem.Status.values());
        model.addAttribute("action", "/admin/book-items");
        return "admin/book-items/form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        bookItemService.deleteById(id);
        return "redirect:/admin/book-items";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BookItem it = bookItemService.findById(id).orElseThrow();
        model.addAttribute("item", it);
        return "admin/book-items/detail";
    }
}

package libraryManager.controller;

import libraryManager.repository.BookItemRepository;
import libraryManager.service.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/loans")
public class LoanController {

    private final LoanService loanService;
    private final BookItemRepository bookItemRepository;

    public LoanController(LoanService loanService, BookItemRepository bookItemRepository) {
        this.loanService = loanService;
        this.bookItemRepository = bookItemRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("loans", loanService.openLoans());
        return "admin/loans";
    }

    @GetMapping("/borrow")
    public String borrowForm(Model model) {
        model.addAttribute("items", bookItemRepository.findAll());
        return "admin/borrow";
    }

    @PostMapping("/borrow")
    public String borrow(@RequestParam(name = "bookId") Long bookId,
                         @RequestParam(name = "borrowerName") String borrowerName) {
        loanService.borrowBook(bookId, borrowerName, 14);
        return "redirect:/admin/loans";
    }

    @PostMapping("/{id}/return")
    public String returnLoan(@PathVariable("id") Long id) {
        loanService.returnBook(id);
        return "redirect:/admin/loans";
    }
}

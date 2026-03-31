package libraryManager.controller;

import libraryManager.repository.BookItemRepository;
import libraryManager.repository.BookRepository;
import libraryManager.repository.FineRepository;
import libraryManager.repository.LoanRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {

    private final BookRepository bookRepo;
    private final BookItemRepository itemRepo;
    private final LoanRepository loanRepo;
    private final FineRepository fineRepo;

    public AdminDashboardController(BookRepository bookRepo, BookItemRepository itemRepo, LoanRepository loanRepo, FineRepository fineRepo) {
        this.bookRepo = bookRepo; this.itemRepo = itemRepo; this.loanRepo = loanRepo; this.fineRepo = fineRepo;
    }

    @GetMapping("/admin/home")
    public String dashboard(Model model) {
        model.addAttribute("bookCount", bookRepo.count());
        model.addAttribute("itemCount", itemRepo.count());
        model.addAttribute("activeLoans", loanRepo.findByReturnDateIsNull().size());
        model.addAttribute("openFines", fineRepo.findByPaidFalse().size());
        return "admin/dashboard";
    }
}

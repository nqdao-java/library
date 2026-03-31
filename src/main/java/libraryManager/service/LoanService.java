package libraryManager.service;

import libraryManager.entity.BookItem;
import libraryManager.entity.Loan;
import libraryManager.repository.BookItemRepository;
import libraryManager.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookItemRepository bookItemRepository;

    public LoanService(LoanRepository loanRepository, BookItemRepository bookItemRepository) {
        this.loanRepository = loanRepository;
        this.bookItemRepository = bookItemRepository;
    }

    public List<Loan> openLoans() {
        return loanRepository.findByReturnDateIsNull();
    }

    @Transactional
    public Loan borrowBook(Long bookItemId, String borrowerName, int days) {
        BookItem item = bookItemRepository.findById(bookItemId).orElseThrow();
        if (item.getStatus() != null && item.getStatus().name().equals("BORROWED")) {
            throw new IllegalStateException("Book item already borrowed");
        }
        item.setStatus(BookItem.Status.BORROWED);
        bookItemRepository.save(item);

        Loan loan = new Loan();
        loan.setBookItem(item);
        loan.setBorrowerName(borrowerName);
        loan.setBorrowDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(days));
        return loanRepository.save(loan);
    }

    @Transactional
    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        if (loan.getReturnDate() != null) return loan;
        loan.setReturnDate(LocalDate.now());
        BookItem item = loan.getBookItem();
        item.setStatus(BookItem.Status.AVAILABLE);
        bookItemRepository.save(item);
        return loanRepository.save(loan);
    }
}

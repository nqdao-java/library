package libraryManager.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_item_id")
    private BookItem bookItem;

    private String borrowerName;

    private LocalDate borrowDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BookItem getBookItem() { return bookItem; }
    public void setBookItem(BookItem bookItem) { this.bookItem = bookItem; }
    public String getBorrowerName() { return borrowerName; }
    public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}

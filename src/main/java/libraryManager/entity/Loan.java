package libraryManager.entity;

import jakarta.persistence.*;
import libraryManager.entity.Enum.ELoanStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "loan")
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLoan")
    private Long idLoan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBookItem", nullable = false)
    private BookItem bookItem;

    @Column(name = "borrowDate", nullable = false)
    private LocalDateTime borrowDate;

    @Column(name = "dueDate", nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "returnDate")
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ELoanStatus status;


}

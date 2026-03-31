package libraryManager.entity;

import jakarta.persistence.*;
import libraryManager.entity.Enum.EBookItemStatus;
import lombok.Data;

@Entity
@Table( name = "bookItem")
@Data
public class BookItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBookItem")
    private int idBookItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "barcode", unique = true, nullable = false, length = 50)
    private String barcode;

    @Column(name = "floor", length = 50)
    private String floor;

    @Column(name = "shelf", length = 50)
    private String shelf;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EBookItemStatus status;

}

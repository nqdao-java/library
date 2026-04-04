package libraryManager.repository;
import libraryManager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String q);

    @Query("SELECT b FROM Book b WHERE " +
            "(:q IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(b.author) LIKE LOWER(CONCAT('%', :q, '%'))) " +
            "AND (:categoryId IS NULL OR b.category.id = :categoryId)")
    List<Book> searchBooks(@Param("q") String q, @Param("categoryId") Long categoryId);

    List<Book> findByCategoryId(Long categoryId);
}

package libraryManager.repository;

import libraryManager.entity.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookItemRepository extends JpaRepository<BookItem, Long> {
    List<BookItem> findByBarcodeContainingIgnoreCase(String q);
}

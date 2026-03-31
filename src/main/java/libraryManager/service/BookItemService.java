package libraryManager.service;

import libraryManager.entity.BookItem;
import libraryManager.repository.BookItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookItemService {

    private final BookItemRepository repo;

    public BookItemService(BookItemRepository repo) {
        this.repo = repo;
    }

    public List<BookItem> listAll() { return repo.findAll(); }

    public List<BookItem> search(String q) {
        if (q == null || q.isBlank()) return repo.findAll();
        return repo.findByBarcodeContainingIgnoreCase(q);
    }

    public Optional<BookItem> findById(Long id) { return repo.findById(id); }

    public BookItem save(BookItem b) { return repo.save(b); }

    public void deleteById(Long id) { repo.deleteById(id); }
}

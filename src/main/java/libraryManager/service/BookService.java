package libraryManager.service;

import libraryManager.entity.Book;
import libraryManager.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> search(String q) {
        if (q == null || q.isBlank()) return findAll();
        return bookRepository.findByTitleContainingIgnoreCase(q);
    }

    public List<Book> search(String q, Long categoryId) {
        return bookRepository.searchBooks(q, categoryId);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findByCategoryId(Long categoryId) {
        if (categoryId == null) {
            return List.of();
        }
        return bookRepository.findByCategoryId(categoryId);
    }

    public  List<Book>  findByTitleContainingIgnoreCase(String categoryId) {
        return bookRepository.findByTitleContainingIgnoreCase(categoryId);
    }
}

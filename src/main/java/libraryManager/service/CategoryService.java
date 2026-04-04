package libraryManager.service;

import libraryManager.entity.Category;
import libraryManager.repository.CategoryRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    public List<Category> search(String q) {
        if (q == null || q.isBlank()) return categoryRepository.findAll();
        return categoryRepository.findByNameContainingIgnoreCase(q);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category c) {
        return categoryRepository.save(c);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}

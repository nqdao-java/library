package libraryManager.controller;

import libraryManager.entity.Category;
import libraryManager.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(@RequestParam(name = "q", required = false) String q, Model model) {
        model.addAttribute("categories", categoryService.search(q));
        model.addAttribute("q", q);
        return "admin/categories/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("action", "/admin/categories");
        return "admin/categories/form";
    }

    @PostMapping
    public String create(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Category c = categoryService.findById(id).orElseThrow();
        model.addAttribute("category", c);
        model.addAttribute("action", "/admin/categories");
        return "admin/categories/form";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Category c = categoryService.findById(id).orElseThrow();
        model.addAttribute("category", c);
        return "admin/categories/detail";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }

}

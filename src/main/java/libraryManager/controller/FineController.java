package libraryManager.controller;

import libraryManager.service.FineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/fines")
public class FineController {

    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("fines", fineService.listAll());
        return "admin/fines";
    }

    @PostMapping("/{id}/pay")
    public String pay(@PathVariable("id") Long id) {
        fineService.payFine(id);
        return "redirect:/admin/fines";
    }
}

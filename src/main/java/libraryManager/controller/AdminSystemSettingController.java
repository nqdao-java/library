package libraryManager.controller;

import libraryManager.entity.SystemSetting;
import libraryManager.service.SystemSettingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/settings")
public class AdminSystemSettingController {

    private final SystemSettingService service;

    public AdminSystemSettingController(SystemSettingService service) { this.service = service; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("settings", service.listAll());
        return "admin/settings/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("setting", new SystemSetting());
        return "admin/settings/form";
    }

    @PostMapping
    public String save(@ModelAttribute SystemSetting setting) {
        service.save(setting);
        return "redirect:/admin/settings";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("setting", service.listAll().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(new SystemSetting()));
        return "admin/settings/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/admin/settings";
    }
}

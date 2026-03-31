package libraryManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping({"/", "/home", "/books/catalog"})
    public String showHomePage() {
        return "books/catalog";
    }

}

package libraryManager.config;

import libraryManager.entity.User;
import libraryManager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
@Slf4j
public class DataInitializer {

    @Bean
    public CommandLineRunner createAdmin(UserService userService, PasswordEncoder passwordEncoder) {
     log.debug("Check createAdmin");
        log.debug(passwordEncoder.encode("admin"));
        return args -> {
            String adminUsername = "admin";
            if (!userService.existsByUsername(adminUsername)) {
                User u = new User();
                u.setUsername(adminUsername);
                u.setFullName("Administrator");
                u.setEmail("admin@example.local");
                u.setRole(User.Role.ADMIN);
                u.setPassword(passwordEncoder.encode("admin"));
                userService.save(u); // comment
                System.out.println("Created default admin/admin user.");
            }
        };
    }
}

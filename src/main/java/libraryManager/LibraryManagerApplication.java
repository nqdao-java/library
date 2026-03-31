package libraryManager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootApplication
public class LibraryManagerApplication {

    public static void main(String[] args) {
        log.error("Check");
        SpringApplication.run(LibraryManagerApplication.class, args);
    }

}

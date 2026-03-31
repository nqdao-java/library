package libraryManager.config;
import libraryManager.security.JwtAuthenticationFilter;
import libraryManager.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter; // Khai báo filter

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http    .cors(cors -> {})
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 1. Mở cửa cho GIAO DIỆN (GET)
                        .requestMatchers("/login", "/").permitAll()

                        // 2. Mở cửa cho TÀI NGUYÊN TĨNH (Phải có cái này mới chạy được CSS/JS/API-Client)
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll()

                        // 3. Mở cửa cho API AUTH (POST)
                        .requestMatchers("/api/auth/**").permitAll()

                        // 4. Phân quyền API nghiệp vụ
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/books/**").hasAnyRole("STUDENT", "LECTURER", "LIBRARIAN", "ADMIN")

                        // Còn lại khóa hết
                        .anyRequest().authenticated()
                );

        // MỞ COMMENT DÒNG NÀY ĐỂ JWT HOẠT ĐỘNG
        http.addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

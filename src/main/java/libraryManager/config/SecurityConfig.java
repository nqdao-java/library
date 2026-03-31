package libraryManager.config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

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
        log.debug("SecurityFilterChain" + http);
        http     .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**","/js/**","/","/books/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                        .formLogin(form -> form
                                .loginPage("/auth/login").permitAll()
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/admin/dashboard", true)
                                .failureUrl("/auth/login?error")
                        )
                        .logout(logout -> logout.logoutSuccessUrl("/auth/login?logout"))
                        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())
                        );
        return http.build();
    }
}

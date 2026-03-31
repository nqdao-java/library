package libraryManager.service;
import libraryManager.dto.request.LoginRequest;
import libraryManager.dto.response.JwtAuthResponse;
import libraryManager.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public JwtAuthResponse login(LoginRequest loginRequest) {
        // Giao việc xác thực cho AuthenticationManager (Nó sẽ tự gọi CustomUserDetailsService và check Pass)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassWord()
                )
        );

        // 2. Nếu đúng pass -> Lưu trạng thái vào SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Tạo JWT Token
        String jwt = tokenProvider.generateToken(authentication);

        // 4. Lấy Role hiện tại của user để trả về cho Front-end
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_STUDENT"); // Mặc định nếu không có

        // 5. Trả về DTO
        return JwtAuthResponse.builder()
                .accessToken(jwt)
                .username(loginRequest.getUserName())
                .role(role)
                .build();
    }
}

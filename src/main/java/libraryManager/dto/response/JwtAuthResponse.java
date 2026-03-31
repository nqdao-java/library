package libraryManager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class JwtAuthResponse {
    private String accessToken;
    @Builder.Default
    private String tokenType = "Bearer";
    private String username;
    private String role;

    public JwtAuthResponse (String accessToken){
        this.accessToken = accessToken;
    }
}

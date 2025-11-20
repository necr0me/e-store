package by.necr0me.estore.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponseDto {
    private final String accessToken;

    @JsonIgnore
    private final String refreshToken;
}

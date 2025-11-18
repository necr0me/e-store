package by.necr0me.estore.dto.auth;

import lombok.Getter;

@Getter
public class RegistrationRequestDto {
    private final String username;

    private final String email;

    private final String password;

    public RegistrationRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

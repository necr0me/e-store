package by.necr0me.estore.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class RegistrationRequestDto {
    @Length(min = 6, max = 32)
    @NotEmpty
    private final String username;

    @Email
    @NotEmpty
    private final String email;

    @Length(min = 6, max = 64)
    @NotEmpty
    private final String password;
}

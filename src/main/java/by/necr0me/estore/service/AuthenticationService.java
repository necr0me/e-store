package by.necr0me.estore.service;

import by.necr0me.estore.dto.auth.AuthResponseDto;
import by.necr0me.estore.dto.auth.LoginRequestDto;
import by.necr0me.estore.dto.auth.RegistrationRequestDto;

public interface AuthenticationService {
    void register(RegistrationRequestDto registrationRequestDto);

    AuthResponseDto authenticate(LoginRequestDto loginRequestDto);

    AuthResponseDto refreshTokens(String refreshToken);
}

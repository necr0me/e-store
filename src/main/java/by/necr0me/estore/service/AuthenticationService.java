package by.necr0me.estore.service;

import by.necr0me.estore.dto.auth.AuthResponseDto;
import by.necr0me.estore.dto.auth.LoginRequestDto;
import by.necr0me.estore.dto.auth.RegistrationRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    void register(RegistrationRequestDto registrationRequestDto);

    AuthResponseDto authenticate(LoginRequestDto loginRequestDto);

    AuthResponseDto refreshTokens(HttpServletRequest request, HttpServletResponse response);
}

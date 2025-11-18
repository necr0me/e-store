package by.necr0me.estore.controller;

import by.necr0me.estore.dto.auth.AuthResponseDto;
import by.necr0me.estore.dto.auth.LoginRequestDto;
import by.necr0me.estore.dto.auth.RegistrationRequestDto;
import by.necr0me.estore.entity.User;
import by.necr0me.estore.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody @Validated RegistrationRequestDto request) {
        authenticationService.register(request);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        AuthResponseDto response = authenticationService.authenticate(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, buildResponseCookie(response).toString())
                .body(response);
    }

    @PostMapping("/refresh_tokens")
    public ResponseEntity<AuthResponseDto> refreshTokens(HttpServletRequest request,
                                                         HttpServletResponse response) {
        AuthResponseDto result = authenticationService.refreshTokens(request, response);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, buildResponseCookie(result).toString())
                .body(result);
    }

    private ResponseCookie buildResponseCookie(AuthResponseDto response) {
        return ResponseCookie.from("refreshToken", response.getRefreshToken())
                .httpOnly(true)
                .path("/") // set up age too (after proper jwt configuration)
                .build();
    }
}

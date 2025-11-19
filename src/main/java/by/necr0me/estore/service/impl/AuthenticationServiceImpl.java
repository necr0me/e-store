package by.necr0me.estore.service.impl;

import by.necr0me.estore.dto.auth.AuthResponseDto;
import by.necr0me.estore.dto.auth.LoginRequestDto;
import by.necr0me.estore.dto.auth.RegistrationRequestDto;
import by.necr0me.estore.entity.RefreshToken;
import by.necr0me.estore.entity.User;
import by.necr0me.estore.entity.enums.Role;
import by.necr0me.estore.repository.RefreshTokenRepository;
import by.necr0me.estore.repository.UserRepository;
import by.necr0me.estore.service.AuthenticationService;
import by.necr0me.estore.service.JwtService;
import by.necr0me.estore.service.UserService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     JwtService jwtService,
                                     PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager,
                                     RefreshTokenRepository refreshTokenRepository, UserService userService)
    {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userService = userService;
    }

    @Override
    public void register(RegistrationRequestDto request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    @Override
    public AuthResponseDto authenticate(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("No user found"));

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeRefreshTokens(user);

        saveRefreshToken(refreshToken, user);

        return new AuthResponseDto(accessToken, refreshToken);
    }

    @Override
    public AuthResponseDto refreshTokens(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user found"));

        if (!jwtService.isRefreshTokenValid(refreshToken, user)) {
            throw new JwtException("Refresh token is invalid");
        }

        String newAccessToken = jwtService.generateAccessToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);

        revokeRefreshTokens(user);

        saveRefreshToken(newRefreshToken, user);

        return new AuthResponseDto(newAccessToken, newRefreshToken);
    }

    private void revokeRefreshTokens(User user) {
        List<RefreshToken> tokenList = refreshTokenRepository.findAllRefreshTokenByUser(user.getId());

        if(!tokenList.isEmpty()) {
            tokenList.forEach(token -> token.setLoggedOut(true));
        }

        refreshTokenRepository.saveAll(tokenList);
    }

    private void saveRefreshToken(String tokenValue, User user) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setValue(tokenValue);
        refreshToken.setLoggedOut(false);
        refreshToken.setUser(user);

        refreshTokenRepository.save(refreshToken);
    }
}

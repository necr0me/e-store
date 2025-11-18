package by.necr0me.estore.handler.security;

import by.necr0me.estore.entity.RefreshToken;
import by.necr0me.estore.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import static by.necr0me.estore.constant.Http.*;


@Component
public class CustomLogoutHandler implements LogoutHandler {
    private final RefreshTokenRepository refreshTokenRepository;

    public CustomLogoutHandler(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        if(authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            return;
        }

        String tokenValue = authHeader.substring(BEARER_PREFIX.length());

        RefreshToken refreshToken = refreshTokenRepository.findByValue(tokenValue).orElse(null);

        if(refreshToken != null) {
            refreshToken.setLoggedOut(true);
            refreshTokenRepository.save(refreshToken);
        }
    }
}

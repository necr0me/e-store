package by.necr0me.estore.service;

import by.necr0me.estore.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JwtService {
    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String extractUsername(String token);

    boolean isAccessTokenValid(String token, UserDetails user);

    boolean isRefreshTokenValid(String token, UserDetails user);
}

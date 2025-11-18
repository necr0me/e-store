package by.necr0me.estore.service.impl;

import by.necr0me.estore.entity.User;
import by.necr0me.estore.repository.RefreshTokenRepository;
import by.necr0me.estore.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${security.jwt.secret_key}")
    private String secretKey;

    @Value("${security.jwt.access_token_expiration}")
    private long accessTokenExpiration;

    @Value("${security.jwt.refresh_token_expiration}")
    private long refreshTokenExpiration;

    private final RefreshTokenRepository refreshTokenRepository;

    public JwtServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public String generateAccessToken(User user) {
        return generateToken(user, accessTokenExpiration);
    }

    @Override
    public String generateRefreshToken(User user) {
        return generateToken(user, refreshTokenExpiration);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractClaimsFromToken(token);

        return resolver.apply(claims);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isAccessTokenValid(String token, UserDetails user) {
        String username = extractUsername(token);

        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    @Override
    public boolean isRefreshTokenValid(String token, UserDetails user) {
        String username = extractUsername(token);

        boolean isTokenValid = refreshTokenRepository.findByValue(token)
                .map(t -> !t.isLoggedOut()).orElse(false);

        return username.equals(user.getUsername())
                && isTokenValid
                && isTokenExpired(token);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractClaimsFromToken(String token) {
        JwtParserBuilder parser = Jwts.parser();
        parser.verifyWith(getSigningKey());

        return parser.build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String generateToken(User user, long expirationTime) {
        JwtBuilder builder = Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey());

        return builder.compact();
    }

    private SecretKey getSigningKey() {
        byte[] encodedKey = Base64.getDecoder().decode(secretKey);

        return Keys.hmacShaKeyFor(encodedKey);
    }
}

package me.ry4nn00b.hortifruti.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class TokenManager {

    private static final String SECRET = "k9F8wN2zR7vT6bXqP4sL8mQ1dJ5aZ3yH";
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_MS = 86400000;

    //Generate Token
    public static String generateToken(String email, List<String> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    //Extract Email
    public static String getEmailFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    //Extract Roles
    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        return (List<String>) parseClaims(token).get("roles");
    }

    //Check Token
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Hortifruti Erro: Token inválido: " + e.getMessage());
            return false;
        }
    }

    //Parser
    private static Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

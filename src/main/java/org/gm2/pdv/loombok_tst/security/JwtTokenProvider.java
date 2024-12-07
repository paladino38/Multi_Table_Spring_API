package org.gm2.pdv.loombok_tst.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key secretKey = Keys.hmacShaKeyFor("secretsecretsecretsecretsecretsecretsecretsecret".getBytes()); // Use a secure key in production
    private final long validityInMilliseconds = 3600000; // 1 hour

    public String createToken(String username, String role) {
        Claims claims = (Claims) Jwts.claims().setSubject(username);
        claims.put("role", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS256) // Updated signing method
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey) // Updated for version >= 0.11.0
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        String username = Jwts.parser()
                .setSigningKey(secretKey) // Updated for version >= 0.11.0
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return new UsernamePasswordAuthenticationToken(username, "", null);
    }
}

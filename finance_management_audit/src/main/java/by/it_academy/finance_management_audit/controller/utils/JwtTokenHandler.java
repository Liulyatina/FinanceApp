package by.it_academy.finance_management_audit.controller.utils;

import by.it_academy.finance_management_audit.config.properties.JWTProperty;
import by.it_academy.finance_management_audit.core.enums.UserRole;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenHandler {

    private final JWTProperty property;

    public JwtTokenHandler(JWTProperty property) {
        this.property = property;
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public String generateAccessToken(String name, UserRole userRole) {
        return Jwts.builder()
                .setSubject(name)
                .setIssuer(property.getIssuer())
                .claim("role", userRole)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                .signWith(SignatureAlgorithm.HS512, property.getSecret())
                .compact();
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(property.getSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            //logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            //logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            //logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            //logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            //logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}

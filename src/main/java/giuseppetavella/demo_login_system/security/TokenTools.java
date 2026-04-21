package giuseppetavella.demo_login_system.security;

import giuseppetavella.D5.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.UUID;


@Component
public class TokenTools {

    private final String secret;

    public TokenTools(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }


    public String generateToken(String userId) {
        Date now = new Date(System.currentTimeMillis());
        int _7DaysMillis = 1000 * 60 * 60 * 24 * 7;
        Date futureTime = new Date(System.currentTimeMillis() + _7DaysMillis);
        String subject = userId;
        Key secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        // here we build the JWT
        return Jwts.builder()
                .issuedAt(now)
                .expiration(futureTime)
                .subject(subject)
                .signWith(secretKey)
                .compact();
    }



    public void verifyToken(String token) {

        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
            Jwts
                    .parser()
                    .verifyWith(secretKey)
                    .build()
                    .parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Il token non è valido. Fai di nuovo login.");
        }
    }


    public UUID extractIdFromToken(String token) {
        return UUID.fromString(
                Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(this.secret.getBytes()))
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getSubject()
        );
    }

}
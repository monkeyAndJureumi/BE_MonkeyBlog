package com.monkey.utils;

import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Configuration
@PropertySource("classpath:properties/${spring.profiles.active}/token.properties")
public class JwtTokenUtils {
    private static String JwtSecretKey;
    private static String JwtIssuer;
    private static long JwtExpiration;

    @Value("${jwt.secret.key}")
    public void setSecretKey(String secretKey) {
        JwtSecretKey = secretKey;
    }

    @Value("${jwt.issuer}")
    public void setIssuer(String issuer) {
        JwtIssuer = issuer;
    }

    @Value("${jwt.expiration}")
    public void setExpiration(long expiration) {
        JwtExpiration = expiration;
    }

    public static String CreateJwtToken(Long userId) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + Duration.ofHours(JwtExpiration).toMillis());
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(JwtIssuer)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .setSubject("UserSession")
                .setId(userId.toString())
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(JwtSecretKey.getBytes()))
                .compact();
    }

    public static Long ParseJwtToken(String token) throws SignatureException {
        token = RemoveBearer(token);
        Claims claims = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(JwtSecretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getId());
    }

    private static String RemoveBearer(String token) {
        if (token == null || !token.startsWith("Bearer "))
            throw new MonkeyException(MonkeyErrorCode.E601, HttpStatus.BAD_REQUEST);

        return token.substring("Bearer ".length());
    }
}

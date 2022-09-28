package com.monkey.utils;

import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtTokenUtils {
    public static String CreateAccessToken(long expiration, String issuer, String secretKey, Long userId, String userCode) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + Duration.ofSeconds(expiration).toMillis());
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .setSubject("Access Token")
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .claim("user_id", userId)
                .claim("user_code", userCode)
                .compact();
    }

    public static String CreateRefreshToken(long expiration, String issuer, String secretKey, Long userId, String userCode) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + Duration.ofSeconds(expiration).toMillis());
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .setSubject("Refresh Token")
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .claim("user_id", userId)
                .claim("user_code", userCode)
                .compact();
    }

    public static Claims ParseJwtToken(String token, String secretKey) throws SignatureException {
        token = RemoveBearer(token);
        Claims claims = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    private static String RemoveBearer(String token) {
        if (token == null || !token.startsWith("Bearer "))
            throw new MonkeyException(MonkeyErrorCode.E601, HttpStatus.BAD_REQUEST);

        return token.substring("Bearer ".length());
    }
}

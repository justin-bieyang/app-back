package com.justin.app_back.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */
public class JwtUtil {

    // 使用足够长度的密钥（至少 32 字节）
    private static final String SECRET_STRING = "jf3q-jwt-this-is-a-very-long-secret-key-1234567890";
    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    public static String getToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String getUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            throw new RuntimeException("Token 解析失败: " + e.getMessage());
        }
    }
}

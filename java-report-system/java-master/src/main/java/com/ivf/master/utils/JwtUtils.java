package com.ivf.master.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtils {
    // Token 过期时间（1天）
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    // Token 密钥
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    /**
     * 生成 Token
     *
     * @param id       用户ID
     * @param nickname 用户昵称
     * @return 生成的 Token
     */
    public static String generateToken(String id, String nickname) {
        return Jwts.builder()
                .setSubject("common-user")
                .claim("id", id)
                .claim("nickname", nickname)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    /**
     * 校验 Token
     *
     * @param token 待校验的 Token
     * @return 是否有效
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从 Token 中提取用户ID
     *
     * @param token 待提取信息的 Token
     * @return 用户ID
     */
    public static String getMemberId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(APP_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("id");
    }

    /**
     * 从 HttpServletRequest 中提取 Token，并获取用户ID
     *
     * @param request HTTP 请求
     * @return 用户ID
     */
    public static String getMemberId(HttpServletRequest request) {
        String token = request.getHeader("token");
        return getMemberId(token);
    }
}



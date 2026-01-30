package com.indra.controlhorarioapi.jwt;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;


public class JwtUtil {
    private static final String SECRET_KEY = "g82376d4782136d4872364871h2364876238746fg71283g65d4s872364786hj3876h78236hd4";
    private static final long EXPIRATION_TIME = 3600000; // 1 hour
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final Key key = new SecretKeySpec(SECRET_KEY.getBytes(), SIGNATURE_ALGORITHM.getJcaName());

    public static String generateToken(String username, String role, Map<String, Object> userData) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("usuario", userData)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SIGNATURE_ALGORITHM, key)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static String getRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }
}
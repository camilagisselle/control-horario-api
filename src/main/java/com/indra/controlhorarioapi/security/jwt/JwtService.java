package com.indra.controlhorarioapi.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private final String SECRET_KEY = "NzgwMTQwODMtZGE5ZC00NDk5LTk1YWItZDk0Njc4ZDJiMjM5";

    public String generarToken(Map<String, Object> userData) {

        return Jwts.builder()
                .claim("usuario", userData)   // 👈 mete el objeto
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
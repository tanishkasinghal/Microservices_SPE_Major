package com.example.ApiGateway.util;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;


    private String secret = "jwtTokenKey";

    //validate token

    public void validToken(final String token) {
       // Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }
}

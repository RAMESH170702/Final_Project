package com.bej.authentication.security;


import com.bej.authentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    public Map<String, String> createToken(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userEmail", user.getUserEmail());
        return generateToken(claims,user.getUserEmail());
    }

    public Map<String, String> generateToken(Map<String,Object> claims,String subject) {
        String jwtToken = Jwts.builder().setIssuer("ShoppingZone")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"mysecret")
                .compact();
        Map<String,String> map = new HashMap<>();
        map.put("token",jwtToken);
        map.put("message", "User Successfully logged in");
        return map;
    }


}

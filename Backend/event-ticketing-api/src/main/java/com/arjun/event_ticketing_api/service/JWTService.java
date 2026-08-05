package com.arjun.event_ticketing_api.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

@Service
public class JWTService {
    private String secretKey="";
    public JWTService(){
        try {
            KeyGenerator keyGen =
                    KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256);
            SecretKey sk = keyGen.generateKey();

            secretKey =
                    Base64.getEncoder()
                            .encodeToString(sk.getEncoded());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public String generateToken(String username) {


        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getKey())
                .compact();


    }
    private SecretKey getKey() {
        byte[] keyBytes =
                Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);

    }
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();



    }
    public boolean validateToken(String token, UserDetails userDetails){
        String username=extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);


    }
    private boolean isTokenExpired(String token){
        Date expiration = extractExpiration(token);
        return expiration.before(new Date());

    }
    private Date extractExpiration(String token) {

        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }
}

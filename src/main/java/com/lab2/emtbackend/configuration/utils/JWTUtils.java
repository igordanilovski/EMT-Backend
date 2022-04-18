package com.lab2.emtbackend.configuration.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lab2.emtbackend.configuration.JWTAuthConstants;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTUtils {
    public String extractEmail(String token) {
        /*
        First, we check and validate the signature
        If signature is not valid, throw Exception and 403 Forbidden.
         */
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(JWTAuthConstants.SECRET)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Invalid Signature!");
        }

        return JWT.decode(token).getClaims().get("sub").asString();
    }

    public Date extractExpiration(String token) {
        return JWT.decode(token).getClaims().get("exp").asDate();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        return createToken(userDetails.getUsername());
    }

    private String createToken(String username) {
        return JWT.create()
                .withSubject(username)//dto here
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTAuthConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JWTAuthConstants.SECRET));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractEmail(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}


package com.blogapis.bloggingappllication.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JWTTokenHelper {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private String secret = "JwtTokenKey";



    public String getUsernameFromToken(String token){
        // retrieve username from jwt token
        return getClaimFromToken(token, Claims::getSubject);
    }


    public Date getExpirationDate(String token) {
        // retrieve Expiration date from jwt token
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    private Claims getAllClaimsFromToken(String token) {
        // for retrieving any information from jwt token we will need the secret key
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    public Boolean isTokenExpired(String token) {
        // check if token is expired
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }


    public String generateToken(UserDetails userDetails) {
        // generate token
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /* While creating token
    * Define claims of the token , like Issuer, Expiration, Subject and Id.
    * Sign the JWT using HS512 algorithm and secret key.
    * According to the JWT Compact Serialization  */

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}

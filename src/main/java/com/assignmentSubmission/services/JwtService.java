package com.assignmentSubmission.services;

import com.assignmentSubmission.entity.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService
{
    // Implement JWT service methods here
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryDuration;

    private Algorithm algorithm;

    private final String USER_ID = "userId";

    @PostConstruct
    public void postConstruct()
    {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    // Implement JWT generation, validation, and decoding methods here

    // generate token for user with given user object
    public String generateToken(Users users)
    {
        return JWT.create()
                .withClaim(USER_ID, users.getUserid())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryDuration))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    // verifying token here
    public String getUserName(String token)
    {
        DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return decodedJWT.getClaim(USER_ID).asString();
    }
}

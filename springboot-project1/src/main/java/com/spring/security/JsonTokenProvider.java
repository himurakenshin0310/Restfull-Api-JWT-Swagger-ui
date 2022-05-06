package com.spring.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JsonTokenProvider {
	private final String JWT_SERECT = "small application";
	private final long JWT_EXPIRATION = 30 * 24 * 60 * 60 * 1000l;

	public String generateToken(UserCustomDetail userDetail) {
		Date now = new Date();
		Date expiryDate = new Date(System.currentTimeMillis() + JWT_EXPIRATION);
		Algorithm algorithm = Algorithm.HMAC512(JWT_SERECT.getBytes());
		return JWT.create().withSubject(userDetail.getUsername()).withIssuedAt(now).withExpiresAt(expiryDate)
				.sign(algorithm);
	}

	public String getUserNameFromToken(String token) {
		Algorithm algorithm = Algorithm.HMAC512(JWT_SERECT.getBytes());
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decodeToken = verifier.verify(token);
		return decodeToken.getSubject();
	}

	public boolean verifyToken(String token) {
		Algorithm algorithm = Algorithm.HMAC512(JWT_SERECT.getBytes());
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decodeToken = verifier.verify(token);
		if (decodeToken.getSubject().isEmpty())
			return false;
		else
			return true;

	}
}

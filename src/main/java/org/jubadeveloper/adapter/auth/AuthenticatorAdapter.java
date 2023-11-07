package org.jubadeveloper.adapter.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.jubadeveloper.core.domain.User;
import org.jubadeveloper.core.ports.AuthenticatorPort;
import org.jubadeveloper.several.exceptions.AuthenticationException;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

public class AuthenticatorAdapter implements AuthenticatorPort {
    private final JWTVerifier verifier;
    private final Algorithm algorithm;
    public AuthenticatorAdapter(String privateKey) throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(privateKey);
        verifier = JWT.require(algorithm).withIssuer("auth0").build();
    }
    @Override
    public String auth(User user) {
        return JWT.create()
                .withIssuer("auth0")
                .withSubject(user.getEmail())
                .withClaim("email", user.getEmail())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1200000L))
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }

    @Override
    public String checkAuth(String token) throws AuthenticationException {
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("email").asString();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new AuthenticationException();
        }
    }

    @Override
    public String updateToken(String token) throws AuthenticationException {
        DecodedJWT decodedJWT = verifier.verify(token);
        if (decodedJWT.getToken() != null) {
            return JWT.create()
                    .withIssuer(decodedJWT.getIssuer())
                    .withSubject(decodedJWT.getSubject())
                    .withClaim("email", decodedJWT.getClaim("email").asString())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1200000L))
                    .withJWTId(UUID.randomUUID().toString())
                    .sign(algorithm);
        } else {
            throw new AuthenticationException();
        }
    }
    @Override
    public String getAuthRequest (String authHeader) {
        return authHeader.split(" ")[1];
    }
}

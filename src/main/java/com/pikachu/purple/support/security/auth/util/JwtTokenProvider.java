package com.pikachu.purple.support.security.auth.util;

import com.auth0.jwk.InvalidPublicKeyException;
import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.support.security.auth.vo.JwtClaims;
import java.net.URL;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String createToken(
        JwtClaims jwtClaims,
        String secret
    ) {
        return JWT
            .create()
            .withJWTId(jwtClaims.getRegisteredClaims().getJti())
            .withSubject(jwtClaims.getRegisteredClaims().getSub())
            .withIssuer(jwtClaims.getRegisteredClaims().getIss())
            .withAudience(String.valueOf(jwtClaims.getRegisteredClaims().getAud()))
            .withIssuedAt(jwtClaims.getRegisteredClaims().getIat())
            .withNotBefore(jwtClaims.getRegisteredClaims().getNbf())
            .withExpiresAt(jwtClaims.getRegisteredClaims().getExp())
            .withPayload(jwtClaims.getCustomClaims())
            .sign(Algorithm.HMAC256(secret));
    }

    public JwtClaims verifyToken(
        String token,
        String secret
    ) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return verifyToken(token, algorithm);
    }

    public JwtClaims verifyJwksBasedToken(
        String token,
        URL jwksUri
    ) throws BusinessException, JwkException {
        JwkProvider provider = new JwkProviderBuilder(jwksUri)
            .cached(10, 1440L, TimeUnit.HOURS)
            .rateLimited(10, 1, TimeUnit.MINUTES)
            .build();
        DecodedJWT jwt = decodeToken(token);
        Jwk jwk = provider.get(jwt.getKeyId());
        Algorithm algorithm = extractAlgorithm(jwk);

        return verifyToken(token, algorithm);
    }

    private Algorithm extractAlgorithm(Jwk jwk) throws InvalidPublicKeyException {
        return switch (jwk.getAlgorithm()) {
            case "ES256" -> Algorithm.ECDSA256((ECPublicKey) jwk.getPublicKey(), null);
            case "ES384" -> Algorithm.ECDSA384((ECPublicKey) jwk.getPublicKey(), null);
            case "ES512" -> Algorithm.ECDSA512((ECPublicKey) jwk.getPublicKey(), null);
            case "RS256" -> Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
            case "RS384" -> Algorithm.RSA384((RSAPublicKey) jwk.getPublicKey(), null);
            case "RS512" -> Algorithm.RSA512((RSAPublicKey) jwk.getPublicKey(), null);
            default -> throw new IllegalArgumentException("지원되지 않는 알고리즘입니다.");
        };
    }

    private JwtClaims verifyToken(
        String token,
        Algorithm algorithm
    ) throws BusinessException {
        try {
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
            return JwtClaims.from(decodedJWT);
        } catch (TokenExpiredException e) {
            throw new BusinessException(ErrorCode.JWT_EXPIRED_EXCEPTION);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.JWT_VERIFICATION_EXCEPTION);
        }
    }

    private DecodedJWT decodeToken(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException e) {
            throw new BusinessException(ErrorCode.JWT_VERIFICATION_EXCEPTION);
        }
    }

}


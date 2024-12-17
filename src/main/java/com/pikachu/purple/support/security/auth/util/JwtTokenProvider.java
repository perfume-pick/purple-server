package com.pikachu.purple.support.security.auth.util;

import static com.pikachu.purple.bootstrap.common.exception.ErrorCode.JWT_VERIFICATION_EXCEPTION;

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
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenProvider {

    public static final int CACHE_SIZE = 10;
    public static final int BUCKET_SIZE = 10;
    public static final Long EXPIRES_IN = 1440L;

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
    ) throws BusinessException {
        try {
            JwkProvider provider = new JwkProviderBuilder(jwksUri)
                .cached(CACHE_SIZE, EXPIRES_IN, TimeUnit.HOURS)
                .rateLimited(BUCKET_SIZE, 1, TimeUnit.MINUTES)
                .build();
            DecodedJWT jwt = decodeToken(token);
            Jwk jwk = provider.get(jwt.getKeyId());
            Algorithm algorithm = extractAlgorithm(jwk);

            return verifyToken(token, algorithm);
        } catch (JwkException e) {
            throw new BusinessException(JWT_VERIFICATION_EXCEPTION);
        }

    }

    private Algorithm extractAlgorithm(Jwk jwk) {
        try {
            return switch (jwk.getAlgorithm()) {
                case "ES256" -> Algorithm.ECDSA256((ECPublicKey) jwk.getPublicKey(), null);
                case "ES384" -> Algorithm.ECDSA384((ECPublicKey) jwk.getPublicKey(), null);
                case "ES512" -> Algorithm.ECDSA512((ECPublicKey) jwk.getPublicKey(), null);
                case "RS256" -> Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
                case "RS384" -> Algorithm.RSA384((RSAPublicKey) jwk.getPublicKey(), null);
                case "RS512" -> Algorithm.RSA512((RSAPublicKey) jwk.getPublicKey(), null);
                default -> throw new BusinessException(JWT_VERIFICATION_EXCEPTION);
            };
        } catch (Exception e) {
            throw new BusinessException(JWT_VERIFICATION_EXCEPTION);
        }

    }

    private JwtClaims verifyToken(
        String token,
        Algorithm algorithm
    ) throws BusinessException {
        try {
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
            return JwtClaims.from(decodedJWT);
        } catch (TokenExpiredException e) {
            log.info("ERROR OCCURRED!! time: {}", Date.from(Instant.now()));
            DecodedJWT decodedJWT = decodeToken(token);
            log.info("userId>>> {}", decodedJWT.getClaim("userId").asString());
            log.info("email>>> {}", decodedJWT.getClaim("email").asString());
            log.info("exp>>> {}", decodedJWT.getClaim("exp").asInstant().toString());
            log.info("now>>> {}", Clock.systemUTC().instant().truncatedTo(ChronoUnit.SECONDS));
            throw new BusinessException(ErrorCode.JWT_EXPIRED_EXCEPTION);
        } catch (Exception e) {
            throw new BusinessException(JWT_VERIFICATION_EXCEPTION);
        }
    }

    private DecodedJWT decodeToken(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException e) {
            throw new BusinessException(JWT_VERIFICATION_EXCEPTION);
        }
    }

}


package com.pikachu.purple.application.user.service.util.impl;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.AccessTokenExpiredException;

import com.pikachu.purple.application.common.properties.JwtTokenProperties;
import com.pikachu.purple.application.user.port.out.UserTokenRepository;
import com.pikachu.purple.application.user.service.util.UserTokenService;
import com.pikachu.purple.application.user.service.util.UserTokenType;
import com.pikachu.purple.application.user.service.util.strategy.SocialLoginStrategy;
import com.pikachu.purple.application.user.service.util.strategy.SocialLoginStrategyFactory;
import com.pikachu.purple.application.user.vo.tokens.AccessToken;
import com.pikachu.purple.application.user.vo.tokens.IdToken;
import com.pikachu.purple.application.user.vo.tokens.JwtToken;
import com.pikachu.purple.application.user.vo.tokens.RefreshToken;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import com.pikachu.purple.support.security.auth.util.JwtTokenProvider;
import com.pikachu.purple.support.security.auth.vo.JwtClaims;
import com.pikachu.purple.support.security.auth.vo.JwtClaims.RegisteredClaims;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTokenServiceImpl implements UserTokenService {

    private final JwtTokenProperties jwtTokenProperties;
    private final JwtTokenProvider jwtTokenProvider;
    private final SocialLoginStrategyFactory socialLoginStrategyFactory;
    private final UserTokenRepository userTokenRepository;

    @Override
    public IdToken resolveIdToken(
        String idToken,
        SocialLoginProvider provider
    ) {
        SocialLoginStrategy socialLoginStrategy = socialLoginStrategyFactory.getStrategy(provider);

        return socialLoginStrategy.resolveIdToken(idToken);
    }

    @Override
    public AccessToken resolveAccessToken(String accessToken) {
        JwtClaims jwtClaims = jwtTokenProvider.verifyToken(
            accessToken,
            jwtTokenProperties.getAccess().secret()
        );
        Long userId = Long.valueOf(
            jwtClaims.getCustomClaims().get("userId").toString().replace("\"", "")
        );
        String email = jwtClaims.getCustomClaims().get("email").toString().replace("\"", "");

        userTokenRepository.findAccessTokenByUserId(userId).orElseThrow(() -> AccessTokenExpiredException);

        return new AccessToken(
            userId,
            email
        );
    }

    @Override
    public RefreshToken resolveRefreshToken(String refreshToken) {
        JwtClaims jwtClaims = jwtTokenProvider.verifyToken(
            refreshToken,
            jwtTokenProperties.getRefresh().secret()
        );

        Long userId = Long.valueOf(jwtClaims.getCustomClaims().get("userId").toString());

        userTokenRepository.findRefreshTokenByUserId(userId)
            .orElseThrow(() -> new BusinessException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));

        return new RefreshToken(userId);
    }

    @Override
    public JwtToken resolveJwtToken(String jwtToken) {
        JwtClaims jwtClaims = jwtTokenProvider.verifyToken(
            jwtToken,
            jwtTokenProperties.getJwt().secret()
        );

        String accessToken = jwtClaims.getCustomClaims().get("accessToken").toString();
        String refreshToken = jwtClaims.getCustomClaims().get("refreshToken").toString();

        return new JwtToken(
            accessToken,
            refreshToken
        );
    }

    @Override
    public String generateAccessToken(User user) {
        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("type", UserTokenType.ACCESS_TOKEN.name());
        customClaims.put("userId", String.valueOf(user.getId()));
        customClaims.put("email", user.getEmail());

        JwtClaims jwtClaims = new JwtClaims(
            new RegisteredClaims(
                null,
                Date.from(
                    Instant.now().plusSeconds(jwtTokenProperties.getAccess().expireSeconds())
                ),
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                jwtTokenProperties.getIssuer(),
                null,
                null
            ),
            customClaims
        );

        String accessToken = jwtTokenProvider.createToken(
            jwtClaims,
            jwtTokenProperties.getAccess().secret()
        );

        userTokenRepository.saveAccessToken(
            user.getId(),
            accessToken,
            jwtTokenProperties.getAccess().expireSeconds()
        );

        return jwtTokenProvider.createToken(jwtClaims, jwtTokenProperties.getAccess().secret());
    }

    @Override
    public String generateRefreshToken(User user) {
        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("type", UserTokenType.REFRESH_TOKEN.name());
        customClaims.put("userId", String.valueOf(user.getId()));

        JwtClaims jwtClaims = new JwtClaims(
            new RegisteredClaims(
                null,
                Date.from(
                    Instant.now().plusSeconds(jwtTokenProperties.getRefresh().expireSeconds())
                ),
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                jwtTokenProperties.getIssuer(),
                null,
                null
            ),
            customClaims
        );

        String refreshToken = jwtTokenProvider.createToken(
            jwtClaims,
            jwtTokenProperties.getRefresh().secret()
        );

        userTokenRepository.saveRefreshToken(
            user.getId(),
            refreshToken,
            jwtTokenProperties.getRefresh().expireSeconds()
        );

        return refreshToken;
    }

    @Override
    public String generateJwtToken(
        User user,
        String accessToken,
        String refreshToken
    ) {
        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("type", UserTokenType.JWT_TOKEN.name());
        customClaims.put("userId", String.valueOf(user.getId()));
        customClaims.put("accessToken", accessToken);
        customClaims.put("refreshToken", refreshToken);

        JwtClaims jwtClaims = new JwtClaims(
            new RegisteredClaims(
                null,
                Date.from(
                    Instant.now().plusSeconds(jwtTokenProperties.getJwt().expireSeconds())),
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                jwtTokenProperties.getIssuer(),
                null,
                null
            ),
            customClaims
        );

        return jwtTokenProvider.createToken(
            jwtClaims,
            jwtTokenProperties.getJwt().secret()
        );
    }

}

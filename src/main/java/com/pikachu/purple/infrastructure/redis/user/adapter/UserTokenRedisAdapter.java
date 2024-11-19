package com.pikachu.purple.infrastructure.redis.user.adapter;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.AccessTokenExpiredException;

import com.pikachu.purple.application.user.port.out.UserTokenRepository;
import com.pikachu.purple.infrastructure.redis.user.entity.UserAccessTokenRedisHash;
import com.pikachu.purple.infrastructure.redis.user.entity.UserRefreshTokenRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.UserAccessTokenRedisRepository;
import com.pikachu.purple.infrastructure.redis.user.repository.UserRefreshTokenRedisRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTokenRedisAdapter implements UserTokenRepository {

    private final UserRefreshTokenRedisRepository userRefreshTokenRedisRepository;
    private final UserAccessTokenRedisRepository userAccessTokenRedisRepository;

    @Override
    public void saveRefreshToken(
        Long userId,
        String refreshToken,
        Long expirationSeconds
    ) {
        UserRefreshTokenRedisHash userRefreshTokenRedisHash = new UserRefreshTokenRedisHash(
            userId,
            refreshToken,
            expirationSeconds
        );

        userRefreshTokenRedisRepository.save(userRefreshTokenRedisHash);
    }

    @Override
    public void saveAccessToken(
        Long userId,
        String accessToken,
        Long expirationSeconds
    ) {
        UserAccessTokenRedisHash userAccessTokenRedisHash = new UserAccessTokenRedisHash(
            userId,
            accessToken,
            expirationSeconds
        );

        userAccessTokenRedisRepository.save(userAccessTokenRedisHash);
    }

    @Override
    public void findAccessTokenByUserId(Long userId) {
        if(userAccessTokenRedisRepository.findById(userId).isEmpty()) {
            throw AccessTokenExpiredException;
        }
    }

    @Override
    public Optional<String> findRefreshTokenByUserId(Long userId) {
        Optional<UserRefreshTokenRedisHash> userRefreshTokenRedisHash = userRefreshTokenRedisRepository.findById(
            userId);

        String refreshToken = userRefreshTokenRedisHash.get().getRefreshToken();

        return Optional.of(refreshToken);
    }

    @Override
    public void deleteAllToken(Long userId) {
        userAccessTokenRedisRepository.deleteById(userId);
        userRefreshTokenRedisRepository.deleteById(userId);
    }
}

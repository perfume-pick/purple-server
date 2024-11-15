package com.pikachu.purple.infrastructure.redis.user.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pikachu.purple.infrastructure.redis.user.entity.OAuthTokenRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.OAuthTokenRedisRepository;
import java.util.LinkedHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OAuthTokenRedisRepositoryImpl implements OAuthTokenRedisRepository {

    private static final String KEY = "oauth_token:";
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void save(OAuthTokenRedisHash oAuthTokenRedisHash) {
        String key = KEY + oAuthTokenRedisHash.getId();

        redisTemplate.opsForValue().set(key, oAuthTokenRedisHash);
    }

    @Override
    public OAuthTokenRedisHash find(Long userId) {
        String key = KEY + userId;

        Object data = redisTemplate.opsForValue().get(key);
        if (data instanceof LinkedHashMap) {
            return objectMapper.convertValue(data, OAuthTokenRedisHash.class);
        } else if (data instanceof OAuthTokenRedisHash redisHash) {
            return redisHash;
        } else {
            return null;
        }
    }

}

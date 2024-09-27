package com.pikachu.purple.infrastructure.redis.user.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pikachu.purple.infrastructure.redis.user.entity.PerfumeHistoryRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.PerfumeHistoryRedisRepository;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerfumeHistoryRedisRepositoryImpl implements PerfumeHistoryRedisRepository {

    private static final int MAX_SIZE = 9;
    private static final String KEY = "searchHistory:";

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void save(PerfumeHistoryRedisHash perfumeHistoryRedisHash) {
        Long getCount = redisTemplate.opsForList().size(KEY + perfumeHistoryRedisHash.getId());
        redisTemplate.opsForList().leftPush(
            KEY + perfumeHistoryRedisHash.getId(),
            perfumeHistoryRedisHash
        );

        if(getCount != null && getCount >= 10) {
            redisTemplate.opsForList().trim(
                KEY + perfumeHistoryRedisHash.getId(),
                0,
                MAX_SIZE
            );
        }
    }

    @Override
    public List<PerfumeHistoryRedisHash> findAllByUserId(Long userId) {
        List<Object> result = redisTemplate.opsForList().range(
            KEY + userId,
            0,
            MAX_SIZE
        );

        if (result == null) {
            return Collections.emptyList();
        }

        return result.stream()
            .map(object -> objectMapper.convertValue(
                object,
                PerfumeHistoryRedisHash.class
            ))
            .toList();
    }

    @Override
    public void deleteAllPerfumeHistory(Long userId) {
        redisTemplate.delete(KEY + userId);
    }

}
